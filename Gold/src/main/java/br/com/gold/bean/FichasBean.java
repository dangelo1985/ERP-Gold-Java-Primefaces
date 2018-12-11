package br.com.gold.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import org.omnifaces.util.Messages;
import br.com.gold.dao.FichasDAO;
import br.com.gold.dao.LogradouroDAO;
import br.com.gold.domain.Fichas;
import br.com.gold.domain.Logradouro;



@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class FichasBean implements Serializable {
	private Fichas ficha;
	private Logradouro logradouro;
	private List<Fichas> fichas;
	private List<Logradouro> logradouros;

	   
	public Fichas getFicha() {
		return ficha;
	}

	public void setFicha(Fichas ficha) {
		this.ficha = ficha;
	}

	public List<Fichas> getFichas() {
		return fichas;
	}

	public void setFichas(List<Fichas> fichas) {
		this.fichas = fichas;
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
			FichasDAO fichasDAO = new FichasDAO();
			fichas = fichasDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar as fichas");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			ficha = new Fichas();
			
			//carregado combo com os dados da cidade
			LogradouroDAO logradouroDAO = new LogradouroDAO();
			logradouros = logradouroDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar uma nova lista");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			FichasDAO fichasDAO = new FichasDAO();
			fichasDAO.merge(ficha);

			ficha = new Fichas();

			LogradouroDAO logradouroDAO = new LogradouroDAO();
			logradouros = logradouroDAO.listar();

			fichas = fichasDAO.listar();

			Messages.addGlobalInfo("salva com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			ficha = (Fichas) evento.getComponent().getAttributes().get("fichaSelecionada");

			FichasDAO fichasDAO = new FichasDAO();
			fichasDAO.excluir(ficha);

			fichas = fichasDAO.listar();

			Messages.addGlobalInfo("removida com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			ficha = (Fichas) evento.getComponent().getAttributes().get("fichaSelecionada");

			LogradouroDAO logradouroDAO = new LogradouroDAO();
			logradouros = logradouroDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um Logradouro");
			erro.printStackTrace();
		}	
	}
}
