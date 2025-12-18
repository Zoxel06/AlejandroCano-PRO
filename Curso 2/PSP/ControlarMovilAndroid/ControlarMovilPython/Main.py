import pyautogui
import socket
import time

#===== CONFIGURACIÓN =====
MOBILE_IP = "192.168.2.213"  # Cambia por la IP que muestra tu app Android
PORT = 9000
CAPTURE_DELAY = 3  # segundos de espera antes de capturar

def capture_and_send():
    try:
        print(f"Cambiando de ventana... capturando en {CAPTURE_DELAY} segundos")
        time.sleep(CAPTURE_DELAY)

        # Captura de pantalla
        screenshot = pyautogui.screenshot()
        screenshot.save("captura.png")

        with open("captura.png", "rb") as f:
            data = f.read()

        # Conectar al móvil
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.settimeout(5)  # evita bloqueo
        print(f"Intentando conectar al móvil {MOBILE_IP}:{PORT}...")
        s.connect((MOBILE_IP, PORT))

        # Enviar tamaño y datos
        s.sendall(len(data).to_bytes(4, "big"))
        s.sendall(data)
        s.close()

        print("✅ Captura enviada al móvil correctamente")

    except socket.timeout:
        print("❌ Timeout: el móvil no respondió. Verifica IP y servidor.")
    except ConnectionRefusedError:
        print("❌ Conexión rechazada: asegúrate de iniciar el servidor en el móvil.")
    except Exception as e:
        print(f"❌ Error inesperado: {e}")

#===== PROGRAMA PRINCIPAL =====
while True:
    input("Pulsa ENTER para capturar pantalla...")
    capture_and_send()