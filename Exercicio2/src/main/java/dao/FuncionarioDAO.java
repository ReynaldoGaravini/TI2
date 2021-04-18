package dao;

import java.sql.*;
import model.Funcionario;

public class FuncionarioDAO {
	private Connection conexao;
	private int maxId = 0;
	
	public int getMaxId() {
		return maxId;
	}
	
	public boolean conectar() {
		String driverName = "org.postgresql.Driver";
		String serverName = "localhost";
		String mydatabase = "funcionario";
		int porta = 5432;
		String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + mydatabase;
		String username = "ti2cc";
		String password = "ti@cc";
		boolean status = false;

		try {
			Class.forName(driverName);
			conexao = DriverManager.getConnection(url, username, password);
			status = (conexao == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuada com o postgres -- " + e.getMessage());
		}

		return status;
	}
	
	public FuncionarioDAO() {
		conexao = null;
	}
	
	public void add(Funcionario funcionario) {
		try {
			this.maxId = (funcionario.getId() > this.maxId) ? funcionario.getId() : this.maxId;
			Statement st = conexao.createStatement();
			st.executeUpdate("INSERT INTO funcionarios (id, nome, salario, cpf, sexo) "
					       + "VALUES ("+funcionario.getId()+ ", '" 
					       + funcionario.getNome() + "', '"  
					       + funcionario.getCPF() + "', '"  
					       + funcionario.getSexo() + "', '" 
					       + funcionario.getSalario() + "');");
			st.close();
		} catch (SQLException u) {
			System.out.println("ERRO ao inserir funcionario na base de dados!");
			throw new RuntimeException(u);
		}
	}
	
	public Funcionario get(int id) {
		Funcionario funcionario = new Funcionario();
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM funcionarios WHERE id = " + id);		
	        funcionario.setId(rs.getInt("id"));
	        funcionario.setNome(rs.getString("nome"));
	        funcionario.setCPF(rs.getString("cpf"));
	        funcionario.setSexo(rs.getString("sexo").charAt(0));
	        funcionario.setSalario(rs.getFloat("salario"));
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return funcionario;
	}
	
	public void update(Funcionario funcionario) {
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE funcionarios SET nome = '"
						 + funcionario.getNome() + "', cpf = '"  
						 + funcionario.getCPF() + "', sexo = '" 
						 + funcionario.getSexo() + "', salario = "
						 + funcionario.getSalario()
						 + " WHERE id = " + funcionario.getId();
			st.executeUpdate(sql);
			st.close();
		} catch (SQLException u) {
			System.out.println("ERRO ao atualizar funcionario na base de dados!");
			throw new RuntimeException(u);
		}
	}
	
	public void remove(Funcionario funcionario) {
		try {  
			Statement st = conexao.createStatement();
			st.executeUpdate("DELETE FROM funcionarios WHERE id = " + funcionario.getId());
			st.close();
		} catch (SQLException u) {
			System.out.println("ERRO ao excluir funcionario da base de dados!");
			throw new RuntimeException(u);
		}
	}
	
	public Funcionario[] getAll() {
		Funcionario[] funcionarios = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT * FROM funcionarios");		
	         if(rs.next()){
	             rs.last();
	             funcionarios = new Funcionario[rs.getRow()];
	             rs.beforeFirst();

	             for(int i = 0; rs.next(); i++) {
	                funcionarios[i] = new Funcionario(rs.getInt("id"), 
	                								  rs.getString("nome"), 
	                								  rs.getFloat("salario"), 
	                								  rs.getString("cpf"), 
	                								  rs.getString("sexo").charAt(0));
	             }
	          }
	          st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return funcionarios;
	}
	
	public boolean close() {
		boolean status = false;
		
		try {
			conexao.close();
			status = true;
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}
}
