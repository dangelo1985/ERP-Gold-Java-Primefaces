package br.com.gold.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.gold.dao.GrupoDAO;
import br.com.gold.domain.Grupo;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class GrupoBean implements Serializable {
	private Grupo grupo;
	private List<Grupo> grupos;

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	@PostConstruct
	public void listar() {
		try {
			GrupoDAO grupoDAO = new GrupoDAO();
			grupos = grupoDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os grupos");
			erro.printStackTrace();
		}
	}

	public void novo() {
		grupo = new Grupo();
	}

	public void salvar() {
		try {
			GrupoDAO grupoDAO = new GrupoDAO();
			grupoDAO.merge(grupo);

			grupo = new Grupo();
			grupos = grupoDAO.listar();

			Messages.addGlobalInfo("Grupo salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o grupo");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			grupo = (Grupo) evento.getComponent().getAttributes().get("grupoSelecionado");

			GrupoDAO grupoDAO = new GrupoDAO();
			grupoDAO.excluir(grupo);

			grupos = grupoDAO.listar();

			Messages.addGlobalInfo("Grupo removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o grupo");
			erro.printStackTrace();
		}
	}

	public void editar(ActionEvent evento) {
		grupo = (Grupo) evento.getComponent().getAttributes().get("grupoSelecionado");
	}
}
