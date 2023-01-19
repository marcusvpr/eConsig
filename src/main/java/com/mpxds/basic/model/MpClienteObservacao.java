package com.mpxds.basic.model;

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

@Entity
@Table(name="mp_cliente_observacao")
public class MpClienteObservacao extends MpEntity {
	//
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_movimento", nullable = false, length = 10)
	private Date dataMovimento;  

	@Column(nullable = true, length = 500)
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "mpClienteConsignado_id")
	private MpClienteConsignado mpClienteConsignado;

	// ---
	
	public MpClienteObservacao() {
		//
	}

  	public MpClienteObservacao(Long id, Date dataMovimento, String observacao) {
  		//
  		super();
  		
  		this.id = id;
  		this.dataMovimento = dataMovimento;
  		this.observacao = observacao;
  	}
 
  	public Date getDataMovimento() { return this.dataMovimento; }
  	public void setDataMovimento(Date newDataMovimento) { this.dataMovimento = newDataMovimento; }
  	
	public String getObservacao() { return this.observacao; }
	public void setObservacao(String newObservacao) { this.observacao = newObservacao; }
	
	public MpClienteConsignado getMpClienteConsignado() { return mpClienteConsignado; }
	public void setMpClienteConsignado(MpClienteConsignado mpClienteConsignado) { 
																this.mpClienteConsignado = mpClienteConsignado; }

	//
	
	@Transient
  	public String getDataMovimentoSDF() {
  		//
		if (null==this.dataMovimento) return "__/__/____";
		
  		SimpleDateFormat sdfDMY = new SimpleDateFormat("dd/MM/yyyy");
  		
  		return sdfDMY.format(this.dataMovimento); 
  	}
	
}
