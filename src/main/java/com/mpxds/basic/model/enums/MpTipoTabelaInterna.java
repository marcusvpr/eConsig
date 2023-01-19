package com.mpxds.basic.model.enums;

public enum MpTipoTabelaInterna {
	//
	TB_INDICACAO("Tabela Indicação", "c", 50, false, null),
	TB_ORGAO_EXPEDIDOR("Tabela Orgão Expedidor", "c", 50, false, null),
	TB_CIDADE("Tabela Cidade", "c", 50, false, null), // Uf -> EnumSeparado !
	TB_TIPO_TELEFONE("Tabela Tipo Telefone", "c", 50, false, null),
	TB_BANCO("Tabela Banco", "c", 50, false, null),
//	TB_0006("Tabela Conta Tipo", "c", 50, false, null), // Enums
//	TB_0007("Tabela Conta", "c", 50, false, null), // Enums
	TB_OPERADORA("Tabela Operadora", "c", 50, false, null),
	TB_CORRETOR("Tabela Corretor", "c", 50, false, null),
	TB_VENDEDOR("Tabela Vendedor", "c", 50, false, null),
	TB_CONVENIO("Tabela Convênio", "c", 50, false, null),
	TB_ESPECIE_BENEFICIO("Tabela Espécie Benefício", "c", 50, false, null),
	TB_TIPO_CONTRATO("Tabela Tipo Contrato", "c", 50, false, null),
	TB_BANCO_OPERACAO("Tabela Banco Operação", "c", 50, false, null),
	TB_PRAZO("Tabela Prazo", "c", 50, false, null), 
	TB_CADASTRADOR("Tabela Cadastrador", "c", 50, false, null), // Cadastro?
	TB_DIGITADOR("Tabela Digitador", "c", 50, false, null), // ??? 
	TB_TIPO_RG("Tabela Tipo RG", "c", 50, false, null),
	TB_CORRESPONDENTE("Tabela Correspondente", "c", 50, false, null),
	TB_CODIGO_COMISSAO("Tabela Codigo Comissão", "c", 50, false, null),
	TB_ORGAO_CONVENIO("Tabela Orgão Convenio", "c", 50, false, null),
	TB_PAGAMENTO_TIPO("Tabela Pagamento Tipo", "c", 50, false, null),
	TB_PAGAMENTO_STATUS("Tabela Pagamento Status", "c", 50, false, null),
	TB_EMAIL_DIGITADOR_PROPOSTA("Tabela Email Digitador Proposta", "c", 80, false, null),
	TB_WHATSAPP_MSG("Tabela Whatsapp Mensagem Padrão", "c", 80, false, null);
	
//	- INDICAÇÃO – Google / Letreiro / Sms / Cliente / Telmktg / Outros / Listagem Viper / = Ok?
//	- Orgão Expedidor – Detran / IFP / SSP / Marinha / Exército/ Aeronáurica / OAB = Ok?
//	- UF – RJ / SP / MG / SC / ES / PB / PE / RS / = OK
//	- Cidade  - Rio de Janeiro / São Paulo / = OK
//	- Tel (DDD)  - Tipo  - Casa / Trabalho / Mãe / Filha / Pai / Recado = Ok?            
//	- Banco – Itaú / Caixa / Bradesco / Santander / Brasil / Mercantil = Ok?
//	- Conta Tipo – Corrente / Poupança = OK
//	- Conta – Individual / Conjunta = OH
//	- Operadora – Cia do Credito / Bevicred Rio / = OK
//	- Corretor – Adri / Adalberto = OK
//	- Vendedor – Adalberto / Giovanna /  = OK
//	- Convênio – Inss / Federal / Marinha / Aeronáutica / Federal / Previ Rio / Estado / Prefeitura Rio = Ok?
//	- Espécie de Benefício – Aposentado 42 / Aposentado 41 / Aposentado 32 / Aposentado / Pensionista / Ativo  = Ok?
//	- Tipo Contrato – Refin / Refin A / Novo / Cartão / Portabilidade / Refin Portab / = Ok?
//	- Banco Operação – Itaú / Safra / Pan / Olé / Cetelem / Bmg / Daycoval / = Ok?
//	- Prazo – 72 / 96 / 60 /48 / 36 / 24 / 12 / 0 = Ok?
//  - Cadastro – Administrador / Gerente / Operacional / Financeiro / Telemarketing / 
//  - Tipo RG - Rg - Cnh - etc 
//  - Correspondente - ???
//  - Codigo Comissão - A(3%) B(3.5%) C(4%) ... 
//  - Orgão Convenio - ??? ... 
//  - Pagamento Tipo - ??? ... 
//  - Pagamento Status - Pago / Pendente ... 

	private final String descricao;
	private final String formato; // c=character n=numeric
	private final Integer tamanho;
	private final Boolean indPai;
	private final String filha;
	
	// ---

	MpTipoTabelaInterna(String descricao, String formato, Integer tamanho, Boolean indPai, String filha) {
		//
		this.descricao = descricao;
		this.formato = formato;
		this.tamanho = tamanho;
		this.indPai = indPai;
		this.filha = filha;
	}
	
//    public static MpTipoTabelaInternaSJ capturaTipoTabela(String tabelaX) {
//    	//
//    	MpTipoTabelaInternaSJ mpTipoTabelaInternaSJX = null; // Default
//    	
//        for (MpTipoTabelaInternaSJ itemX : MpTipoTabelaInternaSJ.values()) {
//            if (itemX.getTabela().equals(tabelaX)) {
//            	mpTipoTabelaInternaSJX = itemX;
//                break;
//            }
//        }
//        //
//        return mpTipoTabelaInternaSJX;
//    }	
	
	// ---
	
	public String getDescricao() { return descricao; }
	
	public String getFormato() { return formato; }
	
	public Integer getTamanho() { return tamanho; }
	
	public Boolean getIndPai() { return indPai; }
	
	public String getFilha() { return filha; }
	
}