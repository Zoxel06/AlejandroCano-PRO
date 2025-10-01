public class Entrada {

    public static void main(String[] args) {
        //OPERADORES ARITMETICOS - BINARIOS
        System.out.println("OPERADORES ARITMETICOS");
        float operador1 = 10;
        int operador2 = 5;
        float resultado = operador1 + operador2;
        System.out.println("El resultado de la suma es " + resultado);
        resultado = operador1 - operador2;
        System.out.println("El resultado de la resta es " + resultado);
        resultado = operador1 * operador2;
        System.out.println("El resultado de la multiplicacion es " + resultado);
        operador1 = 7;
        operador2 = 4;
        resultado = operador1 / operador2;
        System.out.println("El resultado de la division es " + resultado);
        resultado = operador1 % operador2;
        System.out.println("El resultado del resto es " + resultado);


        //ARITMETICOS MONARIOS
        System.out.println("OPERADORES DE ASIGNACION");
        resultado = -resultado;
        System.out.println("El resultado del opuesto es " + resultado);
        resultado++;
        resultado++;
        resultado++;
        resultado++;
        resultado++;
        System.out.println("El resultado del incremento es " + resultado);
        resultado--;
        System.out.println("El resultado del decremento es " + resultado);
        //resultado = 1
        operador1 = 10;
        resultado += operador1; //11
        System.out.println("El resultado de la suma asignada es " + resultado);
        resultado -= 6; //5
        System.out.println("El resultado de la resta asignada es " + resultado);
        resultado *= 3; //15
        System.out.println("El resultado de la multiplicacion asignada es " + resultado);
        resultado /= 2; //7
        System.out.println("El resultado de la division asignada es " + resultado);
        resultado %= 2; //1
        System.out.println("El resultado del modulo asignada es " + resultado);


        //OPERADORES DE COMPRARACION --> numeros y buleanos
        int n1 = 10;
        int n2 = 7;
        // boolean resultado = n1 > n2 true
        // x > x mayor que
        // x >= x mayor o igual que
        // x < x menor que
        // x <= x menor o igual que
        // x == igual que
        // x!= x diferente


        //OPERADORES LOGICOS
        // AND &&
        //  C1     C2     R
        // FALSE  FALSE  FALSE
        // FALSE  TRUE   FALSE
        // TRUE   FALSE  FALSE
        // TRUE   TRUE   TRUE
        // (n1<n2)&&(n3%n4==0)&&(n1>10)&&(n2<100) = F

        System.out.println("OPERADORES LOGICOS");
        n1 = 30;
        n2 = 40;
        boolean n3 = true;
        int n4 = -20;
        boolean resultadoLogico = (n1<n2) && n3 && n4<0; //true && true && true = true
        System.out.println("El resultado logico de las && es "+resultadoLogico);

        // OR ||
        //  C1     C2     R
        // TRUE   TRUE   TRUE
        // FALSE  FALSE  FALSE
        // FALSE  TRUE   TRUE
        // TRUE   FALSE  TRUE

        resultadoLogico = (n1>0) || (n2!=n1) || n3 || n4>0; //true || true || true || false = true
        System.out.println("El resultado logico de las || es "+resultadoLogico);
        boolean resultadoCompuesto = ((n1>n2) && !n3 && (n4<10)) || n4>0 || n2>0;
                                      //(F && F && T) || F || T
                                      // F || F || T
                                      // T

        
        String palabra1 = "hola";
        String palabra2 = "hola";
        boolean resultadoPalabras = palabra1.equalsIgnoreCase(palabra2); //equalesIgnoreCase es para ignorar que las palabras son las mismas
        System.out.println("El resultado de la comparacion de las palabras es "+resultadoPalabras);

        n1 = 4;
        n2 = 3;
        double resultadoOperacion = (double) n1/n2;
        double n1Double = (double) n1;
        String n1String = String.valueOf(n1);
        String palabra = "12345";
        int numeroPalabra = Integer.parseInt(palabra);
        System.out.println("El resultado de la division es "+resultadoOperacion);
    }
}
