package com.mpxds.basic.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import javax.faces.FacesException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Named;

import org.primefaces.event.CaptureEvent;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;

import com.mpxds.basic.exception.MpNegocioException;
import com.mpxds.basic.model.MpClienteConsignado;
import com.mpxds.basic.model.MpClienteObservacao;
import com.mpxds.basic.model.MpDadosBancario;
import com.mpxds.basic.model.MpDadosConvenio;
import com.mpxds.basic.model.MpDocumentoDigital;
import com.mpxds.basic.model.MpEnderecoLocal;
import com.mpxds.basic.model.MpProposta;
import com.mpxds.basic.model.MpTabelaInterna;
import com.mpxds.basic.model.enums.MpContaTipo;
import com.mpxds.basic.model.enums.MpEstadoCivil;
import com.mpxds.basic.model.enums.MpEstadoUF;
import com.mpxds.basic.model.enums.MpSexo;
import com.mpxds.basic.model.enums.MpStatus;
import com.mpxds.basic.model.enums.MpTipoConta;
import com.mpxds.basic.model.enums.MpTipoTabelaInterna;
import com.mpxds.basic.model.xml.MpCepXML;
import com.mpxds.basic.repository.MpClienteConsignadoRepository;
import com.mpxds.basic.repository.MpClienteObservacaoRepository;
import com.mpxds.basic.repository.MpDocumentoDigitalRepository;
import com.mpxds.basic.repository.MpTabelaInternaRepository;
import com.mpxds.basic.service.MpClienteConsignadoService;
import com.mpxds.basic.service.MpTabelaInternaService;
import com.mpxds.basic.util.MpAppUtil;
import com.mpxds.basic.util.jsf.MpFacesUtil;
import com.mpxds.basic.util.ws.MpClienteWebService;

@Named
public class MpClienteConsignadoController implements Serializable {
	//
	private static final long serialVersionUID = 1L;
	
    @Autowired
	private MpClienteConsignadoRepository mpClienteConsignadoRepository;

    @Autowired
	private MpClienteConsignadoService mpClienteConsignadoService;
	
    @Autowired
	private MpTabelaInternaRepository mpTabelaInternaRepository;
    @Autowired
	private MpTabelaInternaService mpTabelaInternaService;

    @Autowired
	private MpDocumentoDigitalRepository mpDocumentoDigitalRepository;

    @Autowired
	private MpClienteObservacaoRepository mpClienteObservacaoRepository;
	
	// ---
	
	private String txtTitulo = "Cliente";

	private MpClienteConsignado mpClienteConsignado = new MpClienteConsignado();
	private MpClienteConsignado mpClienteConsignadoAnt;

	private Boolean indEditavel = true;
	private String txtModoTela = "";
	private String txtModoDoc = "";
	private String txtModoObs = "";
	
	private List<MpClienteConsignado> mpClienteConsignadoList;
	private List<MpClienteConsignado> filtroMpClienteConsignadoList;
	
	private MpStatus mpStatus;
	private List<MpStatus> mpStatusList;
	
	private MpSexo mpSexo;
	private List<MpSexo> mpSexoList;
	
	private MpEstadoCivil mpEstadoCivil;
	private List<MpEstadoCivil> mpEstadoCivilList;
	
	private MpEstadoUF mpEstadoUF;
	private MpEstadoUF mpEstadoUFIdent;
	private List<MpEstadoUF> mpEstadoUFList;
	 
	private MpTabelaInterna mpIndicacao; // tab_0001
	private List<MpTabelaInterna> mpIndicacaoList  = new ArrayList<MpTabelaInterna>();
	 
	private MpTabelaInterna mpOrgaoEmissor; // tab_0002
	private List<MpTabelaInterna> mpOrgaoEmissorList  = new ArrayList<MpTabelaInterna>();
	 
	private MpEnderecoLocal mpEnderecoLocal = new MpEnderecoLocal();
	private MpDadosBancario mpDadosBancario = new MpDadosBancario();
	private MpDadosConvenio mpDadosConvenio = new MpDadosConvenio();

	private MpTabelaInterna mpCidade;
	private List<MpTabelaInterna> mpCidadeList  = new ArrayList<MpTabelaInterna>();

	private MpTabelaInterna mpTipoTelefone;
	private MpTabelaInterna mpTipoTelefone1;
	private List<MpTabelaInterna> mpTipoTelefoneList  = new ArrayList<MpTabelaInterna>();

	private MpTabelaInterna mpBanco; // tab_0005
	private List<MpTabelaInterna> mpBancoList  = new ArrayList<MpTabelaInterna>();

	private MpTabelaInterna mpConvenio;
	private List<MpTabelaInterna> mpConvenioList  = new ArrayList<MpTabelaInterna>();

	private MpTabelaInterna mpEspecieBeneficio;
	private List<MpTabelaInterna> mpEspecieBeneficioList  = new ArrayList<MpTabelaInterna>();

	private MpTabelaInterna mpOrgaoConvenio; 
	private List<MpTabelaInterna> mpOrgaoConvenioList  = new ArrayList<MpTabelaInterna>();

	private MpTabelaInterna mpTipoRg;
	private List<MpTabelaInterna> mpTipoRgList  = new ArrayList<MpTabelaInterna>();

	private MpContaTipo mpContaTipo; // Enums
	private List<MpContaTipo> mpContaTipoList  = new ArrayList<MpContaTipo>();

	private MpTipoConta mpTipoConta; // Enums
	private List<MpTipoConta> mpTipoContaList  = new ArrayList<MpTipoConta>();
	
	private Integer idade;
	private Integer tempoDesde;
	
	private UploadedFile arquivoUpload;
	private StreamedContent arquivoContent = new DefaultStreamedContent();
	private byte[] arquivoBytes;

	private MpDocumentoDigital mpDocumentoDigitalSel = new MpDocumentoDigital();
	private MpDocumentoDigital mpDocumentoDigitalSelAnt = new MpDocumentoDigital();
	private List<MpDocumentoDigital> mpDocumentoDigitalList = new ArrayList<MpDocumentoDigital>();
	private List<MpDocumentoDigital> mpDocumentoDigitalListAnt = new ArrayList<MpDocumentoDigital>();
	private List<MpDocumentoDigital> mpDocumentoDigitalListDel = new ArrayList<MpDocumentoDigital>();
	
	private StreamedContent fileSC = new DefaultStreamedContent();

	private MpClienteObservacao mpClienteObservacaoSel = new MpClienteObservacao();
	private MpClienteObservacao mpClienteObservacaoSelAnt = new MpClienteObservacao();
	private List<MpClienteObservacao> mpClienteObservacaoList = new ArrayList<MpClienteObservacao>();
	private List<MpClienteObservacao> mpClienteObservacaoListAnt = new ArrayList<MpClienteObservacao>();
	private List<MpClienteObservacao> mpClienteObservacaoListDel = new ArrayList<MpClienteObservacao>();
		
	// ------------------
	
	public void inicializar() {
		//		
		this.limpar();
		//
		this.indEditavel = true;		
		this.txtModoTela = "";
		this.txtModoDoc = "";
		this.txtModoObs = "";
		
		this.mpClienteConsignadoList = this.mpClienteConsignadoService.findAllByNome();
		
		this.mpStatusList = Arrays.asList(MpStatus.values());
		this.mpSexoList = Arrays.asList(MpSexo.values());
		this.mpEstadoCivilList = Arrays.asList(MpEstadoCivil.values());
		this.mpContaTipoList = Arrays.asList(MpContaTipo.values());
		this.mpTipoContaList = Arrays.asList(MpTipoConta.values());
		//
		this.mpEstadoUFList = Arrays.asList(MpEstadoUF.values());
		//
		this.mpIndicacaoList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																		MpTipoTabelaInterna.TB_INDICACAO);
		this.mpOrgaoEmissorList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																		MpTipoTabelaInterna.TB_ORGAO_EXPEDIDOR);
		this.mpCidadeList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																		MpTipoTabelaInterna.TB_CIDADE);
		this.mpTipoTelefoneList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																		MpTipoTabelaInterna.TB_TIPO_TELEFONE);
		this.mpBancoList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																		MpTipoTabelaInterna.TB_BANCO);
		this.mpConvenioList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																		MpTipoTabelaInterna.TB_CONVENIO);
		this.mpEspecieBeneficioList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																		MpTipoTabelaInterna.TB_ESPECIE_BENEFICIO);
		this.mpTipoRgList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																		MpTipoTabelaInterna.TB_TIPO_RG);
		this.mpOrgaoConvenioList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																			MpTipoTabelaInterna.TB_ORGAO_CONVENIO);
	}
		
	public void limpar() {
		//
		this.mpClienteConsignado = new MpClienteConsignado();	

		this.mpEnderecoLocal = new MpEnderecoLocal();
		this.mpEnderecoLocal.setCep("");
		this.mpEnderecoLocal.setLogradouro("");
		this.mpEnderecoLocal.setBairro("");
		this.mpEnderecoLocal.setComplemento("");
		this.mpEnderecoLocal.setNumero("");

		this.mpDadosBancario = new MpDadosBancario();
		this.mpDadosConvenio = new MpDadosConvenio();
		//
		this.mpClienteConsignado.setMpEnderecoLocal(mpEnderecoLocal);
		this.mpClienteConsignado.setMpDadosBancario(mpDadosBancario);
		this.mpClienteConsignado.setMpDadosConvenio(mpDadosConvenio);
		//
		this.idade = 0;
		this.tempoDesde = 0;
		//
		this.mpClienteConsignado.setMpDocumentoDigitalList(new ArrayList<MpDocumentoDigital>());
		this.mpClienteConsignado.setMpClienteObservacaoList(new ArrayList<MpClienteObservacao>());

		this.mpDocumentoDigitalList = new ArrayList<MpDocumentoDigital>();
		this.mpDocumentoDigitalListAnt = new ArrayList<MpDocumentoDigital>();
		this.mpDocumentoDigitalListDel = new ArrayList<MpDocumentoDigital>();

		this.mpClienteObservacaoList = new ArrayList<MpClienteObservacao>();
		this.mpClienteObservacaoListAnt = new ArrayList<MpClienteObservacao>();
		this.mpClienteObservacaoListDel = new ArrayList<MpClienteObservacao>();
	}
	
	public void salvar() {
		//
		// Trata Expiração do Sistema !
		Calendar calx = new GregorianCalendar(2019,5,1);
		
		Date dataAtual = new Date();
		if (dataAtual.after(calx.getTime())) {
			//
			MpFacesUtil.addErrorMessage("Error on System - Date Expiration (Contactar Suporte)");
			return;
		}
		//
		this.idade = MpAppUtil.calculaIdadeAnos(this.mpClienteConsignado.getDataNascimento());
		this.tempoDesde = MpAppUtil.calculaIdadeAnos(this.mpClienteConsignado.getDataDesde());
		
		this.mpClienteConsignado = this.mpClienteConsignadoService.guardar(this.mpClienteConsignado);
		
		// Trata Lista Documento Digital Deletados !
		for (MpDocumentoDigital mpDocumentoDigitalD : this.mpDocumentoDigitalListDel) {
			//
			this.mpDocumentoDigitalRepository.delete(mpDocumentoDigitalD);
		}		
		// Trata Lista Cliente Observacao Deletados ! 
		for (MpClienteObservacao mpClienteObservacaoD : this.mpClienteObservacaoListDel) {
			//
			this.mpClienteObservacaoRepository.delete(mpClienteObservacaoD);
		}
//		// Recarrega List´s ...
//		this.mpDocumentoDigitalList = mpDocumentoDigitalListAtu;
//		this.mpClienteObservacaoList = mpClienteObservacaoListAtu;
//		
//		this.mpClienteConsignado.setMpDocumentoDigitalList(this.mpDocumentoDigitalList);
//		this.mpClienteConsignado.setMpClienteObservacaoList(this.mpClienteObservacaoList);
//		
//		this.mpClienteConsignado = this.mpClienteConsignadoService.guardar(this.mpClienteConsignado);
		//
		MpFacesUtil.addInfoMessage(this.txtTitulo + "... salvo com sucesso! ( " +
									this.mpClienteConsignado.getNome() + 
									" / Num.Documentos = " + this.mpDocumentoDigitalList.size() +
									" / Num.Observações = " + this.mpClienteObservacaoList.size());
	}
	
	public void handleChangeDataNascimento() {
		//
		this.idade = MpAppUtil.calculaIdadeAnos(this.mpClienteConsignado.getDataNascimento());	
	}
	
	public void handleChangeDataDesde() {
		//
		this.tempoDesde = MpAppUtil.calculaIdadeAnos(this.mpClienteConsignado.getDataDesde());	
	}

	public void handleChangeCEP() {
		//
		this.onCepWebService();
	}

	public void onCepWebService() {
    	//
		MpCepXML mpCepXML = MpClienteWebService.executaCep(this.mpClienteConsignado.getMpEnderecoLocal().getCep());
		if (null == mpCepXML)
			MpFacesUtil.addErrorMessage("CEP WebService sem retorno ou CEP não encontrado !");
		else {
//			System.out.println("MpCadastroClienteConsignadoBean.onCepWebService() - ( " +
//				this.mpClienteConsignado.getMpEnderecoEntrega().getCep() + " / " + mpCepXML.getLogradouro());
			//
			this.mpClienteConsignado.getMpEnderecoLocal().setLogradouro(mpCepXML.getLogradouro());
			this.mpClienteConsignado.getMpEnderecoLocal().setComplemento(mpCepXML.getComplemento());
			this.mpClienteConsignado.getMpEnderecoLocal().setBairro(mpCepXML.getBairro());

			// Trata UF! ...
			MpEstadoUF mpEstadoUF = MpEstadoUF.XX;
			if (null == mpCepXML.getEstado())
				mpEstadoUF = MpEstadoUF.XX;
			else {
				mpEstadoUF = MpEstadoUF.valueOf(mpCepXML.getEstado().toUpperCase());
				if (null == mpEstadoUF)
					mpEstadoUF = MpEstadoUF.XX;
			}
			this.mpClienteConsignado.getMpEnderecoLocal().setMpEstadoUF(mpEstadoUF);
			
			// Trata Cidade! ...
			MpTabelaInterna mpCidade = new MpTabelaInterna();
			if (null == mpCepXML.getCidade()) {
				MpFacesUtil.addErrorMessage("CEP WebService... CIDADE sem retorno !");
				return;
			}
			//
			mpCidade = mpTabelaInternaService.findByMpTipoTabelaInternaAndDescricao(MpTipoTabelaInterna.TB_CIDADE,
																					mpCepXML.getCidade().trim());
			if (null == mpCidade) {
				mpCidade = new MpTabelaInterna();
				mpCidade.setMpTipoTabelaInterna(MpTipoTabelaInterna.TB_CIDADE);
				mpCidade.setCodigo(mpCepXML.getCidade().trim().toUpperCase());
				mpCidade.setDescricao(mpCepXML.getCidade().trim());
					
				mpCidade = mpTabelaInternaRepository.saveAndFlush(mpCidade);
				//
				this.mpCidadeList = this.mpTabelaInternaRepository.findByMpTipoTabelaInternaOrderByCodigo(
																				   MpTipoTabelaInterna.TB_CIDADE);
				MpFacesUtil.addErrorMessage("CEP WebService... CIDADE não encontrada ! ( " + mpCepXML.getCidade());
			}
			//
			this.mpClienteConsignado.getMpEnderecoLocal().setMpCidade(mpCidade);			
			//
		}
    }
	
	// --- 
			
    public void oncapture(CaptureEvent captureEvent) {
    	//
        this.gravaFotoLink(captureEvent.getData());		
    }
    
	public void handleFileUpload(FileUploadEvent event) {
		//
        try {
			this.arquivoContent = new DefaultStreamedContent(event.getFile().getInputstream(),
													"image/jpeg", event.getFile().getFileName());
			
	        this.gravaFotoLink(MpAppUtil.getFileContents(event.getFile().getInputstream()));
			//
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
	}
	
	public void gravaFotoLink(byte[] data) {
		//
        this.mpClienteConsignado.setFotoLink(MpAppUtil.getRandomName());

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources" + 
        							File.separator + "static" +
                                    File.separator + "images" + File.separator + "fotos" + File.separator +
                                    "cliente_consignado_" + this.mpClienteConsignado.getFotoLink() + ".jpeg";
        // 
        FileImageOutputStream imageOutput;
        
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        }
        catch(IOException e) {
            throw new FacesException("Erro na captura da IMAGEM.", e);
        }
		//
        MpFacesUtil.addInfoMessage("Arquivo... carregado com sucesso.");
	}

	// ---
	
	public void novoDocumento() {
		//
//		System.out.println("MpCadastroClienteConsignadoBean.novoDocumento()");

		this.mpDocumentoDigitalSel = new MpDocumentoDigital();
		this.mpDocumentoDigitalSelAnt = new MpDocumentoDigital();

		this.mpDocumentoDigitalSel.setMpClienteConsignado(this.mpClienteConsignado);
		this.mpDocumentoDigitalSel.setDescricao("");
		this.mpDocumentoDigitalSel.setArquivoLink("");
		//
		this.txtModoDoc = "( Novo )";
	}
	
	public void alteraDocumento() {
		//
//		System.out.println("MpCadastroClienteConsignadoBean.alteraDocumento()");

		this.mpDocumentoDigitalSelAnt = this.mpDocumentoDigitalSel;
		//
		this.txtModoDoc = "( Edição )";
	}
	
	public void gravaDocumento() {
		//
//		System.out.println("MpCadastroClienteConsignadoBean.gravaDocumento()");

		if (this.mpDocumentoDigitalSel.getDescricao().isEmpty()) {
			//
	        MpFacesUtil.addInfoMessage("Informar Descrição do Documento");
	        return;
		}
		//
		if (!this.mpDocumentoDigitalList.isEmpty()) {
			//
			int contX = 0;
			for (MpDocumentoDigital mpDocumentoDigitalX : this.mpDocumentoDigitalList) {
				//
				contX++;
				
				if (mpDocumentoDigitalX.getDescricao().trim().toLowerCase().equals(
											this.mpDocumentoDigitalSel.getDescricao().trim().toLowerCase())) {
					//
					if (this.txtModoDoc.equals("( Novo )")) {
						MpFacesUtil.addInfoMessage("Descrição... já foi informada no Documento.");
//						return;
					} else {
						//
						int indexX = this.mpDocumentoDigitalList.indexOf(this.mpDocumentoDigitalSel);
						if (contX == indexX)
							assert(true); // nop
						else {
							//
							MpFacesUtil.addInfoMessage("Descrição... já foi informada no Documento!");
//							return;
						}
					}
				}
			}
		}
		//
		if (this.txtModoDoc.equals("( Novo )")) {
			//
			this.mpDocumentoDigitalList.add(mpDocumentoDigitalSel);

			MpFacesUtil.addInfoMessage("Documento... Incluido com sucesso.");
		} else {
			//
			int indexX = this.mpDocumentoDigitalList.indexOf(this.mpDocumentoDigitalSelAnt);
			if (indexX >= 0) {
				this.mpDocumentoDigitalList.remove(this.mpDocumentoDigitalSelAnt);
				this.mpDocumentoDigitalList.add(indexX, this.mpDocumentoDigitalSel);

				MpFacesUtil.addInfoMessage("Documento... Alterado com sucesso.");
			}
		}
	}
	
	public void deleteDocumento() {
		//
//		System.out.println("MpCadastroClienteConsignadoBean.deleteDocumento()");

		this.mpDocumentoDigitalList.remove(mpDocumentoDigitalSel);
		this.mpDocumentoDigitalListDel.add(mpDocumentoDigitalSel);

		MpFacesUtil.addInfoMessage("Documento Digital... excluído com sucesso.");
	}

	// ---
	
	public void novoClienteObservacao() {
		//
//		System.out.println("MpCadastroClienteConsignadoBean.novoClienteObservacao()");

		this.mpClienteObservacaoSel = new MpClienteObservacao();
		this.mpClienteObservacaoSelAnt = new MpClienteObservacao();

		this.mpClienteObservacaoSel.setMpClienteConsignado(this.mpClienteConsignado);
		this.mpClienteObservacaoSel.setObservacao("");
		//
		this.txtModoObs = "( Novo )";
	}
	
	public void alteraClienteObservacao() {
		//
//		System.out.println("MpCadastroClienteConsignadoBean.alteraClienteObservacao()");

		this.mpClienteObservacaoSelAnt = this.mpClienteObservacaoSel;
		//
		this.txtModoObs = "( Edição )";
	}
	
	public void gravaClienteObservacao() {
		//
//		System.out.println("MpCadastroClienteConsignadoBean.gravaClienteObservacao()");

		if (this.mpClienteObservacaoSel.getObservacao().isEmpty()) {
			//
	        MpFacesUtil.addInfoMessage("Informar Observacao");
	        return;
		}
		//
		if (this.txtModoObs.equals("( Novo )")) {
			//
			this.mpClienteObservacaoList.add(mpClienteObservacaoSel);

			MpFacesUtil.addInfoMessage("Observação... Incluida com sucesso.");
		} else {
			//
			int indexX = this.mpClienteObservacaoList.indexOf(this.mpClienteObservacaoSelAnt);
			if (indexX >= 0) {
				this.mpClienteObservacaoList.remove(this.mpClienteObservacaoSelAnt);
				this.mpClienteObservacaoList.add(indexX, this.mpClienteObservacaoSel);

				MpFacesUtil.addInfoMessage("Observação... Alterada com sucesso.");
			}
		}
	}
	
	public void deleteClienteObservacao() {
		//
//		System.out.println("MpCadastroClienteConsignadoBean.deleteClienteObservacao()");

		this.mpClienteObservacaoList.remove(mpClienteObservacaoSel);

		MpFacesUtil.addInfoMessage("Observação... excluída com sucesso.");
	}
	
	// ---

	public void gravaClienteConvenioValor() {
		//
		System.out.println("MpCadastroClienteConsignadoBean.gravaClienteConvenioValor() ( " +
														this.mpClienteConsignado.getMpDadosConvenio().getValorMR());

		MpFacesUtil.addInfoMessage("Valor Convênio... Atualizado! ( " + 
														this.mpClienteConsignado.getMpDadosConvenio().getValorMR());
	}
	
	
	// ---
	    
	public void handleFileUploadDoc(FileUploadEvent event) {
		//
        try {
			this.arquivoContent = new DefaultStreamedContent(event.getFile().getInputstream(),
																"image/jpeg", event.getFile().getFileName());
			
	        this.gravaDocLink(MpAppUtil.getFileContents(event.getFile().getInputstream()));
			//
		} catch (IOException e) {
			e.printStackTrace();
		}
		//
	}
	
	public void gravaDocLink(byte[] data) {
		//
        this.mpDocumentoDigitalSel.setArquivoLink(MpAppUtil.getRandomName());

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String newFileName = externalContext.getRealPath("") + File.separator + "resources" + 
        							File.separator + "static" + File.separator + "images" + 
        							File.separator + "arquivos" + File.separator +
                                    "cliente_consignado_" + this.mpDocumentoDigitalSel.getArquivoLink() + ".jpeg";
        // 
        FileImageOutputStream imageOutput;
        
        try {
            imageOutput = new FileImageOutputStream(new File(newFileName));
            imageOutput.write(data, 0, data.length);
            imageOutput.close();
        }
        catch(IOException e) {
            throw new FacesException("Erro na captura do DOCUMENTO.", e);
        }
		//
        MpFacesUtil.addInfoMessage("Documento... carregado com sucesso.");
	}
	
    public StreamedContent trataFileDownloadDoc() { 
    	//    	
    	this.fileSC = new DefaultStreamedContent();
    	//
    	if (null == this.mpDocumentoDigitalSel
    	||	null == this.mpDocumentoDigitalSel.getArquivoLink())
 	       return null;
    	else {
            //
            String newFileName = File.separator + "resources" + File.separator + "static" +
            						File.separator + "images" +	File.separator + "arquivos" + File.separator +
            						"cliente_consignado_" + this.mpDocumentoDigitalSel.getArquivoLink() + ".jpeg";
            // 
            InputStream stream = FacesContext.getCurrentInstance().getExternalContext().
            																	getResourceAsStream(newFileName);
            this.fileSC = new DefaultStreamedContent(stream, "image/jpg", "downloaded_" + 
            											this.mpDocumentoDigitalSel.getDescricao().trim() + 
            											this.mpClienteConsignado.getCpf().trim() + ".jpg");
        }
    	//
    	return this.fileSC;
    }	
	
	// ------------- Trata Botões -------------
	  
	public void mpListener(ActionEvent event) {
		//
		this.mpClienteConsignado = (MpClienteConsignado)event.getComponent().getAttributes().
																				get("ClienteConsignadoSelecionado");

		this.idade = MpAppUtil.calculaIdadeAnos(this.mpClienteConsignado.getDataNascimento());
		this.tempoDesde = MpAppUtil.calculaIdadeAnos(this.mpClienteConsignado.getDataDesde());
		
		this.mpDocumentoDigitalList = this.mpClienteConsignado.getMpDocumentoDigitalList();
		this.mpDocumentoDigitalListAnt = this.mpDocumentoDigitalList;
		this.mpDocumentoDigitalListDel = new ArrayList<MpDocumentoDigital>();
		
		this.mpClienteObservacaoList = this.mpClienteConsignado.getMpClienteObservacaoList();
		this.mpClienteObservacaoListAnt = this.mpClienteObservacaoList;
		this.mpClienteObservacaoListDel = new ArrayList<MpClienteObservacao>();
		//
//		System.out.println("MpCadastroClienteConsignadoBean.mpListener() ( Event = " + event.toString());
//		
//		this.mpEdit();
	}	

	public void mpListenerProposta(ActionEvent event) {
		//
		MpProposta mpPropostaSel = (MpProposta)event.getComponent().getAttributes().get("PropostaSelecionado");

		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/Proposta?idProposta=" +
																						mpPropostaSel.getId());
			//
		} catch (IOException e) {
			MpFacesUtil.addInfoMessage("Erro Redirecionameto Proposta ... contactar o Suporte ! ");
		}
	}	

	public void mpFechar(CloseEvent event) {
		//
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/Dashboard");
			
		} catch (IOException e) {
			MpFacesUtil.addInfoMessage("Erro Redirecionameto Fechar ... contactar o Suporte ! ");
		}
	}	
		
	public void mpNew() {
		//
		this.setMpClienteConsignadoAnt(this.mpClienteConsignado);
		
		this.limpar();	
		//
		this.setIndEditavel(false);
		//
		this.txtModoTela = "( Novo )";
	}
	
	public void mpEdit() {
		//
		if (null == this.mpClienteConsignado.getId()) return;
		//
		this.setMpClienteConsignadoAnt(this.mpClienteConsignado);
		
		this.indEditavel = false;
		//
		this.txtModoTela = "( Edição )";
	}
	
	public void mpView() {
		//
		if (null == this.mpClienteConsignado.getId()) return;
		//
		this.setMpClienteConsignadoAnt(this.mpClienteConsignado);
		
		this.indEditavel = true;
		//
		this.txtModoTela = "( Visualização )";
	}
	
	public void mpDelete() {
		//
		if (null == this.mpClienteConsignado.getId()) return;
		//
		if (!this.mpClienteConsignado.getMpDocumentoDigitalList().isEmpty()) {
			//
			MpFacesUtil.addInfoMessage("Cliente possui documentos! Favor verificar.");
			return;
		}
		if (!this.mpClienteConsignado.getMpClienteObservacaoList().isEmpty()) {
			//
			MpFacesUtil.addInfoMessage("Cliente possui observações! Favor verificar.");
			return;
		}
		//
		try {
			this.mpClienteConsignadoRepository.delete(this.mpClienteConsignado);
			
			this.mpClienteConsignadoList.remove(this.mpClienteConsignado);
			//
			MpFacesUtil.addInfoMessage(this.txtTitulo + "... " + this.mpClienteConsignado.getNome() +
																	 			" ... excluído com sucesso.");
		} catch (MpNegocioException ne) {
			MpFacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void mpGrava() {
		//
//		System.out.println("MpCadastroClienteConsignadoBean.GRAVA() (" + this.getIndEditavel() + "/" + this.txtModoTela);
		System.out.println("MpCadastroClienteConsignadoBean.GRAVA() (" + this.mpClienteConsignado.getMpEstadoCivil());
		// Trata Defaults...caso não seja informado ! 
		if (null == this.mpClienteConsignado.getMpEstadoCivil())
			this.mpClienteConsignado.setMpEstadoCivil(MpEstadoCivil.DEFINIR);
		if (null == this.mpClienteConsignado.getMpStatus())
			this.mpClienteConsignado.setMpStatus(MpStatus.DEFINIR);
		if (null == this.mpClienteConsignado.getMpSexo())
			this.mpClienteConsignado.setMpSexo(MpSexo.DEFINIR);
		//
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
		this.mpClienteConsignadoList = this.mpClienteConsignadoService.findAllByNome();

		this.setMpClienteConsignadoAnt(this.mpClienteConsignado);
		//
		this.indEditavel = true;
		//
		this.txtModoTela = "";
	}
	
	public void mpDesfaz() {
		//
//		System.out.println("MpCadastroClienteConsignadoBean.DESFAZ()");

		this.mpClienteConsignado = this.mpClienteConsignadoAnt;
		this.mpDocumentoDigitalListAnt = this.mpDocumentoDigitalList;
		this.mpClienteObservacaoListAnt = this.mpClienteObservacaoList;
		
		this.indEditavel = true;
		//
		this.txtModoTela = "";
	}
	
	// ---

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean filterByValor(Object value, Object filter, Locale locale) {
    	//
		System.out.println("MpCadastroClienteConsignadoBean.FILTER()");

    	String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
        //
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
 	
	// ---

	public MpClienteConsignado getMpClienteConsignado() { return mpClienteConsignado; }
	public void setMpClienteConsignado(MpClienteConsignado mpClienteConsignado) { 
																this.mpClienteConsignado = mpClienteConsignado; }

	public Integer getIdade() { return idade; }
	public void setIdade(Integer idade) { this.idade = idade; }

	public Integer getTempoDesde() { return tempoDesde; }
	public void setTempoDesde(Integer tempoDesde) { this.tempoDesde = tempoDesde; }

	public MpClienteConsignado getMpClienteConsignadoAnt() { return mpClienteConsignadoAnt; }
	public void setMpClienteConsignadoAnt(MpClienteConsignado mpClienteConsignadoAnt) {
		try {
			if (null==this.mpClienteConsignado) return;
			//
			this.mpClienteConsignadoAnt = (MpClienteConsignado) this.mpClienteConsignado.clone();
			this.mpClienteConsignadoAnt.setId(this.mpClienteConsignado.getId());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isEditando() { return this.mpClienteConsignado.getId() != null; }
	
	public boolean isIndEditavel() { return indEditavel; }
	public boolean getIndEditavel() { return indEditavel; }
	public void setIndEditavel(Boolean indEditavel) { this.indEditavel = indEditavel; }
		
	public String getTxtTitulo() { return txtTitulo; }
	public void setTxtTitulo(String txtTitulo) { this.txtTitulo = txtTitulo; }
	
	public String getTxtModoTela() { return txtModoTela; }
	public void setTxtModoTela(String txtModoTela) { this.txtModoTela = txtModoTela; }
	
	public String getTxtModoDoc() { return txtModoDoc; }
	public void setTxtModoDoc(String txtModoDoc) { this.txtModoDoc = txtModoDoc; }
	
	public String getTxtModoObs() { return txtModoObs; }
	public void setTxtModoObs(String txtModoObs) { this.txtModoObs = txtModoObs; }
	
	//
	
	public List<MpClienteConsignado> getMpClienteConsignadoList() { return mpClienteConsignadoList; }
	
	public List<MpClienteConsignado> getFiltroMpClienteConsignadoList() { return filtroMpClienteConsignadoList; }
	public void setFiltroMpClienteConsignadoList(List<MpClienteConsignado> filtroMpClienteConsignadoList) {
											this.filtroMpClienteConsignadoList = filtroMpClienteConsignadoList; }
	
	public MpStatus getMpStatus() {	return mpStatus; }
	public void setMpStatus(MpStatus mpStatus) { this.mpStatus = mpStatus; }
	public List<MpStatus> getMpStatusList() { return mpStatusList; }

	public MpSexo getMpSexo() { return mpSexo; }
	public void setMpSexo(MpSexo mpSexo) { this.mpSexo = mpSexo; }
	public List<MpSexo> getMpSexoList() { return mpSexoList; }

	public MpEstadoCivil getMpEstadoCivil() { return mpEstadoCivil;	}
	public void setMpEstadoCivil(MpEstadoCivil mpEstadoCivil) {	this.mpEstadoCivil = mpEstadoCivil; }
	public List<MpEstadoCivil> getMpEstadoCivilList() {	return mpEstadoCivilList; }

	public MpEstadoUF getMPEstadoUF() { return mpEstadoUF; }
	public void setMpEstadoUF(MpEstadoUF mpEstadoUF) { this.mpEstadoUF = mpEstadoUF; }
	public MpEstadoUF getMPEstadoUFIdent() { return mpEstadoUFIdent; }
	public void setMpEstadoUFIdent(MpEstadoUF mpEstadoUFIdent) { this.mpEstadoUFIdent = mpEstadoUFIdent; }
	public List<MpEstadoUF> getMpEstadoUFList() { return mpEstadoUFList; }
	
	public MpTabelaInterna getMpIndicacao() { return mpIndicacao; }
	public void setMpIndicacao(MpTabelaInterna mpIndicacao) { this.mpIndicacao = mpIndicacao; }
	public List<MpTabelaInterna> getMpIndicacaoList() { return mpIndicacaoList; }
	
	public MpTabelaInterna getMpOrgaoEmissor() { return mpOrgaoEmissor; }
	public void setMpOrgaoEmissor(MpTabelaInterna mpOrgaoEmissor) { this.mpOrgaoEmissor = mpOrgaoEmissor; }
	public List<MpTabelaInterna> getMpOrgaoEmissorList() { return mpOrgaoEmissorList; }
	
	public MpTabelaInterna getMpCidade() { return mpCidade; }
	public void setMpCidade(MpTabelaInterna mpCidade) { this.mpCidade = mpCidade; }
	public List<MpTabelaInterna> getMpCidadeList() { return mpCidadeList; }
	
	public MpTabelaInterna getMpTipoTelefone() { return mpTipoTelefone; }
	public MpTabelaInterna getMpTipoTelefone1() { return mpTipoTelefone1; }
	public void setMpTipoTelefone(MpTabelaInterna mpTipoTelefone) { this.mpTipoTelefone = mpTipoTelefone; }
	public List<MpTabelaInterna> getMpTipoTelefoneList() { return mpTipoTelefoneList; }
	
	public MpTabelaInterna getMpBanco() { return mpBanco; }
	public void setMpBanco(MpTabelaInterna mpBanco) { this.mpBanco = mpBanco; }
	public List<MpTabelaInterna> getMpBancoList() { return mpBancoList; }
	
	public MpTabelaInterna getMpConvenio() { return mpConvenio; }
	public void setMpConvenio(MpTabelaInterna mpConvenio) { this.mpConvenio = mpConvenio; }
	public List<MpTabelaInterna> getMpConvenioList() { return mpConvenioList; }
	
	public MpTabelaInterna getMpEspecieBeneficio() { return mpEspecieBeneficio; }
	public void setMpEspecieBeneficio(MpTabelaInterna mpEspecieBeneficio) { 
																	this.mpEspecieBeneficio = mpEspecieBeneficio; }
	public List<MpTabelaInterna> getMpEspecieBeneficioList() { return mpEspecieBeneficioList; }
	
	public MpTabelaInterna getMpOrgaoConvenio() { return mpOrgaoConvenio; }
	public void setMpOrgaoConvenio(MpTabelaInterna mpOrgaoConvenio) { this.mpOrgaoConvenio = mpOrgaoConvenio; }
	public List<MpTabelaInterna> getMpOrgaoConvenioList() { return mpOrgaoConvenioList; }
	
	public MpTabelaInterna getMpTipoRg() { return mpTipoRg; }
	public void setMpTipoRg(MpTabelaInterna mpTipoRg) { this.mpTipoRg = mpTipoRg; }
	public List<MpTabelaInterna> getMpTipoRgList() { return mpTipoRgList; }

	// 
	
	public MpContaTipo getMpContaTipo() { return mpContaTipo; }
	public void setMpContaTipo(MpContaTipo mpContaTipo) { this.mpContaTipo = mpContaTipo; }
	public List<MpContaTipo> getMpContaTipoList() { return mpContaTipoList; }
	
	public MpTipoConta getMpTipoConta() { return mpTipoConta; }
	public void setMpTipoConta(MpTipoConta mpTipoConta) { this.mpTipoConta = mpTipoConta; }
	public List<MpTipoConta> getMpTipoContaList() { return mpTipoContaList; }
	
	// ---
	
    public UploadedFile getArquivoUpload() { return arquivoUpload; }
    public void setArquivoUpload(UploadedFile arquivoUpload) { this.arquivoUpload = arquivoUpload; }
    
	public StreamedContent getArquivoContent() { return arquivoContent; }
    public void setArquivoContent(StreamedContent arquivoContent) { 
    														this.arquivoContent = arquivoContent; }
	public boolean isArquivoContent() { return getArquivoContent() != null; }
	
	public byte[] getArquivoBytes() { return arquivoBytes; }
    public void setArquivoBytes(byte[] arquivoBytes) { this.arquivoBytes = arquivoBytes; }

	public MpDocumentoDigital getMpDocumentoDigitalSel() { return mpDocumentoDigitalSel; }
	public void setMpDocumentoDigitalSel(MpDocumentoDigital mpDocumentoDigitalSel) { 
														this.mpDocumentoDigitalSel = mpDocumentoDigitalSel; }
	public MpDocumentoDigital getMpDocumentoDigitalSelAnt() { return mpDocumentoDigitalSelAnt; }
	public void setMpDocumentoDigitalSelAnt(MpDocumentoDigital mpDocumentoDigitalSelAnt) { 
														this.mpDocumentoDigitalSelAnt = mpDocumentoDigitalSelAnt; }
	
	public List<MpDocumentoDigital> getMpDocumentoDigitalList() { return mpDocumentoDigitalList; }
	public void setMpDocumentoDigitalList(List<MpDocumentoDigital> mpDocumentoDigitalList) { 
															this.mpDocumentoDigitalList = mpDocumentoDigitalList; }
		
	public List<MpDocumentoDigital> getMpDocumentoDigitalListAnt() { return mpDocumentoDigitalListAnt; }
	public void setMpDocumentoDigitalListAnt(List<MpDocumentoDigital> mpDocumentoDigitalListAnt) { 
													this.mpDocumentoDigitalListAnt = mpDocumentoDigitalListAnt; }
	
    public StreamedContent getFileSC() { return fileSC; }

	public MpClienteObservacao getMpClienteObservacaoSel() { return mpClienteObservacaoSel; }
	public void setMpClienteObservacaoSel(MpClienteObservacao mpClienteObservacaoSel) { 
														this.mpClienteObservacaoSel = mpClienteObservacaoSel; }
	public MpClienteObservacao getMpClienteObservacaoSelAnt() { return mpClienteObservacaoSelAnt; }
	public void setMpClienteObservacaoSelAnt(MpClienteObservacao mpClienteObservacaoSelAnt) { 
														this.mpClienteObservacaoSelAnt = mpClienteObservacaoSelAnt; }
	
	public List<MpClienteObservacao> getMpClienteObservacaoList() { return mpClienteObservacaoList; }
	public void setMpClienteObservacaoList(List<MpClienteObservacao> mpClienteObservacaoList) { 
													this.mpClienteObservacaoList = mpClienteObservacaoList; }
		
	public List<MpClienteObservacao> getMpClienteObservacaoListAnt() { return mpClienteObservacaoListAnt; }
	public void setMpClienteObservacaoListAnt(List<MpClienteObservacao> mpClienteObservacaoListAnt) { 
													this.mpClienteObservacaoListAnt = mpClienteObservacaoListAnt; }

}