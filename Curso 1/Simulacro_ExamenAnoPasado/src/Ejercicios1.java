import java.util.Scanner;

public class Ejercicios1 {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        System.out.println("Dime tu nombre");
        String nombre = scanner.next();
        System.out.println("Dime tu apellido");
        String apellido = scanner.next();
        System.out.println("Dime tu edad");
        int edad = scanner.nextInt();
        System.out.println("Dime tu altura");
        float altura = scanner.nextFloat();
        System.out.println("Dime tu peso");
        float peso = scanner.nextFloat();
        System.out.println("Dime tu sexo");
        String sexo = scanner.next();
        //char sexo = scanner.next().charAt(0); //asi se haria si decidiera usar char en vez de String
        //el 0 es porque quiero la letra "A" y es la primera letra del abecedario, la "B" seria 1...

        float imc = calculoIMC(peso,altura);
        System.out.printf("Hola %s %s, teniendo en cuenta que tu altura es de %.2fm y " +
                "tu peso es de %.2fkg, tu IMC tiene un valor de %.2f\n",nombre,apellido,altura,peso,imc);
    }


    public static float calculoIMC(float peso, float altura){
        return (float) (peso/Math.pow(altura,2));
    }
}
