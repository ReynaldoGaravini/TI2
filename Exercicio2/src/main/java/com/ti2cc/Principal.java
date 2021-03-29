package com.ti2cc;
import java.util.*;

public class Principal {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		dao.conectar();

		/*Menu de Intera��o CRUD
		 *1) Mostrar
		 *2) Inserir
		 *3) Excluir
		 *4) Atualizar
		 *5) Sair
		 */
		int numMenu;
		do {
			System.out.println("==== Menu ====\n1) Mostrar\n2) Inserir\n3) Excluir\n4) Atualizar\n5) Sair\nDigite o n�mero do menu:");
			
			numMenu = sc.nextInt();
			
			switch(numMenu) {
			case 1:
				//Mostrar funcion�rios
				Funcionario[] funcionarios = dao.getFuncionarios();
				System.out.println("==== Mostrar funcion�rios === ");		
				for(int i = 0; i < funcionarios.length; i++) {
					System.out.println(funcionarios[i].toString());
				}
				break;
			case 2:
				//Inserir um elemento na tabela
				System.out.println("Digite os novos dados do funcion�rio a ser INSERIDO");
				System.out.println("codigo: ");
				int codigoI = sc.nextInt();
				System.out.println("nome..: ");
				String nomeI = sc.next();
				System.out.println("CPF...: ");
				String CPFI = sc.next();
				System.out.println("sexo..: ");
				char sexoI = sc.next().charAt(0);
				Funcionario funcionarioI = new Funcionario(codigoI, nomeI, CPFI, sexoI);
				if(dao.inserirFuncionario(funcionarioI) == true) {
					System.out.println("Inser��o com sucesso -> " + funcionarioI.toString());
				}
				break;
			case 3:
				//Excluir funcion�rio
				System.out.println("Digite o codigo do funcion�rio a ser EXCLUIDO");
				dao.excluirFuncionario(sc.nextInt());
				break;
			case 4:
				//Atualizar funcion�rio
				System.out.println("Digite o codigo do funcion�rio a ser ATUALIZADO\ncodigo:");
				int codigoA = sc.nextInt();
				System.out.println("Digite os dados ATUALIZADOS");
				System.out.println("nome..:");
				String nomeA = sc.nextLine();
				System.out.println("CPF...:");
				String CPFA = sc.nextLine();
				System.out.println("sexo..:");
				char sexoA = sc.nextLine().charAt(0);
				Funcionario funcionarioA = new Funcionario(codigoA, nomeA, CPFA, sexoA);
				funcionarioA.setCPF("CPF atualizado");
				dao.atualizarFuncionario(funcionarioA);
				break;
			case 5:
				System.out.println("Programa encerrado");
				break;
			default:
				System.out.println("N�mero Inv�lido");
			}
		}while(numMenu!=5);
	
		dao.close();
	}
}

