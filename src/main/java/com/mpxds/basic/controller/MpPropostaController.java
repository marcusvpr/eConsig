package com.mpxds.basic.controller;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mpxds.basic.exception.MpNegocioException;
import com.mpxds.basic.mail.MpSendEmail;
import com.mpxds.basic.model.MpClienteConsignado;
import com.mpxds.basic.model.MpContrato;
import com.mpxds.basic.model.MpProposta;
import com.mpxds.basic.model.MpTabelaInterna;
import com.mpxds.basic.model.enums.MpTipoTabelaInterna;
import com.mpxds.basic.repository.MpClienteConsignadoRepository;
import com.mpxds.basic.repository.MpContratoRepository;
import com.mpxds.basic.repository.MpPropostaRepository;
import com.mpxds.basic.repository.MpTabelaInternaRepository;
import com.mpxds.basic.service.MpClienteConsignadoService;
import com.mpxds.basic.service.MpPropostaService;
import com.mpxds.basic.service.MpTabelaInternaService;
import com.mpxds.basic.util.jsf.MpFacesUtil;
import com.mpxds.basic.util.report.MpExecutorRelatorioDTO;

@Named
public class MpPropostaController implements Serializable {
	//
	private static final long serialVersionUID = 1L;
	
    @Autowired
	private MpPropostaRepository mpPropostaRepository;

    @Autowired
	private MpPropostaService mpPropostaService;
	
    @Autowired
	private MpTabelaInternaRepository mpTabelaInternaRepository;
	
    @Autowired
	private MpTabelaInternaService mpTabelaInternaService;

    @Autowired
    private MpClienteConsignadoRepository mpClienteConsignadoRepository;

    @Autowired
    private MpClienteConsignadoService mpClienteConsignadoService;

    @Autowired
	private MpContratoRepository mpContratoRepository;

    @Autowired
    public MpSendEmail mpSendEmail;

    @Autowired
	private FacesContext facesContext;

    @Autowired
	private HttpServletResponse response;
	
	// ---
	
	private String txtTitulo = "Proposta";

	private MpProposta mpProposta;
	private MpProposta mpPropostaAnt;

	private Boolean indEditavel = true;
	private String txtModoTela = "";
	private String txtModoCon = "";
	
	private List<MpProposta> mpPropostaList;
	private List<MpProposta> filtroMpPropostaList;
	 
	private MpClienteConsignado mpClienteConsignado; 
	private List<MpClienteConsignado> mpClienteConsignadoList  = new ArrayList<MpClienteConsignado>();

	private MpTabelaInterna mpCorrespondente; 
	private List<MpTabelaInterna> mpCorrespondenteList  = new ArrayList<MpTabelaInterna>();
	 
	private MpTabelaInterna mpDigitador;
	private List<MpTabelaInterna> mpDigitadorList  = new ArrayList<MpTabelaInterna>();
	 
	private MpTabelaInterna mpCorretor;
	private List<MpTabelaInterna> mpCorretorList  = new ArrayList<MpTabelaInterna>();
	 
	private MpTabelaInterna mpVendedor;
	private List<MpTabelaInterna> mpVendedorList  = new ArrayList<MpTabelaInterna>();
	 
	private MpTabelaInterna mpTipoContrato;
	private List<MpTabelaInterna> mpTipoContratoList  = new ArrayList<MpTabelaInterna>();
	 
	private MpTabelaInterna mpBancoOperacao;
	private List<MpTabelaInterna> mpBancoOperacaoList  = new ArrayList<MpTabelaInterna>();
	 
	private MpTabelaInterna mpPrazo;
	private List<MpTabelaInterna> mpPrazoList  = new ArrayList<MpTabelaInterna>();

	private MpContrato mpContratoSel = new MpContrato();
	private MpContrato mpContratoSelAnt = new MpContrato();
	private List<MpContrato> mpContratoList = new ArrayList<MpContrato>();
	private List<MpContrato> mpContratoListAnt = new ArrayList<MpContrato>();
	 
	private MpTabelaInterna mpCodigoComissao;
	private List<MpTabelaInterna> mpCodigoComissaoList  = new ArrayList<MpTabelaInterna>();
	
	private MpClienteConsignado mpClienteConsignadoParam = new MpClienteConsignado();
	
	// ------------------
	
	public void inicializar() {
		//
		this.limpar();
		//		
		this.indEditavel = true;		
		this.txtModoTela = "";
		
		this.mpPropostaList = this.mpPropostaService.findAllByDataMovimento();
		//
		this.mpClienteConsignadoList = this.mpClienteConsignadoRepository.findAllByOrderByNome();

		this.mpDigitadorList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																			MpTipoTabelaInterna.TB_DIGITADOR);
		this.mpCorrespondenteList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																			MpTipoTabelaInterna.TB_CORRESPONDENTE);
		this.mpCorretorList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																			MpTipoTabelaInterna.TB_CORRETOR);
		this.mpVendedorList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																			MpTipoTabelaInterna.TB_VENDEDOR);
		this.mpTipoContratoList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																			MpTipoTabelaInterna.TB_TIPO_CONTRATO);
		this.mpBancoOperacaoList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																			MpTipoTabelaInterna.TB_BANCO_OPERACAO);
		this.mpPrazoList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigoDesc(
																			MpTipoTabelaInterna.TB_PRAZO);
		this.mpCodigoComissaoList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																			MpTipoTabelaInterna.TB_CODIGO_COMISSAO);
		//
//	    FacesContext context = FacesContext.getCurrentInstance();
//	    String idCliente = context.getExternalContext().getRequestParameterMap().get("idCliente");
		this.mpClienteConsignadoParam = null;

	    Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
	    
		// Trata Proposta vinda da Pesquisa ...
		String idProposta = params.get("idProposta");
		if (null == idProposta || idProposta.isEmpty())
			assert(true); // nop
		else {
			//
			this.mpProposta = mpPropostaService.findById(Long.parseLong(idProposta));
			if (null == this.mpProposta)
				assert(true); // nop
			else {
				//
				this.mpContratoSel = this.mpProposta.getMpContratoList().get(0);
				this.mpContratoList.add(this.mpProposta.getMpContratoList().get(0));
				this.mpContratoListAnt.add(this.mpProposta.getMpContratoList().get(0));
			}
		}
		// Trata Cliente vindo do Cadastro de Cliente ...
		String idCliente = params.get("idCliente");
	    if (null == idCliente || idCliente.isEmpty())
			assert(true); // nop
	    else {
	    	this.mpClienteConsignadoParam = mpClienteConsignadoService.findById(Long.parseLong(idCliente));	 
		    if (null == this.mpClienteConsignadoParam)
				assert(true); // nop
		    else
		    	this.mpProposta.setMpClienteConsignado(mpClienteConsignadoParam);
	    }
	    //
	}
	
	public void limpar() {
		//
		this.mpProposta = new MpProposta();	
		this.mpPropostaAnt = new MpProposta();
		//
		//
		this.mpProposta.setMpContratoList(new ArrayList<MpContrato>());
	
		this.mpContratoList = new ArrayList<MpContrato>();
		this.mpContratoListAnt = new ArrayList<MpContrato>();
		
		this.mpContratoSel = new MpContrato();
		this.mpContratoSelAnt = new MpContrato();
	}
		
	public void salvar() {
		//
		// Vrf.Calculo COMISSÂO ...
		mpContratoSel.setComissao(BigDecimal.ZERO);

		// Trata Proposta x Contrato 1:1 ! 
		// -------------------------------
		this.mpContratoList = new ArrayList<MpContrato>();
		this.mpContratoList.add(this.mpContratoSel);
		
		this.mpProposta.setMpContratoList(this.mpContratoList);
		
		this.mpProposta = this.mpPropostaService.guardar(this.mpProposta);

		//
//		mpContratoSel.setMpProposta(this.mpProposta);
//
//		mpContratoSel = this.mpContratoRepository.saveAndFlush(mpContratoSel);
		
//		// Trata Lista Documento Digital ?
//		for (MpContrato mpContratoX : this.mpContratoList) {
//			//
//			mpContratoX.setMpProposta(this.mpProposta);
//			
//			mpContratoX = this.mpContratoRepository.saveAndFlush(mpContratoX);
//			//
//		}
		//
		MpFacesUtil.addInfoMessage(this.txtTitulo + "... salva com sucesso! ( " + 
													this.mpProposta.getDataMovimentoSDF() +
													" / Num.Contrato(s) = " + this.mpContratoList.size());
	}
	
	public void handleChangeClienteConsignado() {
		//
		if (null == this.mpClienteConsignado) {
			//
			MpFacesUtil.addInfoMessage("Selecionar Cliente !"); 
			return ;
		}
		
		this.mpProposta.setMpClienteConsignado(this.mpClienteConsignado);
	}
	
	public void handleChangeIndTerceiros() {
		//
		MpFacesUtil.addInfoMessage("Ind.Terceiros... modificado !"); 
	}
		
	// --- Trata Contratos --- Proposta só tem um contrado (1:N p/ 1:1) ...
	
	
	// ------------- Trata Botões -------------
	  
	public void mpListener(ActionEvent event){
		//
		this.mpProposta = (MpProposta)event.getComponent().getAttributes().get("PropostaSelecionado");
		
		if (this.mpProposta.getMpContratoList().size() > 0) {
			//
			this.mpContratoSel = this.mpProposta.getMpContratoList().get(0);
			this.mpContratoList.add(this.mpProposta.getMpContratoList().get(0));
			this.mpContratoListAnt.add(this.mpProposta.getMpContratoList().get(0));
		}
	}	

	public void mpFechar(CloseEvent event) {
		//
//		System.out.println("MpCadastroPropostaBean.FECHAR()");
		
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/Dashboard");
			
		} catch (IOException e) {
			MpFacesUtil.addInfoMessage("Erro Redirecionameto Fechar ... contactar o Suporte ! ");
		}
	}	
		
	public void mpNew() {
		//
		this.setMpPropostaAnt(this.mpProposta);
		
		this.mpProposta = new MpProposta();
		
		this.mpProposta.setMpClienteConsignado(mpClienteConsignadoParam);
		this.mpContratoSel = new MpContrato();
		//
		this.setIndEditavel(false);
		//
		this.txtModoTela = "( Novo )";
	}
	
	public void mpEdit() {
		//
		if (null == this.mpProposta.getId()) return;
		//
		this.setMpPropostaAnt(this.mpProposta);
		
		this.indEditavel = false;
		//
		this.txtModoTela = "( Edição )";
	}
	
	public void mpView() {
		//
		if (null == this.mpProposta.getId()) return;
		//
		this.setMpPropostaAnt(this.mpProposta);
		
		this.indEditavel = true;
		//
		this.txtModoTela = "( Visualização )";
	}
	
	public void mpDelete() {
		//
		if (null == this.mpProposta.getId()) return;
		//
//		if (!this.mpProposta.getMpContratoList().isEmpty()) {
//			//
//			MpFacesUtil.addInfoMessage("Proposta possui contratos! Favor verificar.");
//			return;
//		}
		// Exclui contratos relacionadas com a Proposta ! 
		if (!this.mpProposta.getMpContratoList().isEmpty()) {
			//
			for (MpContrato mpContratoDel : this.mpProposta.getMpContratoList()) {
				this.mpContratoRepository.delete(mpContratoDel);
			}
		}
		//
		try {
			this.mpPropostaRepository.delete(this.mpProposta);
			
			this.mpPropostaList.remove(this.mpProposta);
			//
			MpFacesUtil.addInfoMessage(this.txtTitulo + "... " + this.mpProposta.getDataMovimentoSDF() +
																	 			" ... excluída com sucesso.");
		} catch (MpNegocioException ne) {
			MpFacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void mpGrava() {
		//
//		System.out.println("MpCadastroPropostaBean.GRAVA() (" + this.getIndEditavel() + "/" + this.txtModoTela);
		
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
		this.mpPropostaList = this.mpPropostaService.findAllByDataMovimento();

		this.setMpPropostaAnt(this.mpProposta);
		//
		this.indEditavel = true;
		//
		this.txtModoTela = "";
	}
	
	public void mpDesfaz() {
		//
//		System.out.println("MpCadastroPropostaBean.DESFAZ()");

		this.mpProposta = this.mpPropostaAnt;
		
		this.indEditavel = true;
		//
		this.txtModoTela = "";
	}
	
	// ---
	
	public void mpEnviaEmailDigitadorProposta() {
		//
		if (null == this.mpProposta || null == this.mpProposta.getId()) {
			//
			MpFacesUtil.addInfoMessage("Nenhuma Proposta Selecionada !");
			return;
		}
		//
		MpTabelaInterna mpEmailDigitadorProposta = this.mpTabelaInternaService.findByMpTipoTabelaInternaAndCodigo(
														MpTipoTabelaInterna.TB_EMAIL_DIGITADOR_PROPOSTA, "email");
		if (null == mpEmailDigitadorProposta) {	
			MpFacesUtil.addInfoMessage("Error Configuração Email Digitador Proposta! Contactar Suporte!" + 
												"MpTipoTabelaInterna.TB_EMAIL_DIGITADOR_PROPOSTA, \"email\"");
			return;
		}
		//
		String to = "marcus_vpr@hotmail.com"; // mpEmailDigitadorProposta.getDescricao().trim();
		String subject = "MpConsig Proposta ( Data: " + this.mpProposta.getDataMovimentoSDF() + 
									" / Cliente: " + this.mpProposta.getMpClienteConsignado().getNome() + " )";
		String msg = "";
		//
		msg = msg + " [ . Data : " + this.mpProposta.getDataMovimentoSDF() + " ]\n" ;
		msg = msg + " [ . Nome : " + this.mpProposta.getMpClienteConsignado().getNome() + " ]\n" ;
		msg = msg + " [ . Digitador : " + this.mpProposta.getMpDigitador().getDescricao() + " ]\n" ;
		msg = msg + " [ . Correspondente : " + this.mpProposta.getMpCorrespondente().getDescricao() + " ]\n" ;
		msg = msg + " [ . Corretor : " + this.mpProposta.getMpCorretor().getDescricao() + " ]\n" ;
		msg = msg + " [ . Vendedor : " + this.mpProposta.getMpVendedor().getDescricao() + " ]\n" ;
		msg = msg + " [ . Margem : " + this.mpProposta.getMargem() + " ]\n" ;
		msg = msg + " [ . Ind.Terceiros : " + (this.mpProposta.getIndTerceiros() ? "Sim" : "___") + " ]\n" ;
		msg = msg + " [ . Ind.Portabilidade : " + (this.mpProposta.getIndPortabilidade() ? "Sim" : "___") + " ]\n" ;
		msg = msg + " [ . Número Convênio : " + this.mpProposta.getNumeroConvenio() + " ]\n" ;
		//
		if (this.mpProposta.getMpContratoList().size() > 0) {
			//
			MpContrato mpContratoM = this.mpProposta.getMpContratoList().get(0);
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			//
			msg = msg + "\n" ;
			msg = msg + " [ ============================================================== ]\n" ;
			msg = msg + " [ . Tipo Contrato : " +  mpContratoM.getMpTipoContrato().getDescricao() + " ]\n" ;
			msg = msg + " [ . Banco Operação : " +  mpContratoM.getMpBancoOperacao().getDescricao() + " ]\n" ;
			msg = msg + " [ . Valor Parcela : " +  nf.format(mpContratoM.getValorParcela()) + " ]\n" ;
			msg = msg + " [ . Valor Contrato : " +  nf.format(mpContratoM.getValorContrato()) + " ]\n" ;
			msg = msg + " [ . Prazo : " +  mpContratoM.getMpPrazo().getDescricao() + " ]\n" ;
			msg = msg + " [ . Código Comissão : " +  mpContratoM.getMpCodigoComissao().getDescricao() + " ]\n" ;
			msg = msg + " [ . Observação : " +  mpContratoM.getObservacao() + " ]\n" ;
			msg = msg + " [ ============================================================== ]\n" ;
			//
		}
		//
		try { 
			//
//			System.out.println("MpApplicationStartup SEND - ( " + msg);

			this.mpSendEmail.sendSimpleMessage(to, subject, msg);
		}		 
		catch (Exception e) { 
			//
			MpFacesUtil.addInfoMessage("Erro envio Email Proposta ( e = " + e);
			return;
		}
		//
		MpFacesUtil.addInfoMessage("Email da Proposta... Enviado com sucesso !");
		//
	}

    @Transactional
	public void mpImprimeProposta() {
		//
		Map<String, Object> parametros = new HashMap<>();
		
		MpExecutorRelatorioDTO executor = new MpExecutorRelatorioDTO("/relatorios/mpRelatorioFicha_proposta_dto.jasper", 
															this.response, parametros, "MpFicha_proposta" + ".pdf");
		//
		Collection<Object> data = new ArrayList<Object>();
		//
		System.out.println("mpImprimeProposta() ................. ( " + this.mpProposta.getId());
		System.out.println("mpImprimeProposta() ................. ( " + this.mpProposta.getNumeroConvenio());
	
		if (null == this.mpProposta.getNumeroConvenio()) {
			//
			MpProposta mpPropostaXX = mpPropostaService.findById(this.mpProposta.getId());
		
			data.add(mpPropostaXX);
		} else		
			data.add(this.mpProposta);
		//
		try {
			executor.execute(data);
			//
		} catch (Exception e) {
			MpFacesUtil.addErrorMessage("A execução do relatório apresentou erro. Contactar o Suporte");
		}
		//
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			MpFacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
		}
	}
	
	// ---

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean filterByValor(Object value, Object filter, Locale locale) {
    	//
//		System.out.println("MpCadastroPropostaBean.FILTER()");

    	String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null||filterText.equals("")) return true;
         
        if (value == null) return false;
        //
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
	
	// ---

	public MpProposta getMpProposta() { return mpProposta; }
	public void setMpProposta(MpProposta mpProposta) { this.mpProposta = mpProposta; }

	public MpProposta getMpPropostaAnt() { return mpPropostaAnt; }
	public void setMpPropostaAnt(MpProposta mpPropostaAnt) {
		try {
			if (null==this.mpProposta) return;
			//
			this.mpPropostaAnt = (MpProposta) this.mpProposta.clone();
			this.mpPropostaAnt.setId(this.mpProposta.getId());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isEditando() { return this.mpProposta.getId() != null; }
	
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
	
	public List<MpProposta> getMpPropostaList() { return mpPropostaList; }
	
	public List<MpProposta> getFiltroMpPropostaList() { return filtroMpPropostaList; }
	public void setFiltroMpPropostaList(List<MpProposta> filtroMpPropostaList) { 
																this.filtroMpPropostaList = filtroMpPropostaList; }
	
	public MpTabelaInterna getMpDigitador() { return mpDigitador; }
	public void setMpDigitador(MpTabelaInterna mpDigitador) { this.mpDigitador = mpDigitador; }
	public List<MpTabelaInterna> getMpDigitadorList() { return mpDigitadorList; }

	public MpClienteConsignado getMpClienteConsignado() { return mpClienteConsignado; }
	public void setMpClienteConsignado(MpClienteConsignado mpClienteConsignado) { 
																	this.mpClienteConsignado = mpClienteConsignado; }
	public List<MpClienteConsignado> getMpClienteConsignadoList() { return mpClienteConsignadoList; }
	
	public MpTabelaInterna getMpCorrespondente() { return mpCorrespondente; }
	public void setMpCorrespondente(MpTabelaInterna mpCorrespondente) {	this.mpCorrespondente = mpCorrespondente; }
	public List<MpTabelaInterna> getMpCorrespondenteList() { return mpCorrespondenteList; }
	
	public MpTabelaInterna getMpCorretor() { return mpCorretor; }
	public void setMpCorretor(MpTabelaInterna mpCorretor) { this.mpCorretor = mpCorretor; }
	public List<MpTabelaInterna> getMpCorretorList() { return mpCorretorList; }
	
	public MpTabelaInterna getMpVendedor() { return mpVendedor; }
	public void setMpVendedor(MpTabelaInterna mpVendedor) { this.mpVendedor = mpVendedor; }
	public List<MpTabelaInterna> getMpVendedorList() { return mpVendedorList; }
	
	public MpContrato getMpContratoSel() { return mpContratoSel; }
	public void setMpContratoSel(MpContrato mpContratoSel) { this.mpContratoSel = mpContratoSel; }
	public MpContrato getMpContratoSelAnt() { return mpContratoSelAnt; }
	public void setMpContratoSelAnt(MpContrato mpContratoSelAnt) { this.mpContratoSelAnt = mpContratoSelAnt; }
	
	public List<MpContrato> getMpContratoList() { return mpContratoList; }
	public void setMpContratoList(List<MpContrato> mpContratoList) { this.mpContratoList = mpContratoList; }
		
	public List<MpContrato> getMpContratoListAnt() { return mpContratoListAnt; }
	public void setMpContratoListAnt(List<MpContrato> mpContratoListAnt) { 
																	this.mpContratoListAnt = mpContratoListAnt; }
	public MpTabelaInterna getMpTipoContrato() { return mpTipoContrato; }
	public void setMpTipoContrato(MpTabelaInterna mpTipoContrato) { this.mpTipoContrato = mpTipoContrato; }
	public List<MpTabelaInterna> getMpTipoContratoList() { return mpTipoContratoList; }
	
	public MpTabelaInterna getMpBancoOperacao() { return mpBancoOperacao; }
	public void setMpBancoOperacao(MpTabelaInterna mpBancoOperacao) { this.mpBancoOperacao = mpBancoOperacao; }
	public List<MpTabelaInterna> getMpBancoOperacaoList() { return mpBancoOperacaoList; }
	
	public MpTabelaInterna getMpPrazo() { return mpPrazo; }
	public void setMpPrazo(MpTabelaInterna mpPrazo) { this.mpPrazo = mpPrazo; }
	public List<MpTabelaInterna> getMpPrazoList() { return mpPrazoList; }
	
	public MpTabelaInterna getMpCodigoComissao() { return mpCodigoComissao; }
	public void setMpCodigoComissao(MpTabelaInterna mpCodigoComissao) { this.mpCodigoComissao = mpCodigoComissao; }
	public List<MpTabelaInterna> getMpCodigoComissaoList() { return mpCodigoComissaoList; }
		
}