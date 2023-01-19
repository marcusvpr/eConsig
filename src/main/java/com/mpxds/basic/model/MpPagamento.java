package com.mpxds.basic.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="mp_pagamento")
public class MpPagamento extends MpEntity {
	//
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_movimento", nullable = false, length = 10)
	private Date dataMovimento;

	@ManyToOne
	@JoinColumn(name = "mpContrato_id")
	private MpContrato mpContrato;
	
	@ManyToOne
	@JoinColumn(name = "mpPagamentoTipo_id")
	private MpTabelaInterna mpPagamentoTipo; 
	
	@ManyToOne
	@JoinColumn(name = "mpPagamentoStatus_id")
	private MpTabelaInterna mpPagamentoStatus; 
	
	@NotNull(message = "Valor é obrigatório")
	@Column(name = "valor", nullable = false, precision = 10, scale = 2)
	private BigDecimal valor;
	
	// ---
	
	public MpPagamento() {
		//
	}
  	 
  	public Date getDataMovimento() { return this.dataMovimento; }
  	public void setDataMovimento(Date newDataMovimento) { this.dataMovimento = newDataMovimento; }

	public MpContrato getMpContrato() { return mpContrato; }
	public void setMpContrato(MpContrato mpContrato) { this.mpContrato = mpContrato; }
  	
	public MpTabelaInterna getMpPagamentoTipo() { return mpPagamentoTipo; }
	public void setMpPagamentoTipo(MpTabelaInterna mpPagamentoTipo) { this.mpPagamentoTipo = mpPagamentoTipo; }
  	
	public MpTabelaInterna getMpPagamentoStatus() { return mpPagamentoStatus; }
	public void setMpPagamentoStatus(MpTabelaInterna mpPagamentoStatus) { this.mpPagamentoStatus = mpPagamentoStatus; }
  	
	public BigDecimal getValor() { return this.valor; }
	public void setValor(BigDecimal newValor) { this.valor = newValor; }

	//
	
	@Transient
  	public String getDataMovimentoSDF() {
  		//
		if (null==this.dataMovimento) return "__/__/____";
		
  		SimpleDateFormat sdfDMY = new SimpleDateFormat("dd/MM/yyyy");
  		
  		return sdfDMY.format(this.dataMovimento); 
  	}
	
}
