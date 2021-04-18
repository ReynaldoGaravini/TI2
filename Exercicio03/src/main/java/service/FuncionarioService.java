package service;

import dao.FuncionarioDAO;
import model.Funcionario;
import spark.Request;
import spark.Response;

public class FuncionarioService {
	private FuncionarioDAO funcionarioDAO;

	public FuncionarioService() {
		funcionarioDAO = new FuncionarioDAO();
		
		funcionarioDAO.conectar();
	}
	
	public Object add(Request request, Response response) {
		String nome = request.queryParams("nome");
		float salario = Float.parseFloat(request.queryParams("salario"));
		String CPF = request.queryParams("CPF");
		char sexo = request.queryParams("sexo").charAt(0);
		
		int id = funcionarioDAO.getMaxId() + 1;
		
		Funcionario funcionario = new Funcionario(id, nome, salario, CPF, sexo);
		
		funcionarioDAO.add(funcionario);
		
		response.status(201); // 201 Created
		return id;
	}
	
	public Object get(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Funcionario funcionario = (Funcionario) funcionarioDAO.get(id);
		
		if(funcionario != null) {
			response.header("Content-Type", "aplication/xml");
			response.header("Content-Encoding", "UTF-8");
			
			return "<funcionario>\n" +
			       "\t<id>" + funcionario.getId() + "<id>\n" +
			       "\t<nome>" + funcionario.getNome() + "<nome>\n" +
			       "\t<salario>" + funcionario.getSalario() + "<salario>\n" +
			       "\t<CPF>" + funcionario.getCPF() + "<CPF>\n" +
			       "\t<sexo>" + funcionario.getSexo() + "<sexo>\n" +
			       "<funcionario>\n";
		} else {
			response.status(404);// 404 Not found
			return "Funcionario " + id + " nao encontrado.";
		}
	}
	
	public Object update(Request request, Response response) {
		int id = Integer.parseInt(request.params(":id"));
		
		Funcionario funcionario = (Funcionario) funcionarioDAO.get(id);
		
		if(funcionario != null) {
			funcionario.setNome(request.queryParams("nome"));
			funcionario.setSalario(Float.parseFloat(request.queryParams("salario")));
			funcionario.setCPF(request.queryParams("CPF"));
			funcionario.setSexo(request.queryParams("sexo").charAt(0));
			
			funcionarioDAO.update(funcionario);
			
			return id;
		} else {
			response.status(404); //404 Not found
			return "Funcionario nao encontrado";
		}
	}
	
	public Object remove(Request request, Response response) {
        int id = Integer.parseInt(request.params(":id"));

        Funcionario funcionario = (Funcionario) funcionarioDAO.get(id);

        if (funcionario != null) {

            funcionarioDAO.remove(funcionario);

            response.status(200); // success
        	return id;
        } else {
            response.status(404); // 404 Not found
            return "Produto não encontrado.";
        }
	}
	
	public Object getAll(Request request, Response response) {
		StringBuffer returnValue = new StringBuffer("<funcionarios type=\"array\">");
		for(Funcionario funcionario : funcionarioDAO.getAll()) {
			returnValue.append("\n<funcionario>\n" +
			"\t<id>" + funcionario.getId() + "<id>\n" +
			"\t<nome>" + funcionario.getNome() + "<nome>\n" +
			"\t<salario>" + funcionario.getSalario() + "<salario>\n" +
			"\t<CPF>" + funcionario.getCPF() + "<CPF>\n" +
			"\t<sexo>" + funcionario.getSexo() + "<sexo>\n" +
			"<funcionario>\n");
		}
		returnValue.append("</funcionario>");
		response.header("Content-Type", "aplication/xml");
		response.header("Content-Encoding", "UTF-8");
		return returnValue.toString();
	}
}
