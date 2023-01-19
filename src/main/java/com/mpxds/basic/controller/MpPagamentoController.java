package com.mpxds.basic.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
//import java.math.BigDecimal;
//import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.mpxds.basic.exception.MpNegocioException;
import com.mpxds.basic.model.MpPagamento;
import com.mpxds.basic.model.MpContrato;
import com.mpxds.basic.model.MpTabelaInterna;
import com.mpxds.basic.model.enums.MpTipoTabelaInterna;
import com.mpxds.basic.repository.MpPagamentoRepository;
import com.mpxds.basic.repository.MpTabelaInternaRepository;
import com.mpxds.basic.service.MpPagamentoService;
import com.mpxds.basic.util.jsf.MpFacesUtil;

@Named
public class MpPagamentoController implements Serializable {
	//
	private static final long serialVersionUID = 1L;
	
    @Autowired
	private MpPagamentoRepository mpPagamentoRepository;

    @Autowired
	private MpPagamentoService mpPagamentoService;
	
    @Autowired
	private MpTabelaInternaRepository mpTabelaInternaRepository;

	// ---
	
	private String txtTitulo = "Pagamento";

	private MpPagamento mpPagamento;
	private MpPagamento mpPagamentoAnt;
	
	private MpContrato mpContrato;
	private String mpContratoId;

	private Boolean indEditavel = true;
	private String txtModoTela = "";
	private String txtModoCon = "";
	
	private List<MpPagamento> mpPagamentoList;
	private List<MpPagamento> filtroMpPagamentoList;
	 
	private MpTabelaInterna mpPagamentoTipo; 
	private List<MpTabelaInterna> mpPagamentoTipoList  = new ArrayList<MpTabelaInterna>();
	 
	private MpTabelaInterna mpPagamentoStatus; 
	private List<MpTabelaInterna> mpPagamentoStatusList  = new ArrayList<MpTabelaInterna>();
	 
	// ------------------
	
	public void inicializar() {
		//
		this.limpar();
		//		
		this.indEditavel = true;		
		this.txtModoTela = "";
		
		this.mpPagamentoList = this.mpPagamentoService.findAllByDataMovimento();
		//
		this.mpPagamentoTipoList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																			MpTipoTabelaInterna.TB_PAGAMENTO_TIPO);
		this.mpPagamentoStatusList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																			MpTipoTabelaInterna.TB_PAGAMENTO_STATUS);
		//
	}
	
	public void limpar() {
		//
		this.mpPagamento = new MpPagamento();	
		this.mpPagamentoAnt = new MpPagamento();
		//
		//
	}
		
	public void salvar() {
		//
		this.mpPagamento = this.mpPagamentoService.guardar(this.mpPagamento);
		//
		MpFacesUtil.addInfoMessage(this.txtTitulo + "... salva com sucesso! ( " + 
													this.mpPagamento.getDataMovimentoSDF());
	}
		
	// ------------- Trata Botões -------------
	  
	public void mpListener(ActionEvent event){
		//
		this.mpPagamento = (MpPagamento)event.getComponent().getAttributes().get("PagamentoSelecionado");
	}	

	public void mpFechar(CloseEvent event) {
		//
//		System.out.println("MpCadastroPagamentoBean.FECHAR()");
		
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/Dashboard");
			
		} catch (IOException e) {
			MpFacesUtil.addInfoMessage("Erro Redirecionameto Fechar ... contactar o Suporte ! ");
		}
	}	
		
	public void mpNew() {
		//
		this.setMpPagamentoAnt(this.mpPagamento);
		
		this.mpPagamento = new MpPagamento();	
		//
		this.setIndEditavel(false);
		//
		this.txtModoTela = "( Novo )";
	}
	
	public void mpEdit() {
		//
		if (null == this.mpPagamento.getId()) return;
		//
		this.setMpPagamentoAnt(this.mpPagamento);
		
		this.indEditavel = false;
		//
		this.txtModoTela = "( Edição )";
	}
	
	public void mpView() {
		//
		if (null == this.mpPagamento.getId()) return;
		//
		this.setMpPagamentoAnt(this.mpPagamento);
		
		this.indEditavel = true;
		//
		this.txtModoTela = "( Visualização )";
	}
	
	public void mpDelete() {
		//
		if (null == this.mpPagamento.getId()) return;
		//
		try {
			this.mpPagamentoRepository.delete(this.mpPagamento);
			
			this.mpPagamentoList.remove(this.mpPagamento);
			//
			MpFacesUtil.addInfoMessage(this.txtTitulo + "... " + this.mpPagamento.getDataMovimentoSDF() +
																	 			" ... excluída com sucesso.");
		} catch (MpNegocioException ne) {
			MpFacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void mpGrava() {
		//
//		System.out.println("MpCadastroPagamentoBean.GRAVA() (" + this.getIndEditavel() + "/" + this.txtModoTela);
		
		try {
			this.salvar();
			//
		} catch (Exception e) {
			//
			MpFacesUtil.addInfoMessage("Erro na Gravação! ( " + e.toString());			
			//
			return;
		}
		//
		this.mpPagamentoList = this.mpPagamentoService.findAllByDataMovimento();

		this.setMpPagamentoAnt(this.mpPagamento);
		//
		this.indEditavel = true;
		//
		this.txtModoTela = "";
	}
	
	public void mpDesfaz() {
		//
//		System.out.println("MpCadastroPagamentoBean.DESFAZ()");

		this.mpPagamento = this.mpPagamentoAnt;
		
		this.indEditavel = true;
		//
		this.txtModoTela = "";
	}
	
	// ---

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean filterByValor(Object value, Object filter, Locale locale) {
    	//
//		System.out.println("MpCadastroPagamentoBean.FILTER()");

    	String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null||filterText.equals("")) return true;
         
        if (value == null) return false;
        //
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
	
	// ---

	public MpPagamento getMpPagamento() { return mpPagamento; }
	public void setMpPagamento(MpPagamento mpPagamento) { this.mpPagamento = mpPagamento; }

	public MpContrato getMpContrato() { return mpContrato; }
	public void setMpContrato(MpContrato mpContrato) { this.mpContrato = mpContrato; }

	public String getMpContratoId() { return mpContratoId; }
	public void setMpContratoId(String mpContratoId) { this.mpContratoId = mpContratoId; }

	public MpPagamento getMpPagamentoAnt() { return mpPagamentoAnt; }
	public void setMpPagamentoAnt(MpPagamento mpPagamentoAnt) {
		try {
			if (null==this.mpPagamento) return;
			//
			this.mpPagamentoAnt = (MpPagamento) this.mpPagamento.clone();
			this.mpPagamentoAnt.setId(this.mpPagamento.getId());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isEditando() { return this.mpPagamento.getId() != null; }
	
	public boolean isIndEditavel() { return indEditavel; }
	public boolean getIndEditavel() { return indEditavel; }
	public void setIndEditavel(Boolean indEditavel) { this.indEditavel = indEditavel; }
	
	public String getTxtTitulo() { return txtTitulo; }
	public void setTxtTitulo(String txtTitulo) { this.txtTitulo = txtTitulo; }
		
	public String getTxtModoTela() { return txtModoTela; }
	public void setTxtModoTela(String txtModoTela) { this.txtModoTela = txtModoTela; }
	
	public String getTxtModoCon() { return txtModoCon; }
	public void setTxtModoCon(String txtModoCon) { this.txtModoCon = txtModoCon; }
	
	//
	
	public List<MpPagamento> getMpPagamentoList() { return mpPagamentoList; }
	
	public List<MpPagamento> getFiltroMpPagamentoList() { return filtroMpPagamentoList; }
	public void setFiltroMpPagamentoList(List<MpPagamento> filtroMpPagamentoList) { 
																this.filtroMpPagamentoList = filtroMpPagamentoList; }
	
	public MpTabelaInterna getMpPagamentoTipo() { return mpPagamentoTipo; }
	public void setMpPagamentoTipo(MpTabelaInterna mpPagamentoTipo) { this.mpPagamentoTipo = mpPagamentoTipo; }
	public List<MpTabelaInterna> getMpPagamentoTipoList() { return mpPagamentoTipoList; }
	
	public MpTabelaInterna getMpPagamentoStatus() { return mpPagamentoStatus; }
	public void setMpPagamentoStatus(MpTabelaInterna mpPagamentoStatus) { this.mpPagamentoStatus = mpPagamentoStatus; }
	public List<MpTabelaInterna> getMpPagamentoStatusList() { return mpPagamentoStatusList; }

		
}