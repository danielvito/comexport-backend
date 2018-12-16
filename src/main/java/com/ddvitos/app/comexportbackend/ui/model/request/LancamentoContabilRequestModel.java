package com.ddvitos.app.comexportbackend.ui.model.request;

import java.math.BigDecimal;
import java.util.Date;

import com.ddvitos.app.comexportbackend.shared.MyDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class LancamentoContabilRequestModel {

	private String contaContabil;
	@JsonDeserialize(using = MyDateDeserializer.class)
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
