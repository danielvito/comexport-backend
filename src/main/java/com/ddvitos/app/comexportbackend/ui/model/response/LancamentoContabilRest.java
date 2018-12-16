package com.ddvitos.app.comexportbackend.ui.model.response;

import java.math.BigDecimal;
import java.util.Date;

public class LancamentoContabilRest {

	private String contaContabil;
	private Date data;
	private BigDecimal valor;

	public String getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(String contaContabil) {
		this.contaContabil = contaContabil;
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

}
