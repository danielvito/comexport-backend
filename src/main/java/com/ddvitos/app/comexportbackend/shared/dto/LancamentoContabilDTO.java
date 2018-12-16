package com.ddvitos.app.comexportbackend.shared.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LancamentoContabilDTO implements Serializable {

	private static final long serialVersionUID = -5156226727227855875L;

	private long id;
	private String lancamentoContabilId;
	private Date data;
	private BigDecimal valor;
	private ContaContabilDTO contaContabil;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLancamentoContabilId() {
		return lancamentoContabilId;
	}

	public void setLancamentoContabilId(String lancamentoContabilId) {
		this.lancamentoContabilId = lancamentoContabilId;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public ContaContabilDTO getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(ContaContabilDTO contaContabil) {
		this.contaContabil = contaContabil;
	}

}
