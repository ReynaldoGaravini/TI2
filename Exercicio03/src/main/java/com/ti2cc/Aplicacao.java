package com.ti2cc;

import static spark.Spark.*;

public class Aplicacao {
	private static FuncionarioService funcionarioService = new FuncionarioService();
	
	public static void main(String[] args) {
		port(6789);
		
		post("/funcionario", (request, response) -> funcionarioService.add(request, response));
		
		get("/funcionario/:id", (request, response) -> funcionarioService.get(request, response));
		
		get("/funcionario/update/:id", (request, response) -> funcionarioService.update(request, response));
		
		get("/funcionario/delete/:id", (request, response) -> funcionarioService.remove(request, response));
		
		get("/funcionario", (request, response) -> funcionarioService.getAll(request, response));
	}
}
