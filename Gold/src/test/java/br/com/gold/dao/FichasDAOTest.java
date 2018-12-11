package br.com.gold.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;


import br.com.gold.domain.Fichas;
import br.com.gold.domain.Logradouro;

public class FichasDAOTest {
	private Logradouro logradouro;
	private LogradouroDAO logradouroDAO;
	private Fichas ficha;
	private FichasDAO fichasDAO;

	
	@Test
	@Ignore
	public void salvar() {
		Long codigo = 1L;
		
		logradouroDAO = new LogradouroDAO();
		logradouro = new Logradouro();
		logradouro = logradouroDAO.buscar(codigo);

		
		fichasDAO = new FichasDAO();
		ficha = new Fichas();
		ficha.setNome("Rafael");
		ficha.setTipo('F');
		ficha.setTipoFicha("Funcionario");
		ficha.setNomeFantasia("Dangelo");
		ficha.setLogradouro(logradouro);
		ficha.setNumero(151);
		ficha.setComplemento("Fundos");
		ficha.setTelefone("3322-6822");
		ficha.setCpfCnpj("41.984.426-0");
		ficha.setRgIe("229.991.068-90");
		ficha.setEmail("dangelo_1985@hotmail.com");
		fichasDAO.salvar(ficha);
	}
	
	@Test
	@Ignore
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
	@Test
	public void listar() {
		fichasDAO = new FichasDAO();
		List<Fichas> resultado = fichasDAO.listarFuncionarios();
		
		System.out.println("Total de registros: "+resultado.size());
		
		for(Fichas fichas : resultado) {
			System.out.println(fichas.getNome() + "-" 
		+ fichas.getTipoFicha());
		}
	}
}
