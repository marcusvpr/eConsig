package com.mpxds.basic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.mpxds.basic.model.MpEntity;

@Entity
@Table(name = "mp_documento_digital")
public class MpDocumentoDigital extends MpEntity {
	//
	private static final long serialVersionUID = 1L;

	@Column(nullable = true)
	private String descricao;

	@Column(name = "arquivo_link", nullable = true)
	private String arquivoLink;
		
	@ManyToOne
	@JoinColumn(name = "mpClienteConsignado_id")
	private MpClienteConsignado mpClienteConsignado;
	
	public MpDocumentoDigital() {
		//
	}
	
	// ---

	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getArquivoLink() { return this.arquivoLink; }
    public void setArquivoLink(String newArquivoLink) { this.arquivoLink = newArquivoLink; }
    	
	public MpClienteConsignado getMpClienteConsignado() { return mpClienteConsignado; }
	public void setMpClienteConsignado(MpClienteConsignado mpClienteConsignado) { 
															this.mpClienteConsignado = mpClienteConsignado; }
	
}
