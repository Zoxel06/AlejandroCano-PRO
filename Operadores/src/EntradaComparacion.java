public class EntradaComparacion {

    public static void main(String[] args) {
        int n1 = 20;
        int n2 = 30;
        boolean resultado = n1 > n2;
        System.out.println("El resultado de la comparacion es "+resultado);
        resultado = n1 >= n2;
        System.out.println("El resultado de la comparacion es "+resultado);
        resultado = n1<n2;
        System.out.println( "El resultado de la comparacion es "+resultado);
        n1=80;
        n2=30;
        resultado = n1<=n2;
        System.out.println( "El resultado de la comparacion es "+resultado);
        n1=90;
        n2=90;
        resultado = n1==n2;
        System.out.println( "El resultado de la comparacion es "+resultado);
        // != diferente
        resultado = n1!=n2; // 90!=90 false
        System.out.println( "El resultado de la comparacion es "+resultado);
        // negacion !
        resultado = !resultado; //true



    }
}
