package br.com.gold.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.gold.dao.CidadeDAO;
import br.com.gold.dao.LogradouroDAO;
import br.com.gold.domain.Cidade;
import br.com.gold.domain.Logradouro;



@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class LogradouroBean implements Serializable {
	private Cidade cidade;
	private Logradouro logradouro;
	private List<Cidade> cidades;
	private List<Logradouro> logradouros;

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}
    
	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public List<Logradouro> getLogradouros() {
		return logradouros;
	}

	public void setLogradouros(List<Logradouro> logradouros) {
		this.logradouros = logradouros;
	}

	@PostConstruct
	public void listar() {
		try {
			LogradouroDAO logradouroDAO = new LogradouroDAO();
			logradouros = logradouroDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os logradouros");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			logradouro = new Logradouro();
			
			//carregado combo com os dados da cidade
			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar um novo Logradouro");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			LogradouroDAO logradouroDAO = new LogradouroDAO();
			logradouroDAO.merge(logradouro);

			logradouro = new Logradouro();

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();

			logradouros = logradouroDAO.listar();

			Messages.addGlobalInfo("Logradouro salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo Logradouro");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			logradouro = (Logradouro) evento.getComponent().getAttributes().get("logradouroSelecionado");

			LogradouroDAO logradouroDAO = new LogradouroDAO();
			logradouroDAO.excluir(logradouro);

			logradouros = logradouroDAO.listar();

			Messages.addGlobalInfo("Logradouro removida com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o Logradouro");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			logradouro = (Logradouro) evento.getComponent().getAttributes().get("logradouroSelecionado");

			CidadeDAO cidadeDAO = new CidadeDAO();
			cidades = cidadeDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um Logradouro");
			erro.printStackTrace();
		}	
	}
}
