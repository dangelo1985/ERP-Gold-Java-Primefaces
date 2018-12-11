package br.com.gold.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.gold.domain.Estado;

public class EstadoDAOTest {
	Estado estado = new Estado();
	EstadoDAO estadoDAO = new EstadoDAO();
	
	@Test
	@Ignore
	public void salvar() {
	   String estados[] = {
			"Acre","Alagoas","Amapá","Amazonas","Bahia","Ceará","Distrito Federal","Espírito Santo" 
			
			,"Goiás","Maranhão","Mato Grosso","Mato Grosso do Sul","Minas Gerais","Pará","Paraíba"
			,"Paraná","Pernambuco","Piauí","Rio de Janeiro","Rio Grande do Norte","Rio Grande do Sul"
			,"Rondônia","Roraima","Santa Catarina","São Paulo","Sergipe","Tocantins"
		};
	   String siglas[] = {
				"AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB",
				"PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"
		};
	   	for(int i = 0; i < estados.length;i++ ) {
	   		estado.setNome(estados[i]);
			estado.setSigla(siglas[i]);
			
			estadoDAO.salvar(estado);
	   	}
		
	}
	@Test
	@Ignore
	public void listar() {
		estadoDAO = new EstadoDAO();
		List<Estado> resultado = estadoDAO.listar();
		
		System.out.println("Total de registros: "+resultado.size());
		
		for(Estado estado : resultado) {
			System.out.println(estado.getCodigo() + "-" 
		+ estado.getNome() + "-" + estado.getSigla());
		}
	}
	@Test
	@Ignore
	public void buscar() {
		Long codigo = 3L;
		estadoDAO = new EstadoDAO();
		estado = estadoDAO.buscar(codigo);
		
		if(estado == null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			System.out.println("Registro encontrado:");
			System.out.println(estado.getCodigo()+ "-" + estado.getNome() + "-" + estado.getSigla() );
		}
	}
	@Test
	@Ignore
	public void excluir() {
		Long codigo = 1L;
		estadoDAO = new EstadoDAO();
		estado = estadoDAO.buscar(codigo);
		
		if(estado == null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			estadoDAO.excluir(estado);
			System.out.println("Registro Removido:");
			System.out.println(estado.getCodigo()+ "-" + estado.getNome() + "-" + estado.getSigla() );
		}
	}
	
	@Test
	@Ignore
	public void editar() {
		Long codigo = 2L;
		estadoDAO = new EstadoDAO();
		estado = estadoDAO.buscar(codigo);
		
		if(estado == null) {
			System.out.println("Nenhum registro encontrado!");
		}else {
			estado.setNome("teste");
			estado.setSigla("TT");
			estadoDAO.editar(estado);
			
			System.out.println("Registro editado:");
			System.out.println(estado.getCodigo()+ "-" + estado.getNome() + "-" + estado.getSigla() );
		}
	}
	
}
