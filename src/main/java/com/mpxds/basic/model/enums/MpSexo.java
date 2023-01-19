package com.mpxds.basic.model.enums;

public enum MpSexo {

	MASCULINO("Masculino"), 
	FEMININO("Feminino"),
	DEFINIR("Não Definido");
	
	private String descricao;
	
	// ---
	
	MpSexo(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
