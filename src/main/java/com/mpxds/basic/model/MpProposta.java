package com.mpxds.basic.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.mpxds.basic.model.enums.MpContaTipo;
import com.mpxds.basic.model.enums.MpTipoConta;

@Entity
@Table(name="mp_proposta")
public class MpProposta extends MpEntity {
	//
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_movimento", nullable = false, length = 10)
	private Date dataMovimento;
	
	@ManyToOne
	@JoinColumn(name = "mpClienteConsignado_id")
	private MpClienteConsignado mpClienteConsignado;
	
	@ManyToOne
	@JoinColumn(name = "mpDigitador_id")
	private MpTabelaInterna mpDigitador;
	
	@ManyToOne
	@JoinColumn(name = "mpCorrespondente_id")
	private  MpTabelaInterna mpCorrespondente;
	
	@ManyToOne
	@JoinColumn(name = "mpCorretor_id")
	private MpTabelaInterna mpCorretor;
	
	@ManyToOne
	@JoinColumn(name = "mpVendedor_id")
	private MpTabelaInterna mpVendedor;
	
	@Column(name = "codigo_Token1", nullable = true, length = 100)
	private String codigoToken1;
	@Column(name = "codigo_Token2", nullable = true, length = 100)
	private String codigoToken2;
	@Column(name = "codigo_Token3", nullable = true, length = 100)
	private String codigoToken3;
	@Column(name = "codigo_Token4", nullable = true, length = 100)
	private String codigoToken4;
	@Column(name = "codigo_Token5", nullable = true, length = 100)
	private String codigoToken5;
		
	@Column(nullable = true, length = 100)
	private String margem;
	
	@OneToMany(mappedBy = "mpProposta", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<MpContrato> mpContratoList = new ArrayList<MpContrato>();
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_pagamento", nullable = true, length = 10)
	private Date dataPagamento;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_comisssao", nullable = true, length = 10)
	private Date dataComissao;
	
	@Column(name = "ind_terceiros", nullable = true)
	private Boolean indTerceiros;
	
	@Column(name = "ind_portabilidade", nullable = true)
	private Boolean indPortabilidade;
	
	@Column(name = "numero_convenio", nullable = true)
	private Integer numeroConvenio;
	
	@Column(name = "ind_rmc", nullable = true)
	private Boolean indRMC;
	
	// ---
	
	public MpProposta() {
		//
	}

  	public MpProposta(Long id, Date dataMovimento, String margem) {
  		//
  		super();
  		
  		this.id = id;
  		this.dataMovimento = dataMovimento;
  		this.margem = margem;
  	}
  	 
  	public Date getDataMovimento() { return this.dataMovimento; }
  	public void setDataMovimento(Date newDataMovimento) { this.dataMovimento = newDataMovimento; }

	public MpClienteConsignado getMpClienteConsignado() { return mpClienteConsignado; }
	public void setMpClienteConsignado(MpClienteConsignado mpClienteConsignado) { 
																	this.mpClienteConsignado = mpClienteConsignado; }
  	
	public MpTabelaInterna getMpDigitador() { return mpDigitador; }
	public void setMpDigitador(MpTabelaInterna mpDigitador) { this.mpDigitador = mpDigitador; }

	public MpTabelaInterna getMpCorrespondente() { return mpCorrespondente; }
	public void setMpCorrespondente(MpTabelaInterna mpCorrespondente) { this.mpCorrespondente = mpCorrespondente; }

	public MpTabelaInterna getMpCorretor() { return mpCorretor; }
	public void setMpCorretor(MpTabelaInterna mpCorretor) {	this.mpCorretor = mpCorretor; }

	public MpTabelaInterna getMpVendedor() { return mpVendedor; }
	public void setMpVendedor(MpTabelaInterna mpVendedor) { this.mpVendedor = mpVendedor; }

	public String getMargem() { return this.margem; }
	public void setMargem(String newMargem) { this.margem = newMargem; }

	public String getCodigoToken1() { return this.codigoToken1; }
	public void setCodigoToken1(String newCodigoToken1) { this.codigoToken1 = newCodigoToken1; }
	public String getCodigoToken2() { return this.codigoToken2; }
	public void setCodigoToken2(String newCodigoToken2) { this.codigoToken2 = newCodigoToken2; }
	public String getCodigoToken3() { return this.codigoToken3; }
	public void setCodigoToken3(String newCodigoToken3) { this.codigoToken3 = newCodigoToken3; }
	public String getCodigoToken4() { return this.codigoToken4; }
	public void setCodigoToken4(String newCodigoToken4) { this.codigoToken4 = newCodigoToken4; }
	public String getCodigoToken5() { return this.codigoToken5; }
	public void setCodigoToken5(String newCodigoToken5) { this.codigoToken5 = newCodigoToken5; }
		
	public List<MpContrato> getMpContratoList() { return mpContratoList; }
	public void setMpContratoList(List<MpContrato> mpContratoList) { this.mpContratoList = mpContratoList; }

	//
	
 	public Date getDataPagamento() { return this.dataPagamento; }
 	public void setDataPagamento(Date newDataPagamento) { this.dataPagamento = newDataPagamento; }
	
 	public Date getDataComissao() { return this.dataComissao; }
 	public void setDataComissao(Date newDataComissao) { this.dataComissao = newDataComissao; }

 	public Boolean getIndTerceiros() { return this.indTerceiros; }
	public void setIndTerceiros(Boolean newIndTerceiros) { this.indTerceiros = newIndTerceiros; }

 	public Boolean getIndPortabilidade() { return this.indPortabilidade; }
	public void setIndPortabilidade(Boolean newIndPortabilidade) { this.indPortabilidade = newIndPortabilidade; }

 	public Integer getNumeroConvenio() { return this.numeroConvenio; }
	public void setNumeroConvenio(Integer newNumeroConvenio) { this.numeroConvenio = newNumeroConvenio; }

 	public Boolean getIndRMC() { return this.indRMC; }
	public void setIndRMC(Boolean newIndRMC) { this.indRMC = newIndRMC; }
	
	//
	
	@Transient
  	public String getDataMovimentoSDF() {
  		//
		if (null == this.dataMovimento) return "__/__/____";
		
  		SimpleDateFormat sdfDMY = new SimpleDateFormat("dd/MM/yyyy");
  		
  		return sdfDMY.format(this.dataMovimento); 
  	}
	
	@Transient
  	public String getNumeroId() {
  		//
		if (null == this.id) return "";
		//
		return StringUtils.leftPad(this.id.toString(), 5, "0");
  	}
	
	@Transient
  	public MpContrato getMpContrato0() {
  		//
		if (this.mpContratoList.isEmpty())
			return new MpContrato();
		else
			return this.mpContratoList.get(0);
  	}

	// ---
		
	@Transient
  	public MpTabelaInterna getMpConvenioSel() {
  		//
//		System.out.println("getMpConvenioSel() ( 0 = " + this.id);
//		System.out.println("getMpConvenioSel() ( 1 = " + this.getNumeroConvenio());
//		System.out.println("getMpConvenioSel() ( 2 = " + this.numeroConvenio);
		
		if (null == this.getNumeroConvenio() || null == this.mpClienteConsignado) 
			return new MpTabelaInterna();
  		//
		if (this.numeroConvenio == 1)
			return this.mpClienteConsignado.getMpDadosConvenio().getMpConvenio();
		else
			if (this.numeroConvenio == 2)
				return this.mpClienteConsignado.getMpDadosConvenio().getMpConvenio1();
			else
				if (this.numeroConvenio == 3)
					return this.mpClienteConsignado.getMpDadosConvenio().getMpConvenio2();
				else
					if (this.numeroConvenio == 4)
						return this.mpClienteConsignado.getMpDadosConvenio().getMpConvenio3();
					else
						return this.mpClienteConsignado.getMpDadosConvenio().getMpConvenio4();
	}
	
	@Transient
  	public MpTabelaInterna getMpEspecieBeneficioSel() {
  		//
		if (null == this.numeroConvenio || null == this.mpClienteConsignado) 
			return new MpTabelaInterna();
  		//
		if (this.numeroConvenio == 1)
			return this.mpClienteConsignado.getMpDadosConvenio().getMpEspecieBeneficio();
		else
			if (this.numeroConvenio == 2)
				return this.mpClienteConsignado.getMpDadosConvenio().getMpEspecieBeneficio1();
			else
				if (this.numeroConvenio == 3)
					return this.mpClienteConsignado.getMpDadosConvenio().getMpEspecieBeneficio2();
				else
					if (this.numeroConvenio == 4)
						return this.mpClienteConsignado.getMpDadosConvenio().getMpEspecieBeneficio3();
					else
						return this.mpClienteConsignado.getMpDadosConvenio().getMpEspecieBeneficio4();
	}
	
	@Transient
  	public String getMpBeneficioMatriculaSel() {
  		//
		if (null == this.numeroConvenio || null == this.mpClienteConsignado) 
			return "null";
  		//
		if (this.numeroConvenio == 1)
			return this.mpClienteConsignado.getMpDadosConvenio().getBeneficioMatricula();
		else
			if (this.numeroConvenio == 2)
				return this.mpClienteConsignado.getMpDadosConvenio().getBeneficioMatricula1();
			else
				if (this.numeroConvenio == 3)
					return this.mpClienteConsignado.getMpDadosConvenio().getBeneficioMatricula2();
				else
					if (this.numeroConvenio == 4)
						return this.mpClienteConsignado.getMpDadosConvenio().getBeneficioMatricula3();
					else
						return this.mpClienteConsignado.getMpDadosConvenio().getBeneficioMatricula4();
	}
	
	//
	
	@Transient
  	public MpTabelaInterna getMpBancoSel() {
  		//
		if (null == this.numeroConvenio || null == this.mpClienteConsignado) 
			return new MpTabelaInterna();
		//
		if (this.numeroConvenio == 1)
			return this.mpClienteConsignado.getMpBanco();
		else
			if (this.numeroConvenio == 2)
				return this.mpClienteConsignado.getMpBanco1();
			else
				if (this.numeroConvenio == 3)
					return this.mpClienteConsignado.getMpBanco2();
				else
					if (this.numeroConvenio == 4)
						return this.mpClienteConsignado.getMpBanco3();
					else
						return this.mpClienteConsignado.getMpBanco4();
  	}
	
	@Transient
  	public String getMpDadosBancarioAgenciaSel() {
  		//
		if (null == this.numeroConvenio || null == this.mpClienteConsignado) 
			return "null";
		//
		if (this.numeroConvenio == 1)
			return this.mpClienteConsignado.getMpDadosBancario().getAgencia();
		else
			if (this.numeroConvenio == 2)
				return this.mpClienteConsignado.getMpDadosBancario().getAgencia1();
			else
				if (this.numeroConvenio == 3)
					return this.mpClienteConsignado.getMpDadosBancario().getAgencia2();
				else
					if (this.numeroConvenio == 4)
						return this.mpClienteConsignado.getMpDadosBancario().getAgencia3();
					else
						return this.mpClienteConsignado.getMpDadosBancario().getAgencia4();
  	}
	
	@Transient
  	public String getMpDadosBancarioContaSel() {
  		//
		if (null == this.numeroConvenio || null == this.mpClienteConsignado) 
			return "null";
		//
		if (this.numeroConvenio == 1)
			return this.mpClienteConsignado.getMpDadosBancario().getConta();
		else
			if (this.numeroConvenio == 2)
				return this.mpClienteConsignado.getMpDadosBancario().getConta1();
			else
				if (this.numeroConvenio == 3)
					return this.mpClienteConsignado.getMpDadosBancario().getConta2();
				else
					if (this.numeroConvenio == 4)
						return this.mpClienteConsignado.getMpDadosBancario().getConta3();
					else
						return this.mpClienteConsignado.getMpDadosBancario().getConta4();
  	}
	
	@Transient
  	public MpTipoConta getMpDadosBancarioTipoContaSel() {
  		//
		if (null == this.numeroConvenio || null == this.mpClienteConsignado) 
			return null;
		//
		if (this.numeroConvenio == 1)
			return this.mpClienteConsignado.getMpDadosBancario().getMpTipoConta();
		else
			if (this.numeroConvenio == 2)
				return this.mpClienteConsignado.getMpDadosBancario().getMpTipoConta1();
			else
				if (this.numeroConvenio == 3)
					return this.mpClienteConsignado.getMpDadosBancario().getMpTipoConta2();
				else
					if (this.numeroConvenio == 4)
						return this.mpClienteConsignado.getMpDadosBancario().getMpTipoConta3();
					else
						return this.mpClienteConsignado.getMpDadosBancario().getMpTipoConta4();
  	}
	
	@Transient
  	public MpContaTipo getMpDadosBancarioContaTipoSel() {
  		//
		if (null == this.numeroConvenio || null == this.mpClienteConsignado) 
			return null;
		//
		if (this.numeroConvenio == 1)
			return this.mpClienteConsignado.getMpDadosBancario().getMpContaTipo();
		else
			if (this.numeroConvenio == 2)
				return this.mpClienteConsignado.getMpDadosBancario().getMpContaTipo1();
			else
				if (this.numeroConvenio == 3)
					return this.mpClienteConsignado.getMpDadosBancario().getMpContaTipo2();
				else
					if (this.numeroConvenio == 4)
						return this.mpClienteConsignado.getMpDadosBancario().getMpContaTipo3();
					else
						return this.mpClienteConsignado.getMpDadosBancario().getMpContaTipo4();
  	}
	
}
