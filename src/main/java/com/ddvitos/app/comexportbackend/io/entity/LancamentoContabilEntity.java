package com.ddvitos.app.comexportbackend.io.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "lancamentocontabil")
public class LancamentoContabilEntity implements Serializable {

	private static final long serialVersionUID = 6696920620437504573L;

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false)
	private String lancamentoContabilId;

	@Column(nullable = true)
	private Date data;

	@Column(nullable = false)
	private BigDecimal valor;

	@ManyToOne
	@JoinColumn(name = "contaContabil")
	private ContaContabilEntity contaContabil;

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

	public ContaContabilEntity getContaContabil() {
		return contaContabil;
	}

	public void setContaContabil(ContaContabilEntity contaContabil) {
		this.contaContabil = contaContabil;
	}

}
