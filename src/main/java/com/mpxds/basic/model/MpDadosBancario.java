package com.mpxds.basic.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.mpxds.basic.model.enums.MpContaTipo;
import com.mpxds.basic.model.enums.MpTipoConta;

@Embeddable
public class MpDadosBancario implements Serializable {
	//
	private static final long serialVersionUID = 1L;

	@Column(nullable = true, length = 50)
	private String agencia;

	@Column(nullable = true, length = 20)
	private String conta;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 15)
	private MpContaTipo mpContaTipo;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 15)
	private MpTipoConta mpTipoConta;

	// 1 ---

	@Column(nullable = true, length = 50)
	private String agencia1;

	@Column(nullable = true, length = 20)
	private String conta1;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 15)
	private MpContaTipo mpContaTipo1;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 15)
	private MpTipoConta mpTipoConta1;

	// 2 ---

	@Column(nullable = true, length = 50)
	private String agencia2;

	@Column(nullable = true, length = 20)
	private String conta2;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 15)
	private MpContaTipo mpContaTipo2;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 15)
	private MpTipoConta mpTipoConta2;

	// 3 ---

	@Column(nullable = true, length = 50)
	private String agencia3;

	@Column(nullable = true, length = 20)
	private String conta3;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 15)
	private MpContaTipo mpContaTipo3;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 15)
	private MpTipoConta mpTipoConta3;

	// 4 ---

	@Column(nullable = true, length = 50)
	private String agencia4;

	@Column(nullable = true, length = 20)
	private String conta4;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 15)
	private MpContaTipo mpContaTipo4;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 15)
	private MpTipoConta mpTipoConta4;
	
	// ---

	public String getAgencia() { return agencia; }
	public void setAgencia(String agencia) { this.agencia = agencia; }

	public String getConta() { return conta; }
	public void setConta(String conta) { this.conta = conta; }
	
	public MpContaTipo getMpContaTipo() { return mpContaTipo; 	}
	public void setMpContaTipo(MpContaTipo mpContaTipo) { this.mpContaTipo = mpContaTipo; }
	
	public MpTipoConta getMpTipoConta() { return mpTipoConta; 	}
	public void setMpTipoConta(MpTipoConta mpTipoConta) { this.mpTipoConta = mpTipoConta; }
	
	// 1 ---

	public String getAgencia1() { return agencia1; }
	public void setAgencia1(String agencia1) { this.agencia1 = agencia1; }

	public String getConta1() { return conta1; }
	public void setConta1(String conta1) { this.conta1 = conta1; }
	
	public MpContaTipo getMpContaTipo1() { return mpContaTipo1; }
	public void setMpContaTipo1(MpContaTipo mpContaTipo1) { this.mpContaTipo1 = mpContaTipo1; }
	
	public MpTipoConta getMpTipoConta1() { return mpTipoConta1; }
	public void setMpTipoConta1(MpTipoConta mpTipoConta1) { this.mpTipoConta1 = mpTipoConta1; }
	
	// 2 ---

	public String getAgencia2() { return agencia2; }
	public void setAgencia2(String agencia2) { this.agencia2 = agencia2; }

	public String getConta2() { return conta2; }
	public void setConta2(String conta2) { this.conta2 = conta2; }
	
	public MpContaTipo getMpContaTipo2() { return mpContaTipo2; }
	public void setMpContaTipo2(MpContaTipo mpContaTipo2) { this.mpContaTipo2 = mpContaTipo2; }
	
	public MpTipoConta getMpTipoConta2() { return mpTipoConta2; }
	public void setMpTipoConta2(MpTipoConta mpTipoConta2) { this.mpTipoConta2 = mpTipoConta2; }
	
	// 3 ---

	public String getAgencia3() { return agencia3; }
	public void setAgencia3(String agencia3) { this.agencia3 = agencia3; }

	public String getConta3() { return conta3; }
	public void setConta3(String conta3) { this.conta3 = conta3; }
	
	public MpContaTipo getMpContaTipo3() { return mpContaTipo3; }
	public void setMpContaTipo3(MpContaTipo mpContaTipo3) { this.mpContaTipo3 = mpContaTipo3; }
	
	public MpTipoConta getMpTipoConta3() { return mpTipoConta3; }
	public void setMpTipoConta3(MpTipoConta mpTipoConta3) { this.mpTipoConta3 = mpTipoConta3; }
	
	// 4 ---

	public String getAgencia4() { return agencia4; }
	public void setAgencia4(String agencia4) { this.agencia4 = agencia4; }

	public String getConta4() { return conta4; }
	public void setConta4(String conta4) { this.conta4 = conta4; }
	
	public MpContaTipo getMpContaTipo4() { return mpContaTipo4; }
	public void setMpContaTipo4(MpContaTipo mpContaTipo4) { this.mpContaTipo4 = mpContaTipo4; }
	
	public MpTipoConta getMpTipoConta4() { return mpTipoConta4; }
	public void setMpTipoConta4(MpTipoConta mpTipoConta4) { this.mpTipoConta4 = mpTipoConta4; }

}
