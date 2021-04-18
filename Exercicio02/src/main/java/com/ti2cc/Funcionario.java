package com.ti2cc;

public class Funcionario {
	private int codigo;
	private String nome;
	private String CPF;
	private char sexo;
	
	public Funcionario() {
		this.codigo = -1;
		this.nome = "";
		this.CPF = "";
		this.sexo = '*';
	}
	
	public Funcionario(int codigo, String nome, String CPF, char sexo) {
		this.codigo = codigo;
		this.nome = nome;
		this.CPF = CPF;
		this.sexo = sexo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return "Funcionario [codigo=" + codigo + ", Nome=" + nome + ", CPF=" + CPF + ", sexo=" + sexo + "]";
	}
}
