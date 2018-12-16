package com.ddvitos.app.comexportbackend.io.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "contacontabil")
public class ContaContabilEntity implements Serializable {

	private static final long serialVersionUID = -8829759534050098133L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String contaContabilId;

	@Column(nullable = false)
	private String numero;

	@Column(nullable = false, length = 250)
	private String descricao;

	@OneToMany(mappedBy = "contaContabil", cascade = CascadeType.ALL)
	private List<LancamentoContabilEntity> lancamentos;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContaContabilId() {
		return contaContabilId;
	}

	public void setContaContabilId(String contaContabilId) {
		this.contaContabilId = contaContabilId;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<LancamentoContabilEntity> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<LancamentoContabilEntity> lancamentos) {
		this.lancamentos = lancamentos;
	}

}
