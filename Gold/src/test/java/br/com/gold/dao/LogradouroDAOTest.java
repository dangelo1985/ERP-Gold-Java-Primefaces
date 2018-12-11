package br.com.gold.dao;

import org.junit.Ignore;
import org.junit.Test;

import br.com.gold.domain.Cidade;
import br.com.gold.domain.Logradouro;

public class LogradouroDAOTest {
	private Logradouro logradouro;
	private LogradouroDAO logradouroDAO;
	private Cidade cidade;
	private CidadeDAO cidadeDAO;

	
	@Test
	@Ignore
	public void salvar() {
		Long codigo = 1L;
		
		cidadeDAO = new CidadeDAO();
		cidade = new Cidade();
		cidade = cidadeDAO.buscar(codigo);

		
		logradouroDAO = new LogradouroDAO();
		logradouro = new Logradouro();
		logradouro.setDescricao("Rua");
		logradouro.setRua("São Paulo");
		logradouro.setCep("19900051");
		logradouro.setBairro("Centro");
		logradouro.setCidade(cidade);

		logradouroDAO.salvar(logradouro);
	}
	
	@Test
	
	public void buscarCep() {
		String cep = "19900051";
		logradouroDAO = new LogradouroDAO();
		logradouro = new Logradouro();
		logradouro = logradouroDAO.buscarCep(cep);
		
		if(logradouro == null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			System.out.println("Registro encontrado:");
			System.out.println(logradouro.getCodigo() + "-" 
					+ logradouro.getDescricao() + "-" + logradouro.getRua()+ "-" + logradouro.getCidade().getNome());
		}
	}
}
