package com.ddvitos.app.comexportbackend.shared.dto;

import java.io.Serializable;
import java.util.List;

public class ContaContabilDTO implements Serializable {

	private static final long serialVersionUID = -4887746819895541926L;
	private long id;
	private String contaContabilId;
	private String numero;
	private String descricao;
	private List<LancamentoContabilDTO> lancamentos;

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

	public List<LancamentoContabilDTO> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<LancamentoContabilDTO> lancamentos) {
		this.lancamentos = lancamentos;
	}

}
