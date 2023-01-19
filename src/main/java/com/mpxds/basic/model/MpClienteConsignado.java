package com.mpxds.basic.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.br.CPF;

import com.mpxds.basic.model.enums.MpEstadoCivil;
import com.mpxds.basic.model.enums.MpEstadoUF;
import com.mpxds.basic.model.enums.MpSexo;
import com.mpxds.basic.model.enums.MpStatus;

@Entity
@Table(name = "mp_cliente_consignado")
public class MpClienteConsignado extends MpEntity {
	//
	private static final long serialVersionUID = 1L;

	@Column(nullable = false, length = 100)
	private String nome; 
	
	@ManyToOne
	@JoinColumn(name = "mpIndicacao_id")
	private MpTabelaInterna mpIndicacao; // TAB_0001

	@Column(nullable = true, length = 100)
	private String discriminacao; 
	
	@Column(nullable = true, length = 255)
	private String email;

	@Column(nullable = true, length = 50)
	private String telefone;

	@ManyToOne
	@JoinColumn(name = "mptelefone_id", nullable = true)
	private MpTabelaInterna mpTipoTelefone; // TAB_0004
	
	@Column(nullable = true, length = 50)
	private String telefone1;

	@ManyToOne
	@JoinColumn(name = "mptelefone1_id", nullable = true)
	private MpTabelaInterna mpTipoTelefone1; // TAB_0004
	
	@Column(nullable = true, length = 50)
	private String celular;
	
	@CPF
	@Column(nullable = true, length = 15)
	private String cpf;

	@ManyToOne
	@JoinColumn(name = "mpTipoRg1_id")
	private MpTabelaInterna mpTipoRg1;
	
	@Column(nullable = true, length = 100)
	private String identidade1;

	@ManyToOne
	@JoinColumn(name = "mpOrgaoEmissor1_id")
	private MpTabelaInterna mpOrgaoEmissor1;
	
	@Past(message="Data futuro inválida!")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emissao1", nullable = true)
	private Date dataEmissao1;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 2)
	private MpEstadoUF mpEstadoUFIdent1;

	@ManyToOne
	@JoinColumn(name = "mpTipoRg_id")
	private MpTabelaInterna mpTipoRg;
	
	@Column(nullable = true, length = 100)
	private String identidade;

	@ManyToOne
	@JoinColumn(name = "mpOrgaoEmissor_id")
	private MpTabelaInterna mpOrgaoEmissor;
	
	@Past(message="Data futuro inválida!")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_emissao", nullable = true)
	private Date dataEmissao;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 2)
	private MpEstadoUF mpEstadoUFIdent;
		
	@Column(name = "tempo_Residencia_Anos", nullable = true, length = 3)
	private String tempoResidenciaAnos;
	
	@Column(name = "tempo_Residencia_Meses", nullable = true, length = 3)
	private String tempoResidenciaMeses;
	
	@Column(name = "nome_Conjuge", nullable = true, length = 100)
	private String nomeConjuge; 
	
	@Column(name = "nome_Pai", nullable = true, length = 100)
	private String nomePai; 
	
	@Column(name = "nome_Mae", nullable = true, length = 100)
	private String nomeMae; 
	
	@Column(name = "cidade_Naturalidade", nullable = true, length = 100)
	private String cidadeNaturalidade; 

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, length = 2)
	private MpEstadoUF mpEstadoUFNaturalidade;
	
	@Column(nullable = true, length = 1500)
	private String observacao;
	
	@Past(message="Data futuro inválida!")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_nascimento", nullable = true)
	private Date dataNascimento;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_desde", nullable = true)
	private Date dataDesde;

//	@NotNull(message = "Por favor, informe o STATUS")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	private MpStatus mpStatus;
	
//	@NotNull(message = "Por favor, informe o SEXO")
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 15)
	private MpSexo mpSexo;
	
//	@NotNull(message = "Por favor, informe o ESTADO CIVIL")
	@Enumerated(EnumType.STRING)
	@Column(name = "mpestado_civil", nullable = false, length = 15)
	private MpEstadoCivil mpEstadoCivil;
	
	@ManyToOne
	@JoinColumn(name = "mpBanco_id", nullable = true)
	private MpTabelaInterna mpBanco; // TAB_0005
	
	@ManyToOne
	@JoinColumn(name = "mpBanco1_id", nullable = true)
	private MpTabelaInterna mpBanco1; // TAB_0005
	
	@ManyToOne
	@JoinColumn(name = "mpBanco2_id", nullable = true)
	private MpTabelaInterna mpBanco2; // TAB_0005
	
	@ManyToOne
	@JoinColumn(name = "mpBanco3_id", nullable = true)
	private MpTabelaInterna mpBanco3; // TAB_0005
	
	@ManyToOne
	@JoinColumn(name = "mpBanco4_id", nullable = true)
	private MpTabelaInterna mpBanco4; // TAB_0005

	@Embedded
	private MpDadosBancario mpDadosBancario = new MpDadosBancario();

	@Embedded
	private MpDadosConvenio mpDadosConvenio = new MpDadosConvenio();

	@Embedded
	private MpEnderecoLocal mpEnderecoLocal = new MpEnderecoLocal();

	@Column(name = "foto_link", nullable = true)
	private String fotoLink;

	@Column(name = "ind_prospeccao", nullable = true)
	private Boolean indProspeccao;

	@Column(name = "ind_whatsapp", nullable = true)
	private Boolean indWhatsapp;

	@Column(name = "ind_send_whatsapp", nullable = true)
	private Boolean indSendWhatsapp;
		
	@OneToMany(mappedBy = "mpClienteConsignado", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<MpDocumentoDigital> mpDocumentoDigitalList = new ArrayList<MpDocumentoDigital>();
	
	@OneToMany(mappedBy = "mpClienteConsignado", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<MpClienteObservacao> mpClienteObservacaoList = new ArrayList<MpClienteObservacao>();
	
	@OneToMany(mappedBy = "mpClienteConsignado", cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<MpProposta> mpPropostaList = new ArrayList<MpProposta>();

	// ---------------

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public MpTabelaInterna getMpIndicacao() { return mpIndicacao; }
	public void setMpIndicacao(MpTabelaInterna mpIndicacao) { this.mpIndicacao = mpIndicacao; }

	public String getDiscriminacao() { return discriminacao; }
	public void setDiscriminacao(String discriminacao) { this.discriminacao = discriminacao; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

	public String getTelefone() { return telefone; }
	public void setTelefone(String telefone) { this.telefone = telefone; }

	public MpTabelaInterna getMpTipoTelefone() { return mpTipoTelefone; }
	public void setMpTipoTelefone(MpTabelaInterna mpTipoTelefone) { this.mpTipoTelefone = mpTipoTelefone; }

	public String getTelefone1() { return telefone1; }
	public void setTelefone1(String telefone1) { this.telefone1 = telefone1; }

	public MpTabelaInterna getMpTipoTelefone1() { return mpTipoTelefone1; }
	public void setMpTipoTelefone1(MpTabelaInterna mpTipoTelefone1) { this.mpTipoTelefone1 = mpTipoTelefone1; }

	public String getCelular() { return celular; }
	public void setCelular(String celular) { this.celular = celular; }
	
	public String getCpf() { return cpf; }
	public void setCpf(String cpf) { this.cpf = cpf; }

	public MpTabelaInterna getMpTipoRg() { return mpTipoRg; }
	public void setMpTipoRg(MpTabelaInterna mpTipoRg) { this.mpTipoRg = mpTipoRg; }

	public String getIdentidade() { return identidade; }
	public void setIdentidade(String identidade) { this.identidade = identidade; }

	public MpTabelaInterna getMpOrgaoEmissor() { return mpOrgaoEmissor; }
	public void setMpOrgaoEmissor(MpTabelaInterna mpOrgaoEmissor) { this.mpOrgaoEmissor = mpOrgaoEmissor; }

	public Date getDataEmissao() { return dataEmissao; }
	public void setDataEmissao(Date dataEmissao) { this.dataEmissao = dataEmissao; }

	public MpEstadoUF getMpEstadoUFIdent() { return mpEstadoUFIdent; }
	public void setMpEstadoUFIdent(MpEstadoUF mpEstadoUFIdent) { this.mpEstadoUFIdent = mpEstadoUFIdent; }

	public MpTabelaInterna getMpTipoRg1() { return mpTipoRg1; }
	public void setMpTipoRg1(MpTabelaInterna mpTipoRg1) { this.mpTipoRg1 = mpTipoRg1; }

	public String getIdentidade1() { return identidade1; }
	public void setIdentidade1(String identidade1) { this.identidade1 = identidade1; }

	public MpTabelaInterna getMpOrgaoEmissor1() { return mpOrgaoEmissor1; }
	public void setMpOrgaoEmissor1(MpTabelaInterna mpOrgaoEmissor1) { this.mpOrgaoEmissor1 = mpOrgaoEmissor1; }

	public Date getDataEmissao1() { return dataEmissao1; }
	public void setDataEmissao1(Date dataEmissao1) { this.dataEmissao1 = dataEmissao1; }

	public MpEstadoUF getMpEstadoUFIdent1() { return mpEstadoUFIdent1; }
	public void setMpEstadoUFIdent1(MpEstadoUF mpEstadoUFIdent1) { this.mpEstadoUFIdent1 = mpEstadoUFIdent1; }

	public String getTempoResidenciaAnos() { return tempoResidenciaAnos; }
	public void setTempoResidenciaAnos(String tempoResidenciaAnos) { this.tempoResidenciaAnos = tempoResidenciaAnos; }

	public String getTempoResidenciaMeses() { return tempoResidenciaMeses; }
	public void setTempoResidenciaMeses(String tempoResidenciaMeses) { 
																	this.tempoResidenciaMeses = tempoResidenciaMeses; }

	public String getNomeConjuge() { return nomeConjuge; }
	public void setNomeConjuge(String nomeConjuge) { this.nomeConjuge = nomeConjuge; }

	public String getNomePai() { return nomePai; }
	public void setNomePai(String nomePai) { this.nomePai = nomePai; }

	public String getNomeMae() { return nomeMae; }
	public void setNomeMae(String nomeMae) { this.nomeMae = nomeMae; }

	public String getCidadeNaturalidade() { return cidadeNaturalidade; }
	public void setCidadeNaturalidade(String cidadeNaturalidade) { this.cidadeNaturalidade = cidadeNaturalidade; }

	public MpEstadoUF getMpEstadoUFNaturalidade() { return mpEstadoUFNaturalidade; }
	public void setMpEstadoUFNaturalidade(MpEstadoUF mpEstadoUFNaturalidade) { 
															this.mpEstadoUFNaturalidade = mpEstadoUFNaturalidade; }

	public String getObservacao() {	return observacao; }
	public void setObservacao(String observacao) { this.observacao = observacao; }

	public Date getDataNascimento() { return dataNascimento; }
	public void setDataNascimento(Date dataNascimento) { this.dataNascimento = dataNascimento; }

	public Date getDataDesde() { return dataDesde; }
	public void setDataDesde(Date dataDesde) { this.dataDesde = dataDesde; }
		
	public MpStatus getMpStatus() {	return mpStatus; }
	public void setMpStatus(MpStatus mpStatus) { this.mpStatus = mpStatus; }
	
	public MpSexo getMpSexo() { return mpSexo; 	}
	public void setMpSexo(MpSexo mpSexo) { this.mpSexo = mpSexo; }
	
	public MpEstadoCivil getMpEstadoCivil() { return mpEstadoCivil; }
	public void setMpEstadoCivil(MpEstadoCivil mpEstadoCivil) {	this.mpEstadoCivil = mpEstadoCivil; }
	
	public MpTabelaInterna getMpBanco() { return mpBanco; }
	public void setMpBanco(MpTabelaInterna mpBanco) { this.mpBanco = mpBanco; }
	
	public MpTabelaInterna getMpBanco1() { return mpBanco1; }
	public void setMpBanco1(MpTabelaInterna mpBanco1) { this.mpBanco1 = mpBanco1; }
	
	public MpTabelaInterna getMpBanco2() { return mpBanco2; }
	public void setMpBanco2(MpTabelaInterna mpBanco2) { this.mpBanco2 = mpBanco2; }
	
	public MpTabelaInterna getMpBanco3() { return mpBanco3; }
	public void setMpBanco3(MpTabelaInterna mpBanco3) { this.mpBanco3 = mpBanco3; }
	
	public MpTabelaInterna getMpBanco4() { return mpBanco4; }
	public void setMpBanco4(MpTabelaInterna mpBanco4) { this.mpBanco4 = mpBanco4; }
	
	public MpDadosBancario getMpDadosBancario() { return mpDadosBancario; }
	public void setMpDadosBancario(MpDadosBancario mpDadosBancario) { this.mpDadosBancario = mpDadosBancario; }
	
	public MpDadosConvenio getMpDadosConvenio() { return mpDadosConvenio; }
	public void setMpDadosConvenio(MpDadosConvenio mpDadosConvenio) { this.mpDadosConvenio = mpDadosConvenio; }
	
	public MpEnderecoLocal getMpEnderecoLocal() { return mpEnderecoLocal; }
	public void setMpEnderecoLocal(MpEnderecoLocal mpEnderecoLocal) { this.mpEnderecoLocal = mpEnderecoLocal; }
	
    public String getFotoLink() { return this.fotoLink; }
    public void setFotoLink(String fotoLink) { this.fotoLink = fotoLink; }
	
    public Boolean getIndProspeccao() { return this.indProspeccao; }
    public void setIndProspeccao(Boolean indProspeccao) { this.indProspeccao = indProspeccao; }
	
    public Boolean getIndWhatsapp() { return this.indWhatsapp; }
    public void setIndWhatsapp(Boolean indWhatsapp) { this.indWhatsapp = indWhatsapp; }
	
    public Boolean getIndSendWhatsapp() { return this.indSendWhatsapp; }
    public void setIndSendWhatsapp(Boolean indSendWhatsapp) { this.indSendWhatsapp = indSendWhatsapp; }
	
	public List<MpDocumentoDigital> getMpDocumentoDigitalList() { return mpDocumentoDigitalList; }
	public void setMpDocumentoDigitalList(List<MpDocumentoDigital> mpDocumentoDigitalList) { 
															this.mpDocumentoDigitalList = mpDocumentoDigitalList; }
	
	public List<MpClienteObservacao> getMpClienteObservacaoList() { return mpClienteObservacaoList; }
	public void setMpClienteObservacaoList(List<MpClienteObservacao> mpClienteObservacaoList) { 
															this.mpClienteObservacaoList = mpClienteObservacaoList; }
	
	public List<MpProposta> getMpPropostaList() { return mpPropostaList; }
	public void setMpPropostaList(List<MpProposta> mpPropostaList) { this.mpPropostaList = mpPropostaList; }
    
}
