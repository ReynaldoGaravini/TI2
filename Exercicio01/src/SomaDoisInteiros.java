import java.util.*;

class SomaDoisInteiros {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Declara��o de vari�veis
		int num1, num2, soma;
		
		//Le as varai�veis
		System.out.println("Primeiro numero: ");
		num1 = sc.nextInt();
		
		System.out.println("Segundo numero: ");
		num2 = sc.nextInt();
		
		//Soma
		soma = num1 + num2;
		
		//Escreve resultado na tela
		System.out.println("Resultado: " + soma);
	}
}
