package br.com.gold.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;


import br.com.gold.dao.FichasDAO;
import br.com.gold.dao.UsuarioDAO;
import br.com.gold.domain.Fichas;
import br.com.gold.domain.Usuario;



@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {
	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Fichas> fichasLista;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Fichas> getFichasLista() {
		return fichasLista;
	}

	public void setFichasLista(List<Fichas> fichasLista) {
		this.fichasLista = fichasLista;
	}

	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarios = usuarioDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar listar os usuarios");
			erro.printStackTrace();
		}
	}

	public void novo() {
		try {
			usuario = new Usuario();

			FichasDAO fichasDAO = new FichasDAO();
			fichasLista = fichasDAO.listarFuncionarios();
			
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao gerar um novo usuario");
			erro.printStackTrace();
		}
	}

	public void salvar() {
		try {
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.merge(usuario);

			usuario = new Usuario();

			FichasDAO fichasDAO = new FichasDAO();
			fichasLista = fichasDAO.listarFuncionarios();

			usuarios = usuarioDAO.listar();

			Messages.addGlobalInfo("Usuario salvo com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar salvar um novo usuario");
			erro.printStackTrace();
		}
	}

	public void excluir(ActionEvent evento) {
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			usuarioDAO.excluir(usuario);

			usuarios = usuarioDAO.listar();

			Messages.addGlobalInfo("Usuario removido com sucesso");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o usuario");
			erro.printStackTrace();
		}
	}
	
	public void editar(ActionEvent evento){
		try {
			usuario = (Usuario) evento.getComponent().getAttributes().get("usuarioSelecionado");

			FichasDAO fichasDAO = new FichasDAO();
			fichasLista = fichasDAO.listarFuncionarios();
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar selecionar um usuario");
			erro.printStackTrace();
		}	
	}
}
