"""Sistema de Control de Tráfico Aéreo - Gestión de vuelos"""
import csv, time, threading
from datetime import datetime

class ControladorAereo:
    def __init__(self):
        self.vuelos, self.pistas = {}, {}
        self.flujo_aterrizaje, self.flujo_despegue = [], []
        self.minuto_actual, self.simulacion_activa, self.intervalo_real = 0, False, 5
        self.log_eventos, self.estadisticas_pistas, self.total_operaciones, self.tiempos_operaciones = [], {}, 0, {}
        
    def _cargar_csv(self, archivo, procesar_fila, tipo='datos'):
        try:
            with open(archivo, 'r', encoding='utf-8') as f:
                reader = csv.DictReader(f)
                print(f"\n{'='*80}\n📂 Cargando {tipo} desde {archivo}...\n{'='*80}")
                for row in reader:
                    procesar_fila(row)
                print(f"{'='*80}\n✓ {len(getattr(self, 'pistas' if 'pista' in tipo else 'vuelos'))} {tipo} cargados\n")
        except Exception as e:
            print(f"❌ Error: {e}")
    
    def cargar_vuelos(self, archivo='vuelos.csv'):
        def procesar(row):
            id_vuelo, tipo = row['id_vuelo'].strip(), row['tipo'].strip()
            self.vuelos[id_vuelo] = {
                'id': id_vuelo, 'tipo': tipo,
                'eta': int(row['eta']) if row['eta'].strip() else None,
                'etd': int(row['etd']) if row['etd'].strip() else None,
                'prioridad': int(row['prioridad']) if row['prioridad'].strip() else 0,
                'combustible': int(row['combustible']) if row['combustible'].strip() else None,
                'estado': row['estado'].strip() or 'EN_COLA'
            }
            if self.vuelos[id_vuelo]['estado'] == 'EN_COLA':
                (self.flujo_aterrizaje if tipo == 'ATERRIZAJE' else self.flujo_despegue).append(id_vuelo)
                self.registrar_evento('EN_COLA', f"Vuelo {id_vuelo} en cola para {tipo}", id_vuelo=id_vuelo)
            print(f"  ✓ {id_vuelo}: {tipo} - Prioridad {self.vuelos[id_vuelo]['prioridad']}")
        
        self._cargar_csv(archivo, procesar, 'vuelos')
        if self.vuelos:
            self.registrar_evento('CARGA_INICIAL', f"vuelos={len(self.vuelos)} pistas={len(self.pistas)}")
    
    def cargar_pistas(self, archivo='pistas.csv'):
        def procesar(row):
            id_pista, habilitada = row['id_pista'].strip(), int(row['habilitada'])
            self.pistas[id_pista] = {
                'id_pista': id_pista, 'categoria': row['categoria'].strip(),
                'tiempo_uso': int(row['tiempo_uso']), 'habilitada': habilitada,
                'estado': 'DESHABILITADA' if habilitada == 0 else 'LIBRE',
                'vuelo_asignado': None, 'bloqueada_hasta': None
            }
            self.estadisticas_pistas[id_pista] = {'aterrizajes': 0, 'despegues': 0, 'total_operaciones': 0, 'tiempo_ocupada': 0}
            print(f"  {'✓ ACTIVA' if habilitada else '✗ DESHABILITADA'} - {id_pista} ({self.pistas[id_pista]['categoria']}): {self.pistas[id_pista]['tiempo_uso']} min")
        
        self._cargar_csv(archivo, procesar, 'pistas')
    
    def registrar_evento(self, tipo_evento, descripcion, id_vuelo=None, id_pista=None):
        timestamp = datetime.now().strftime('%H:%M:%S')
        evento = {'minuto_simulado': self.minuto_actual, 'timestamp_real': timestamp, 'tipo': tipo_evento, 'descripcion': descripcion, 'id_vuelo': id_vuelo, 'id_pista': id_pista}
        self.log_eventos.append(evento)
        print(f"[Min {self.minuto_actual:03d}] [{timestamp}] [{tipo_evento}] {descripcion}")
        
        try:
            linea = f"[t={self.minuto_actual}] {tipo_evento}"
            if id_vuelo: linea += f" id_vuelo={id_vuelo}"
            if id_pista: linea += f" pista={id_pista}"
            if id_vuelo and id_vuelo in self.vuelos: linea += f" tipo={self.vuelos[id_vuelo]['tipo']}"
            with open('eventos.log', 'a', encoding='utf-8') as f: f.write(linea + "\n")
        except Exception as e:
            print(f"  ⚠️ Error log: {e}")
    
    def agregar_vuelo(self, id_vuelo, tipo, eta=None, etd=None, prioridad=0, combustible=None, estado='EN_COLA', manual=False):
        if id_vuelo in self.vuelos:
            print(f"⚠️  Vuelo {id_vuelo} ya existe")
            return False
        
        self.vuelos[id_vuelo] = {'id': id_vuelo, 'tipo': tipo, 'eta': eta, 'etd': etd, 'prioridad': prioridad, 'combustible': combustible, 'estado': estado}
        
        if estado == 'EN_COLA':
            (self.flujo_aterrizaje if tipo == 'ATERRIZAJE' else self.flujo_despegue).append(id_vuelo)
            self.registrar_evento('EN_COLA', f"Vuelo {id_vuelo} en cola para {tipo}", id_vuelo=id_vuelo)
        
        if manual: print(f"✓ Vuelo {id_vuelo} agregado")
        return True
    
    def actualizar_estado(self, id_vuelo, nuevo_estado):
        if id_vuelo not in self.vuelos:
            return False
        estado_ant = self.vuelos[id_vuelo]['estado']
        self.vuelos[id_vuelo]['estado'] = nuevo_estado
        print(f"Vuelo {id_vuelo}: {estado_ant} → {nuevo_estado}")
        return True
    
    def asignar_pista(self, id_pista, id_vuelo, minuto_actual):
        if id_pista not in self.pistas or id_vuelo not in self.vuelos:
            print(f"⚠️  {'Pista' if id_pista not in self.pistas else 'Vuelo'} no existe")
            return False
        
        pista = self.pistas[id_pista]
        if pista['habilitada'] == 0 or pista['estado'] == 'OCUPADA':
            msg = 'deshabilitada' if pista['habilitada'] == 0 else f'ocupada hasta min {pista["bloqueada_hasta"]}'
            print(f"⚠️  Pista {id_pista} {msg}")
            return False
        
        pista['estado'], pista['vuelo_asignado'], pista['bloqueada_hasta'] = 'OCUPADA', id_vuelo, minuto_actual + pista['tiempo_uso']
        if id_vuelo not in self.tiempos_operaciones:
            self.tiempos_operaciones[id_vuelo] = {'inicio': minuto_actual, 'fin': None}
        
        self.actualizar_estado(id_vuelo, 'ASIGNADO')
        self.registrar_evento('ASIGNACION', f"Pista {id_pista} → {id_vuelo} hasta min {pista['bloqueada_hasta']}", id_vuelo, id_pista)
        print(f"✓ Pista {id_pista} asignada a {id_vuelo}")
        return True
    
    def liberar_pista(self, id_pista):
        if id_pista not in self.pistas:
            return False
        
        pista = self.pistas[id_pista]
        vuelo = pista['vuelo_asignado']
        
        if vuelo and vuelo in self.vuelos:
            tipo = self.vuelos[vuelo]['tipo']
            stats = self.estadisticas_pistas[id_pista]
            stats['total_operaciones'] += 1
            stats['tiempo_ocupada'] += pista['tiempo_uso']
            stats['aterrizajes' if tipo == 'ATERRIZAJE' else 'despegues'] += 1
            self.total_operaciones += 1
            if vuelo in self.tiempos_operaciones:
                self.tiempos_operaciones[vuelo]['fin'] = self.minuto_actual
        
        pista['estado'], pista['vuelo_asignado'], pista['bloqueada_hasta'] = ('LIBRE' if pista['habilitada'] == 1 else 'DESHABILITADA'), None, None
        if vuelo:
            self.registrar_evento('COMPLETADO', f"id_vuelo={vuelo} pista={id_pista}", vuelo, id_pista)
        print(f"✓ Pista {id_pista} liberada")
        return True
    
    def consumir_combustible(self):
        for v in [v for v in self.vuelos.values() if v['tipo'] == 'ATERRIZAJE' and v['estado'] == 'EN_COLA' and v['combustible']]:
            v['combustible'] -= 1
            if v['combustible'] <= 0:
                self.registrar_evento('COMBUSTIBLE', f"⚠️ {v['id']} SIN COMBUSTIBLE!", id_vuelo=v['id'])
                v['prioridad'] = 2
            elif v['combustible'] <= 5 and v['prioridad'] < 2:
                v['prioridad'] = 2
                self.registrar_evento('EMERGENCIA', f"id_vuelo={v['id']} prioridad=2 motivo=combustible<={v['combustible']}", id_vuelo=v['id'])
    
    def calcular_prioridad_vuelo(self, id_vuelo):
        v = self.vuelos.get(id_vuelo)
        if not v: return (999, 999, 999, id_vuelo)
        tiempo = v['eta'] if v['tipo'] == 'ATERRIZAJE' else v['etd']
        return (-v['prioridad'], v['combustible'] if v['combustible'] else 9999, -(self.minuto_actual - tiempo) if tiempo else 0, id_vuelo)
    
    def gestionar_flujos(self):
        pistas_libres = [p for p, d in self.pistas.items() if d['estado'] == 'LIBRE' and d['habilitada'] == 1]
        if not pistas_libres: return
        
        for pista in pistas_libres:
            candidatos = []
            if self.flujo_aterrizaje:
                candidatos.append(min(self.flujo_aterrizaje, key=self.calcular_prioridad_vuelo))
            if self.flujo_despegue:
                candidatos.append(min(self.flujo_despegue, key=self.calcular_prioridad_vuelo))
            
            if not candidatos: break
            vuelo = min(candidatos, key=self.calcular_prioridad_vuelo)
            
            if self.asignar_pista(pista, vuelo, self.minuto_actual):
                (self.flujo_aterrizaje if self.vuelos[vuelo]['tipo'] == 'ATERRIZAJE' else self.flujo_despegue).remove(vuelo)
    
    def actualizar_pistas(self, minuto=None):
        min_actual = minuto or self.minuto_actual
        for id_p, p in self.pistas.items():
            if p['estado'] == 'OCUPADA' and p['bloqueada_hasta'] and min_actual >= p['bloqueada_hasta']:
                vuelo = p['vuelo_asignado']
                self.liberar_pista(id_p)
                if vuelo and vuelo in self.vuelos:
                    self.actualizar_estado(vuelo, 'COMPLETADO')
    
    def actualizar_sistema(self):
        self.consumir_combustible()
        self.actualizar_pistas()
        self.gestionar_flujos()
        if self.minuto_actual % 5 == 0:
            activos = len([v for v in self.vuelos.values() if v['estado'] not in ['COMPLETADO', 'CANCELADO']])
            self.registrar_evento('SISTEMA', f"Estado: {activos} activos, {len([p for p, d in self.pistas.items() if d['estado'] == 'LIBRE'])} libres")
    
    def cambiar_estado_pista(self, id_pista, habilitar, motivo="Mantenimiento"):
        if id_pista not in self.pistas:
            print(f"❌ Pista {id_pista} no existe")
            return False
        
        pista = self.pistas[id_pista]
        if not habilitar and pista['estado'] == 'OCUPADA':
            print(f"⚠️  Pista ocupada por {pista['vuelo_asignado']}")
            return False
        
        if habilitar == (pista['habilitada'] == 1):
            print(f"⚠️  Pista ya {'habilitada' if habilitar else 'deshabilitada'}")
            return False
        
        pista['habilitada'] = 1 if habilitar else 0
        pista['estado'] = 'LIBRE' if habilitar else 'DESHABILITADA'
        self.registrar_evento('PISTA', f"Pista {id_pista} {'HABILITADA' if habilitar else f'DESHABILITADA - {motivo}'}", id_pista=id_pista)
        print(f"✓ Pista {id_pista} {'habilitada' if habilitar else 'deshabilitada'}")
        return True
    
    def cancelar_vuelo(self, id_vuelo, motivo="Cancelación solicitada"):
        if id_vuelo not in self.vuelos:
            print(f"❌ Vuelo {id_vuelo} no existe")
            return False
        
        v = self.vuelos[id_vuelo]
        if v['estado'] in ['ASIGNADO', 'COMPLETADO', 'CANCELADO']:
            print(f"⚠️  No se puede cancelar - Estado: {v['estado']}")
            return False
        
        if v['estado'] == 'EN_COLA':
            (self.flujo_aterrizaje if v['tipo'] == 'ATERRIZAJE' else self.flujo_despegue).remove(id_vuelo)
        
        v['estado'] = 'CANCELADO'
        self.registrar_evento('CANCELACION', f"Vuelo {id_vuelo} CANCELADO - {motivo}", id_vuelo=id_vuelo)
        print(f"✓ Vuelo {id_vuelo} cancelado")
        return True
    
    def mostrar_estado(self, tipo='completo'):
        if tipo in ['completo', 'pistas']:
            print(f"\n{'='*80}\nESTADO DE PISTAS ({len(self.pistas)})\n{'='*80}")
            for p in sorted(self.pistas.values(), key=lambda x: x['id_pista']):
                icono = "🟢" if p['estado'] == 'LIBRE' else ("🔴" if p['estado'] == 'OCUPADA' else "⚫")
                info = f"  {icono} {p['id_pista']} ({p['categoria']}) - {p['estado']}"
                print(f"{info} - Vuelo: {p['vuelo_asignado']} hasta min {p['bloqueada_hasta']}" if p['estado'] == 'OCUPADA' else info)
            print(f"{'='*80}\n")
        
        if tipo in ['completo', 'flujos']:
            print(f"\n{'='*80}\nFLUJOS DE TRÁFICO\n{'='*80}")
            for nombre, flujo, icono in [('ATERRIZAJE', self.flujo_aterrizaje, '🛬'), ('DESPEGUE', self.flujo_despegue, '🛫')]:
                print(f"\n{icono} {nombre} ({len(flujo)} en cola):")
                if not flujo:
                    print("   (vacío)")
                else:
                    for i, id_v in enumerate(sorted(flujo, key=self.calcular_prioridad_vuelo), 1):
                        v = self.vuelos[id_v]
                        pri = ['NORMAL', 'ALTA', 'EMERGENCIA'][v['prioridad']]
                        tiempo = v['eta'] if nombre == 'ATERRIZAJE' else v['etd']
                        atraso = f"+{self.minuto_actual - tiempo} min" if tiempo and self.minuto_actual > tiempo else ""
                        extra = f" - Combustible: {v['combustible']} min" if v['combustible'] else ""
                        print(f"   {i}. {id_v} - {pri} - ET{'A' if nombre == 'ATERRIZAJE' else 'D'}: {tiempo} {atraso}{extra}")
            print(f"{'='*80}\n")
        
        if tipo in ['completo', 'vuelos']:
            activos = [v for v in self.vuelos.values() if v['estado'] not in ['COMPLETADO', 'CANCELADO']]
            print(f"\n{'='*80}\nVUELOS ACTIVOS ({len(activos)})\n{'='*80}")
            for v in sorted(activos, key=lambda x: (x['tipo'], -x['prioridad'])):
                info = f"  {v['id']} - {v['tipo']} - ET{'A' if v['tipo'] == 'ATERRIZAJE' else 'D'}: {v['eta'] or v['etd']} - Pri: {v['prioridad']} - {v['estado']}"
                print(f"{info} - Comb: {v['combustible']} min" if v['combustible'] else info)
            print(f"{'='*80}\n")
    
    def obtener_estadisticas(self):
        return {
            'total': len(self.vuelos),
            'en_cola': len([v for v in self.vuelos.values() if v['estado'] == 'EN_COLA']),
            'asignados': len([v for v in self.vuelos.values() if v['estado'] == 'ASIGNADO']),
            'completados': len([v for v in self.vuelos.values() if v['estado'] == 'COMPLETADO']),
            'cancelados': len([v for v in self.vuelos.values() if v['estado'] == 'CANCELADO']),
            'aterrizajes': len([v for v in self.vuelos.values() if v['tipo'] == 'ATERRIZAJE']),
            'despegues': len([v for v in self.vuelos.values() if v['tipo'] == 'DESPEGUE'])
        }
    
    def generar_informe(self, mostrar=True, guardar=True):
        stats = self.obtener_estadisticas()
        tiempos_espera = [self.tiempos_operaciones[id_v]['inicio'] - (v['eta'] or v['etd']) 
                         for id_v, v in self.vuelos.items() 
                         if id_v in self.tiempos_operaciones and self.tiempos_operaciones[id_v]['inicio'] and (v['eta'] or v['etd']) and self.tiempos_operaciones[id_v]['inicio'] > (v['eta'] or v['etd'])]
        
        uso_pistas = ', '.join([f'{p}={self.estadisticas_pistas[p]["total_operaciones"]} ops' for p in sorted(self.estadisticas_pistas.keys())])
        
        lineas = [
            "="*80, "INFORME DE SIMULACIÓN", "="*80,
            f"Fecha: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}", 
            f"Duración: {self.minuto_actual} min", "="*80, "",
            "RESUMEN", "-"*80,
            f"- Tiempo simulado (min): {self.minuto_actual}",
            f"- Vuelos atendidos: {stats['completados']}",
            f"- Tiempo medio espera (min): {sum(tiempos_espera)/len(tiempos_espera):.1f if tiempos_espera else 0.0}",
            f"- Uso de pistas: {uso_pistas}",
            f"- Emergencias: {len([e for e in self.log_eventos if e['tipo'] == 'EMERGENCIA'])}",
            "\n- Detalle vuelos completados:"
        ]
        
        for id_v, v in sorted([(i, v) for i, v in self.vuelos.items() if v['estado'] == 'COMPLETADO'], key=lambda x: x[0]):
            if id_v in self.tiempos_operaciones:
                t = self.tiempos_operaciones[id_v]
                tipo = f"{v['tipo']}, EMERGENCIA" if v['prioridad'] == 2 else v['tipo']
                lineas.append(f"  • {id_v} ({tipo}) t_inicio={t['inicio']} t_fin={t['fin']}")
        
        lineas.extend(["\n" + "="*80, f"Total: {stats['total']} | En cola: {stats['en_cola']} | Asignados: {stats['asignados']} | Completados: {stats['completados']}", 
                      f"Tasa éxito: {stats['completados']/stats['total']*100 if stats['total'] else 0:.1f}%", "="*80])
        
        texto = "\n".join(lineas)
        if mostrar: print("\n" + texto)
        if guardar:
            try:
                with open('informe.log', 'w', encoding='utf-8') as f: f.write(texto)
                print("\n✓ Informe guardado")
            except Exception as e:
                print(f"\n❌ Error: {e}")
        return texto
    
    def avanzar_minutos(self, n=1):
        print(f"\n⏩ Avanzando {n} min...\n{'-'*80}")
        for _ in range(n):
            self.minuto_actual += 1
            print(f"\n[Minuto {self.minuto_actual}]")
            self.actualizar_sistema()
        print(f"{'-'*80}\n✓ Minuto {self.minuto_actual}\n")
    
    def guardar_estado_csv(self, archivo='estado.csv'):
        try:
            with open(archivo, 'w', encoding='utf-8', newline='') as f:
                f.write('id_vuelo,tipo,eta,etd,prioridad,combustible,estado\n')
                for v in sorted(self.vuelos.values(), key=lambda x: x['id']):
                    f.write(f"{v['id']},{v['tipo']},{v['eta'] or ''},{v['etd'] or ''},{v['prioridad']},{v['combustible'] or ''},{v['estado']}\n")
            print(f"✓ Estado guardado ({len(self.vuelos)} vuelos)")
            return True
        except Exception as e:
            print(f"❌ Error: {e}")
            return False
    
    def menu_anadir_vuelo(self):
        print(f"\n{'='*80}\nAÑADIR VUELO MANUAL\n{'='*80}")
        try:
            id_v = input("ID vuelo: ").strip()
            if not id_v: return print("❌ ID requerido")
            
            tipo_op = input("Tipo (1=ATERRIZAJE, 2=DESPEGUE): ").strip()
            if tipo_op == '1':
                tipo, eta, etd, comb = 'ATERRIZAJE', int(input("ETA: ") or self.minuto_actual), None, int(input("Combustible: ") or 20)
            elif tipo_op == '2':
                tipo, eta, etd, comb = 'DESPEGUE', None, int(input("ETD: ") or self.minuto_actual), None
            else:
                return print("❌ Opción inválida")
            
            pri = int(input("Prioridad (0-2) [0]: ") or 0)
            self.agregar_vuelo(id_v, tipo, eta, etd, pri, comb, 'EN_COLA', True)
        except Exception as e:
            print(f"❌ Error: {e}")
    
    def ejecutar_menu(self):
        print("="*80 + f"\n{' '*20}SISTEMA DE CONTROL DE TRÁFICO AÉREO\n" + "="*80 + "\n")
        
        while True:
            print(f"\n{'='*80}\n{' '*25}MENÚ DE OPERACIONES\n{'='*80}")
            for i, opt in enumerate(["Cargar datos", "Añadir vuelo", "Avanzar 1 min", "Avanzar N min", "Ver estado", "Generar informe", "Deshabilitar pista", "Habilitar pista", "Cancelar vuelo", "Guardar y salir", "Salir"], 1):
                print(f"{i}. {opt}")
            print(f"{'='*80}\nMinuto: {self.minuto_actual}\n{'='*80}")
            
            try:
                op = input("\nOpción (1-11): ").strip()
                
                if op == '1':
                    print("\n" + "="*80)
                    self.cargar_pistas('pistas.csv')
                    self.cargar_vuelos('vuelos.csv')
                    print("="*80)
                elif op == '2':
                    self.menu_anadir_vuelo()
                elif op == '3':
                    self.avanzar_minutos(1)
                elif op == '4':
                    try:
                        n = int(input("\nMinutos: "))
                        if n > 0: self.avanzar_minutos(n)
                        else: print("❌ Número positivo")
                    except: print("❌ Inválido")
                elif op == '5':
                    self.mostrar_estado('completo')
                elif op == '6':
                    self.generar_informe(True, True)
                elif op == '7':
                    self.cambiar_estado_pista(input("\nID pista: ").strip(), False, input("Motivo [Mantenimiento]: ").strip() or "Mantenimiento")
                elif op == '8':
                    self.cambiar_estado_pista(input("\nID pista: ").strip(), True)
                elif op == '9':
                    self.cancelar_vuelo(input("\nID vuelo: ").strip(), input("Motivo: ").strip() or "Cancelación solicitada")
                elif op == '10':
                    print("\n" + "="*80 + "\nGUARDANDO Y SALIENDO\n" + "="*80)
                    self.registrar_evento('FIN_SIMULACION', f"vuelos_atendidos={self.obtener_estadisticas()['completados']}")
                    self.guardar_estado_csv('estado.csv')
                    self.generar_informe(False, True)
                    print("\n📁 Archivos: estado.csv, eventos.log, informe.log\n¡Hasta luego!\n" + "="*80)
                    break
                elif op == '11':
                    if input("\n¿Salir sin guardar? (s/n): ").lower() == 's':
                        print("\n¡Hasta luego!")
                        break
                else:
                    print("❌ Opción 1-11")
                
                if op != '10': input("\nEnter para continuar...")
                
            except KeyboardInterrupt:
                if input("\n\n¿Salir? (s/n): ").lower() == 's': break
            except Exception as e:
                print(f"\n❌ Error: {e}")
                input("\nEnter...")

if __name__ == "__main__":
    ControladorAereo().ejecutar_menu()