package br.com.gold.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.gold.domain.Cidade;
import br.com.gold.domain.Estado;

public class CidadeDAOTest {
	private EstadoDAO estadoDAO;
	private Estado estado;
	private CidadeDAO cidadeDAO;
	private Cidade cidade;
	
	@Test
	@Ignore
	public void salvar() {
		Long codigo = 25L;
		
		estadoDAO = new EstadoDAO();
		estado = new Estado(); 
		estado = estadoDAO.buscar(codigo);
		
		cidadeDAO = new CidadeDAO();
		cidade = new Cidade();
		
		cidade.setNome("Salto Grande");
		cidade.setEstado(estado);
		
		cidadeDAO.salvar(cidade);
				
		
	}
	@Test
	@Ignore
	public void listar() {
		cidadeDAO = new CidadeDAO();
		List<Cidade> resultado = cidadeDAO.listar();
		
		System.out.println("Total de registros: "+resultado.size());
		
		for(Cidade cidade : resultado) {
			System.out.println(cidade.getCodigo() + "-" 
		+ cidade.getNome() + "-" + cidade.getEstado().getNome()+ "-" + cidade.getEstado().getSigla());
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 1L;
		cidadeDAO = new CidadeDAO();
		cidade = new Cidade();
		cidade = cidadeDAO.buscar(codigo);
		
		if(cidade == null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			System.out.println("Registro encontrado:");
			System.out.println(cidade.getCodigo() + "-" 
					+ cidade.getNome() + "-" + cidade.getEstado().getNome()+ "-" + cidade.getEstado().getSigla());
		}
	}
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 2L;
		cidadeDAO = new CidadeDAO();
		cidade = new Cidade();
		cidade = cidadeDAO.buscar(codigo);
		
		if(cidade == null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			cidadeDAO.excluir(cidade);
			System.out.println("Registro Removido:");
			System.out.println(cidade.getCodigo() + "-" 
					+ cidade.getNome() + "-" + cidade.getEstado().getNome()+ "-" + cidade.getEstado().getSigla());
		}
	}

	@Test
	@Ignore
	public void editar() {
		Long codigo = 1L;
		Long codigoEstado = 15L;
		
		estadoDAO = new EstadoDAO();
		estado = new Estado(); 
		estado = estadoDAO.buscar(codigoEstado);
		
		cidadeDAO = new CidadeDAO();
		cidade = new Cidade();
		cidade = cidadeDAO.buscar(codigo);
		
		if(estado == null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			cidade.setNome("teste");
			cidade.setEstado(estado);
			cidadeDAO.editar(cidade);
			
			System.out.println("Registro editado:");
			System.out.println(cidade.getCodigo() + "-" 
					+ cidade.getNome() + "-" + cidade.getEstado().getNome()+ "-" + cidade.getEstado().getSigla());
		}
	}
}

