package com.ddvitos.app.comexportbackend.ui.model.response;

import java.math.BigDecimal;
import java.util.Date;

public class LancamentoContabilRest {

	private String contaContabtil;
	private Date data;
	private BigDecimal valor;

	public String getContaContabtil() {
		return contaContabtil;
	}

	public void setContaContabtil(String contaContabtil) {
		this.contaContabtil = contaContabtil;
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
