package com.mpxds.basic.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.mpxds.basic.model.enums.MpEstadoUF;

@Embeddable
public class MpDadosConvenio implements Serializable {
	//
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "mpConvenio_id")
	private MpTabelaInterna mpConvenio;

	@ManyToOne
	@JoinColumn(name = "mpEspecieBeneficio_id")
	private MpTabelaInterna mpEspecieBeneficio;		

	@ManyToOne
	@JoinColumn(name = "mpOrgaoConvenio_id")
	private MpTabelaInterna mpOrgaoConvenio;		
	
	@Column(name = "beneficio_matricula", nullable = true, length = 50)
	private String beneficioMatricula;

	@Column(nullable = true, length = 50)
	private String instituidor;

	@Column(nullable = true, length = 50)
	private String senha;
	
	@Column(name = "valor_mr", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorMR;
	
	@Column(name = "valor_ir", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorIR;
	
	@Column(name = "valor_seguro", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorSeguro;
	
	@Column(name = "valor_pensao", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorPensao;

	// ---- 1 ----
	
	@ManyToOne
	@JoinColumn(name = "mpConvenio1_id")
	private MpTabelaInterna mpConvenio1;

	@ManyToOne
	@JoinColumn(name = "mpEspecieBeneficio1_id")
	private MpTabelaInterna mpEspecieBeneficio1;		

	@ManyToOne
	@JoinColumn(name = "mpOrgaoConvenio1_id")
	private MpTabelaInterna mpOrgaoConvenio1;		
	
	@Column(name = "beneficio_matricula1", nullable = true, length = 50)
	private String beneficioMatricula1;

	@Column(nullable = true, length = 50)
	private String instituidor1;

	@Column(nullable = true, length = 50)
	private String senha1;
	
	@Column(name = "valor_mr1", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorMR1;
	
	@Column(name = "valor_ir1", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorIR1;
	
	@Column(name = "valor_seguro1", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorSeguro1;
	
	@Column(name = "valor_pensao1", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorPensao1;

	// ---- 2 ----
	
	@ManyToOne
	@JoinColumn(name = "mpConvenio2_id")
	private MpTabelaInterna mpConvenio2;

	@ManyToOne
	@JoinColumn(name = "mpEspecieBeneficio2_id")
	private MpTabelaInterna mpEspecieBeneficio2;		

	@ManyToOne
	@JoinColumn(name = "mpOrgaoConvenio2_id")
	private MpTabelaInterna mpOrgaoConvenio2;		
	
	@Column(name = "beneficio_matricula2", nullable = true, length = 50)
	private String beneficioMatricula2;

	@Column(nullable = true, length = 50)
	private String instituidor2;

	@Column(nullable = true, length = 50)
	private String senha2;
	
	@Column(name = "valor_mr2", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorMR2;
	
	@Column(name = "valor_ir2", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorIR2;
	
	@Column(name = "valor_seguro2", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorSeguro2;
	
	@Column(name = "valor_pensao2", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorPensao2;
	
	// ---- 3 ----
	
	@ManyToOne
	@JoinColumn(name = "mpConvenio3_id")
	private MpTabelaInterna mpConvenio3;

	@ManyToOne
	@JoinColumn(name = "mpEspecieBeneficio3_id")
	private MpTabelaInterna mpEspecieBeneficio3;		

	@ManyToOne
	@JoinColumn(name = "mpOrgaoConvenio3_id")
	private MpTabelaInterna mpOrgaoConvenio3;		
	
	@Column(name = "beneficio_matricula3", nullable = true, length = 50)
	private String beneficioMatricula3;

	@Column(nullable = true, length = 50)
	private String instituidor3;

	@Column(nullable = true, length = 50)
	private String senha3;
	
	@Column(name = "valor_mr3", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorMR3;
	
	@Column(name = "valor_ir3", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorIR3;
	
	@Column(name = "valor_seguro3", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorSeguro3;
	
	@Column(name = "valor_pensao3", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorPensao3;
	
	// ---- 4 ----
	
	@ManyToOne
	@JoinColumn(name = "mpConvenio4_id")
	private MpTabelaInterna mpConvenio4;

	@ManyToOne
	@JoinColumn(name = "mpEspecieBeneficio4_id")
	private MpTabelaInterna mpEspecieBeneficio4;		

	@ManyToOne
	@JoinColumn(name = "mpOrgaoConvenio4_id")
	private MpTabelaInterna mpOrgaoConvenio4;		
	
	@Column(name = "beneficio_matricula4", nullable = true, length = 50)
	private String beneficioMatricula4;

	@Column(nullable = true, length = 50)
	private String instituidor4;

	@Column(nullable = true, length = 50)
	private String senha4;
	
	@Column(name = "valor_mr4", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorMR4;
	
	@Column(name = "valor_ir4", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorIR4;
	
	@Column(name = "valor_seguro4", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorSeguro4;
	
	@Column(name = "valor_pensao4", nullable = true, precision = 10, scale = 2)
	private BigDecimal valorPensao4;
		
	// ----
	
	@Enumerated(EnumType.STRING)
	@Column(name = "convenio_uf", nullable = true, length = 2)
	private MpEstadoUF mpConvenioEstadoUF;

	@Enumerated(EnumType.STRING)
	@Column(name = "convenio_uf1", nullable = true, length = 2)
	private MpEstadoUF mpConvenioEstadoUF1;

	@Enumerated(EnumType.STRING)
	@Column(name = "convenio_uf2", nullable = true, length = 2)
	private MpEstadoUF mpConvenioEstadoUF2;

	@Enumerated(EnumType.STRING)
	@Column(name = "convenio_uf3", nullable = true, length = 2)
	private MpEstadoUF mpConvenioEstadoUF3;

	@Enumerated(EnumType.STRING)
	@Column(name = "convenio_uf4", nullable = true, length = 2)
	private MpEstadoUF mpConvenioEstadoUF4;
	
	// ---

	public MpTabelaInterna getMpConvenio() { return mpConvenio; }
	public void setMpConvenio(MpTabelaInterna mpConvenio) { this.mpConvenio = mpConvenio; }

	public MpTabelaInterna getMpOrgaoConvenio() { return mpOrgaoConvenio; }
	public void setMpOrgaoConvenio(MpTabelaInterna mpOrgaoConvenio) { this.mpOrgaoConvenio = mpOrgaoConvenio; }

	public MpTabelaInterna getMpEspecieBeneficio() { return mpEspecieBeneficio; }
	public void setMpEspecieBeneficio(MpTabelaInterna mpEspecieBeneficio) { 
																this.mpEspecieBeneficio = mpEspecieBeneficio; }

	public String getBeneficioMatricula() { return beneficioMatricula; }
	public void setBeneficioMatricula(String beneficioMatricula) { this.beneficioMatricula = beneficioMatricula; }

	public String getInstituidor() { return instituidor; }
	public void setInstituidor(String instituidor) { this.instituidor = instituidor; }

	public String getSenha() { return senha; }
	public void setSenha(String senha) { this.senha = senha; }
	  	
	public BigDecimal getValorMR() { return this.valorMR; }
	public void setValorMR(BigDecimal newValorMR) { this.valorMR = newValorMR; }
  	
	public BigDecimal getValorIR() { return this.valorIR; }
	public void setValorIR(BigDecimal newValorIR) { this.valorIR = newValorIR; }
		
	public BigDecimal getValorSeguro() { return this.valorSeguro; }
	public void setValorSeguro(BigDecimal newValorSeguro) { this.valorSeguro = newValorSeguro; }
		
	public BigDecimal getValorPensao() { return this.valorPensao; }
	public void setValorPensao(BigDecimal newValorPensao) { this.valorPensao = newValorPensao; }

	// --- 1
	
	public MpTabelaInterna getMpConvenio1() { return mpConvenio1; }
	public void setMpConvenio1(MpTabelaInterna mpConvenio1) { this.mpConvenio1 = mpConvenio1; }

	public MpTabelaInterna getMpOrgaoConvenio1() { return mpOrgaoConvenio1; }
	public void setMpOrgaoConvenio1(MpTabelaInterna mpOrgaoConvenio1) { this.mpOrgaoConvenio1 = mpOrgaoConvenio1; }

	public MpTabelaInterna getMpEspecieBeneficio1() { return mpEspecieBeneficio1; }
	public void setMpEspecieBeneficio1(MpTabelaInterna mpEspecieBeneficio1) { 
																this.mpEspecieBeneficio1 = mpEspecieBeneficio1; }

	public String getBeneficioMatricula1() { return beneficioMatricula1; }
	public void setBeneficioMatricula1(String beneficioMatricula1) { this.beneficioMatricula1 = beneficioMatricula1; }

	public String getInstituidor1() { return instituidor1; }
	public void setInstituidor1(String instituidor1) { this.instituidor1 = instituidor1; }

	public String getSenha1() { return senha1; }
	public void setSenha1(String senha1) { this.senha1 = senha1; }
  	
	public BigDecimal getValorMR1() { return this.valorMR1; }
	public void setValorMR1(BigDecimal newValorMR1) { this.valorMR1 = newValorMR1; }
		
	public BigDecimal getValorIR1() { return this.valorIR1; }
	public void setValorIR1(BigDecimal newValorIR1) { this.valorIR1 = newValorIR1; }
		
	public BigDecimal getValorSeguro1() { return this.valorSeguro1; }
	public void setValorSeguro1(BigDecimal newValorSeguro1) { this.valorSeguro1 = newValorSeguro1; }
		
	public BigDecimal getValorPensao1() { return this.valorPensao1; }
	public void setValorPensao1(BigDecimal newValorPensao1) { this.valorPensao1 = newValorPensao1; }

	// --- 2
	
	public MpTabelaInterna getMpConvenio2() { return mpConvenio2; }
	public void setMpConvenio2(MpTabelaInterna mpConvenio2) { this.mpConvenio2 = mpConvenio2; }

	public MpTabelaInterna getMpOrgaoConvenio2() { return mpOrgaoConvenio2; }
	public void setMpOrgaoConvenio2(MpTabelaInterna mpOrgaoConvenio2) { this.mpOrgaoConvenio2 = mpOrgaoConvenio2; }

	public MpTabelaInterna getMpEspecieBeneficio2() { return mpEspecieBeneficio2; }
	public void setMpEspecieBeneficio2(MpTabelaInterna mpEspecieBeneficio2) { 
																this.mpEspecieBeneficio2 = mpEspecieBeneficio2; }

	public String getBeneficioMatricula2() { return beneficioMatricula2; }
	public void setBeneficioMatricula2(String beneficioMatricula2) { this.beneficioMatricula2 = beneficioMatricula2; }

	public String getInstituidor2() { return instituidor2; }
	public void setInstituidor2(String instituidor2) { this.instituidor2 = instituidor2; }

	public String getSenha2() { return senha2; }
	public void setSenha2(String senha2) { this.senha2 = senha2; }
  	
	public BigDecimal getValorMR2() { return this.valorMR2; }
	public void setValorMR2(BigDecimal newValorMR2) { this.valorMR2 = newValorMR2; }
		
	public BigDecimal getValorIR2() { return this.valorIR2; }
	public void setValorIR2(BigDecimal newValorIR2) { this.valorIR2 = newValorIR2; }
		
	public BigDecimal getValorSeguro2() { return this.valorSeguro2; }
	public void setValorSeguro2(BigDecimal newValorSeguro2) { this.valorSeguro2 = newValorSeguro2; }
		
	public BigDecimal getValorPensao2() { return this.valorPensao2; }
	public void setValorPensao2(BigDecimal newValorPensao2) { this.valorPensao2 = newValorPensao2; }

	// --- 3
	
	public MpTabelaInterna getMpConvenio3() { return mpConvenio3; }
	public void setMpConvenio3(MpTabelaInterna mpConvenio3) { this.mpConvenio3 = mpConvenio3; }

	public MpTabelaInterna getMpOrgaoConvenio3() { return mpOrgaoConvenio3; }
	public void setMpOrgaoConvenio3(MpTabelaInterna mpOrgaoConvenio3) { this.mpOrgaoConvenio3 = mpOrgaoConvenio3; }

	public MpTabelaInterna getMpEspecieBeneficio3() { return mpEspecieBeneficio3; }
	public void setMpEspecieBeneficio3(MpTabelaInterna mpEspecieBeneficio3) { 
																this.mpEspecieBeneficio3 = mpEspecieBeneficio3; }

	public String getBeneficioMatricula3() { return beneficioMatricula3; }
	public void setBeneficioMatricula3(String beneficioMatricula3) { this.beneficioMatricula3 = beneficioMatricula3; }

	public String getInstituidor3() { return instituidor3; }
	public void setInstituidor3(String instituidor3) { this.instituidor3 = instituidor3; }

	public String getSenha3() { return senha3; }
	public void setSenha3(String senha3) { this.senha3 = senha3; }
  	
	public BigDecimal getValorMR3() { return this.valorMR3; }
	public void setValorMR3(BigDecimal newValorMR3) { this.valorMR3 = newValorMR3; }
		
	public BigDecimal getValorIR3() { return this.valorIR3; }
	public void setValorIR3(BigDecimal newValorIR3) { this.valorIR3 = newValorIR3; }
		
	public BigDecimal getValorSeguro3() { return this.valorSeguro3; }
	public void setValorSeguro3(BigDecimal newValorSeguro3) { this.valorSeguro3 = newValorSeguro3; }
		
	public BigDecimal getValorPensao3() { return this.valorPensao3; }
	public void setValorPensao3(BigDecimal newValorPensao3) { this.valorPensao3 = newValorPensao3; }

	// --- 4
	
	public MpTabelaInterna getMpConvenio4() { return mpConvenio4; }
	public void setMpConvenio4(MpTabelaInterna mpConvenio4) { this.mpConvenio4 = mpConvenio4; }

	public MpTabelaInterna getMpOrgaoConvenio4() { return mpOrgaoConvenio4; }
	public void setMpOrgaoConvenio4(MpTabelaInterna mpOrgaoConvenio4) { this.mpOrgaoConvenio4 = mpOrgaoConvenio4; }

	public MpTabelaInterna getMpEspecieBeneficio4() { return mpEspecieBeneficio4; }
	public void setMpEspecieBeneficio4(MpTabelaInterna mpEspecieBeneficio4) { 
																this.mpEspecieBeneficio4 = mpEspecieBeneficio4; }

	public String getBeneficioMatricula4() { return beneficioMatricula4; }
	public void setBeneficioMatricula4(String beneficioMatricula4) { this.beneficioMatricula4 = beneficioMatricula4; }

	public String getInstituidor4() { return instituidor4; }
	public void setInstituidor4(String instituidor4) { this.instituidor4 = instituidor4; }

	public String getSenha4() { return senha4; }
	public void setSenha4(String senha4) { this.senha4 = senha4; }
  	
	public BigDecimal getValorMR4() { return this.valorMR4; }
	public void setValorMR4(BigDecimal newValorMR4) { this.valorMR4 = newValorMR4; }
		
	public BigDecimal getValorIR4() { return this.valorIR4; }
	public void setValorIR4(BigDecimal newValorIR4) { this.valorIR4 = newValorIR4; }
		
	public BigDecimal getValorSeguro4() { return this.valorSeguro4; }
	public void setValorSeguro4(BigDecimal newValorSeguro4) { this.valorSeguro4 = newValorSeguro4; }
		
	public BigDecimal getValorPensao4() { return this.valorPensao4; }
	public void setValorPensao4(BigDecimal newValorPensao4) { this.valorPensao4 = newValorPensao4; }

	// ----
	
	public MpEstadoUF getMpConvenioEstadoUF() { return mpConvenioEstadoUF; }
	public void setMpConvenioEstadoUF(MpEstadoUF mpConvenioEstadoUF) { this.mpConvenioEstadoUF = mpConvenioEstadoUF; }
	
	public MpEstadoUF getMpConvenioEstadoUF1() { return mpConvenioEstadoUF1; }
	public void setMpConvenioEstadoUF1(MpEstadoUF mpConvenioEstadoUF1) { this.mpConvenioEstadoUF1 = mpConvenioEstadoUF1; }
	
	public MpEstadoUF getMpConvenioEstadoUF2() { return mpConvenioEstadoUF2; }
	public void setMpConvenioEstadoUF2(MpEstadoUF mpConvenioEstadoUF2) { this.mpConvenioEstadoUF2 = mpConvenioEstadoUF2; }
	
	public MpEstadoUF getMpConvenioEstadoUF3() { return mpConvenioEstadoUF3; }
	public void setMpConvenioEstadoUF3(MpEstadoUF mpConvenioEstadoUF3) { this.mpConvenioEstadoUF3 = mpConvenioEstadoUF3; }
	
	public MpEstadoUF getMpConvenioEstadoUF4() { return mpConvenioEstadoUF4; }
	public void setMpConvenioEstadoUF4(MpEstadoUF mpConvenioEstadoUF4) { this.mpConvenioEstadoUF4 = mpConvenioEstadoUF4; }

	//
	
	@Transient
  	public Integer getNumeroConvenio() {
  		//
		Integer numeroConvenio = 0;
		
		if (null == this.mpConvenio  || null == this.mpConvenio.getCodigo())  assert(true); else numeroConvenio++;
		if (null == this.mpConvenio1 || null == this.mpConvenio1.getCodigo()) assert(true); else numeroConvenio++;
		if (null == this.mpConvenio2 || null == this.mpConvenio2.getCodigo()) assert(true); else numeroConvenio++;
		if (null == this.mpConvenio3 || null == this.mpConvenio3.getCodigo()) assert(true); else numeroConvenio++;
		if (null == this.mpConvenio4 || null == this.mpConvenio4.getCodigo()) assert(true); else numeroConvenio++;
  		//
  		return numeroConvenio; 
  	}
	
	@Transient
  	public String getConvenio(Integer numeroConvenio) {
  		//
		if (null == numeroConvenio || numeroConvenio == 0) return "";
		//
		String convenio = "";
		//
		if (numeroConvenio == 1) convenio = this.mpConvenio.getDescricao();
		if (numeroConvenio == 2) convenio = this.mpConvenio1.getDescricao();
		if (numeroConvenio == 3) convenio = this.mpConvenio2.getDescricao();
		if (numeroConvenio == 4) convenio = this.mpConvenio3.getDescricao();
		if (numeroConvenio == 5) convenio = this.mpConvenio4.getDescricao();
		//
		return convenio;
	}
	
}
