package com.mpxds.basic.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.mpxds.basic.model.MpEntity;
import com.mpxds.basic.util.jsf.MpFacesUtil;

@Entity
@Table(name = "mp_contrato")
public class MpContrato extends MpEntity {
	//
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "mpTipoContrato_id")
	private MpTabelaInterna mpTipoContrato;
	
	@ManyToOne
	@JoinColumn(name = "mpBancoOperacao_id")
	private MpTabelaInterna mpBancoOperacao;

	@NotNull(message = "Valor Parcela é obrigatório")
	@Column(name = "valor_parcela", nullable = false, precision = 10, scale = 3)
	private BigDecimal valorParcela;

	@NotNull(message = "Valor Contrato é obrigatório")
	@Column(name = "valor_contrato", nullable = false, precision = 10, scale = 3)
	private BigDecimal valorContrato;
	
	@ManyToOne
	@JoinColumn(name = "mpPrazo_id")
	private MpTabelaInterna mpPrazo;
	
	@ManyToOne
	@JoinColumn(name = "mpCodigo_comissao")
	private MpTabelaInterna mpCodigoComissao;
	
	@NotNull(message = "Valor Contrato é obrigatório")
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal comissao;

	@Column(name = "observacao", nullable = true)
	private String observacao;
		
	@ManyToOne
	@JoinColumn(name = "mpProposta_id")
	private MpProposta mpProposta;
		
	@OneToMany(mappedBy = "mpContrato", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<MpPagamento> mpPagamentoList = new ArrayList<MpPagamento>();
	
	// ---------------

	public MpTabelaInterna getMpTipoContrato() { return mpTipoContrato; }
	public void setMpTipoContrato(MpTabelaInterna mpTipoContrato) {	this.mpTipoContrato = mpTipoContrato; }

	public MpTabelaInterna getMpBancoOperacao() { return mpBancoOperacao; }
	public void setMpBancoOperacao(MpTabelaInterna mpBancoOperacao) { this.mpBancoOperacao = mpBancoOperacao; }
  	
	public BigDecimal getValorParcela() { return this.valorParcela; }
	public void setValorParcela(BigDecimal newValorParcela) { this.valorParcela = newValorParcela; }
  	
	public BigDecimal getValorContrato() { return this.valorContrato; }
	public void setValorContrato(BigDecimal newValorContrato) { this.valorContrato = newValorContrato; }

	public MpTabelaInterna getMpPrazo() { return mpPrazo; }
	public void setMpPrazo(MpTabelaInterna mpPrazo) { this.mpPrazo = mpPrazo; }

	public MpTabelaInterna getMpCodigoComissao() { return mpCodigoComissao; }
	public void setMpCodigoComissao(MpTabelaInterna mpCodigoComissao) { this.mpCodigoComissao = mpCodigoComissao; }
  	
	public BigDecimal getComissao() { return this.comissao; }
	public void setComissao(BigDecimal newComissao) { this.comissao = newComissao; }

	public String getObservacao() { return observacao; }
	public void setObservacao(String observacao) { this.observacao = observacao; }
    	
	public MpProposta getMpProposta() { return mpProposta; }
	public void setMpProposta(MpProposta mpProposta) { this.mpProposta = mpProposta; }
	
	public List<MpPagamento> getMpPagamentoList() { return mpPagamentoList; }
	public void setMpPagamentoList(List<MpPagamento> mpPagamentoList) { this.mpPagamentoList = mpPagamentoList; }

	//
	
	@Transient
  	public BigDecimal getComissaoValor() {
  		//
		if (null==this.valorContrato || null==this.mpCodigoComissao) return BigDecimal.ZERO;
		
		BigDecimal comissaoValor = BigDecimal.ZERO;
		try {
			BigDecimal comissaoX = BigDecimal.valueOf(Double.parseDouble(this.mpCodigoComissao.getCodigo()));

			comissaoValor = this.valorContrato.multiply(comissaoX).divide(BigDecimal.valueOf(100));
		} catch (Exception ex) {
			MpFacesUtil.addErrorMessage("Erro Calculo Comissão - Favor verificar ( " + 
																			this.mpCodigoComissao.getCodigo());
			comissaoValor = BigDecimal.ZERO;	
		}
		//
  		return comissaoValor; 
  	}
	
	@Transient
  	public String getNumeroId() {
  		//
		return StringUtils.leftPad(this.id.toString(), 5, "0");
  	}
	
}
