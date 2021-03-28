import java.util.*;

class SomaDoisInteiros {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Declaração de variáveis
		int num1, num2, soma;
		
		//Le as varaiáveis
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
