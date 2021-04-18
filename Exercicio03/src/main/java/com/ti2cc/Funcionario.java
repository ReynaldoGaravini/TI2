package com.ti2cc;

public class Funcionario {
	private int id;
	private String nome;
	private float salario;
	private String CPF;
	private char sexo;
	
	public Funcionario() {
		this.id = -1;
		this.nome = "";
		this.salario = 0;
		this.CPF = "";
		this.sexo = '*';
	}
	
	public Funcionario(int id, String nome, float salario, String CPF, char sexo) {
		this.id = id;
		this.nome = nome;
		this.salario = salario;
		this.CPF = CPF;
		this.sexo = sexo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getSalario() {
		return salario;
	}
	
	public void setSalario(float salario) {
		this.salario = salario;
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
		return "Funcionario [id=" + id + ", Nome=" + nome + ", Salario=" + salario + ", CPF=" + CPF + ", sexo=" + sexo + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Funcionario) obj).getId());
	}
}
