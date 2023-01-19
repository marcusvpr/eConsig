package com.mpxds.basic.model.enums;

public enum MpEstadoCivil {

	CASADO("Casado"), 
	DIVORCIADO("Divorciado"), 
	VIÚVO("Viúvo"), 
	U_ESTÁVEL("União Estável"), 
	SOLTEIRO("Solteiro"),
	DEFINIR("Não Definido");
	
	private String descricao;
	
	// ---
	
	MpEstadoCivil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() { return descricao; }
	
}
