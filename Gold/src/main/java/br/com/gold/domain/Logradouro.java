package br.com.gold.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Logradouro extends GenericDomain{
	@Column(length = 20, nullable = false)
	private String descricao;
	
	@Column(length = 100, nullable = false)
	private String rua;
	
	@Column(length = 50, nullable = false)
	private String Bairro;
	
	@Column(length = 10, nullable = false)
	private String cep;
	
	//chave estrangeira Muitos para um
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cidade cidade;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return Bairro;
	}
	public void setBairro(String bairro) {
		Bairro = bairro;
	}
	public Cidade getCidade() {
		return cidade;
	}
	
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	
}
