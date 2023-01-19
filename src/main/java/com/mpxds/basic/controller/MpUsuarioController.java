package com.mpxds.basic.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;

import com.mpxds.basic.exception.MpNegocioException;
import com.mpxds.basic.model.MpRole;
import com.mpxds.basic.model.MpUser;
import com.mpxds.basic.repository.MpRoleRepository;
import com.mpxds.basic.repository.MpUserRepository;
import com.mpxds.basic.service.MpUserService;
import com.mpxds.basic.util.jsf.MpFacesUtil;

@Named
public class MpUsuarioController implements Serializable {
	//
	private static final long serialVersionUID = 1L;
	
    @Autowired
	private MpUserRepository mpUserRepository;

    @Autowired
	private MpUserService mpUserService;

    @Autowired
	private MpRoleRepository mpRoleRepository;
	
	// ---
	
	private String txtTitulo = "Usuário";

	private MpUser mpUser;
	private MpUser mpUserAnt;

	private String senha = "";

	private Boolean indEditavel = true;
	private String txtModoTela = "";
	
	private List<MpUser> mpUserList;
	private List<MpUser> filtroMpUserList;
	
	private MpRole mpRole;
	private List<MpRole> mpRoleList = new ArrayList<MpRole>();

	// ------------------
	
	public void inicializar() {
		//		
		this.mpUser = new MpUser();	
		this.mpUserAnt = new MpUser();
		//
		this.indEditavel = true;		
		this.txtModoTela = "";
		this.senha = "????"; // Só altera se for modificada ! 
		
		this.mpUserList = this.mpUserService.findAllByUsername();
		
		this.mpRoleList = this.mpRoleRepository.findAllByOrderByName();
	}
		
	public void salvar() {
		//
		this.mpUser = this.mpUserService.guardar(this.mpUser);

		MpFacesUtil.addInfoMessage(this.txtTitulo + "... salvo com sucesso! ( " + this.mpUser.getUsername());
	}
	
	// ------------- Trata Botões -------------
	  
	public void mpListener(ActionEvent event){
		//
		this.mpUser = (MpUser)event.getComponent().getAttributes().get("UsuarioSelecionado");
	}	

	public void mpFechar(CloseEvent event) {
		//
//		System.out.println("MpCadastroUsuarioBean.FECHAR()");
		
		try {
			String contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();

			FacesContext.getCurrentInstance().getExternalContext().redirect(contextPath + "/Dashboard");
			
		} catch (IOException e) {
			MpFacesUtil.addInfoMessage("Erro Redirecionameto Fechar ... contactar o Suporte ! ");
		}
	}	
		
	public void mpNew() {
		//
		this.setMpUserAnt(this.mpUser);
		
		this.mpUser = new MpUser();	
		//
		this.setIndEditavel(false);
		//
		this.txtModoTela = "( Novo )";
	}
	
	public void mpEdit() {
		//
		if (null == this.mpUser.getId()) return;
		//
		this.setMpUserAnt(this.mpUser);
		
		this.indEditavel = false;
		//
		this.txtModoTela = "( Edição )";
	}
	
	public void mpView() {
		//
		if (null == this.mpUser.getId()) return;
		//
		this.setMpUserAnt(this.mpUser);
		
		this.indEditavel = true;
		//
		this.txtModoTela = "( Visualização )";
	}
	
	public void mpDelete() {
		//
		if (null == this.mpUser.getId()) return;
		//
		try {
			this.mpUserRepository.delete(this.mpUser);
			
			this.mpUserList.remove(this.mpUser);
			//
			MpFacesUtil.addInfoMessage(this.txtTitulo + "... " + this.mpUser.getUsername() +
																	 			" ... excluído com sucesso.");
		} catch (MpNegocioException ne) {
			MpFacesUtil.addErrorMessage(ne.getMessage());
		}
	}
	
	public void mpGrava() {
		//
//		System.out.println("MpCadastroUsuarioBean.GRAVA() (" + this.getIndEditavel() + "/" + this.txtModoTela);

//		if (!this.senha.equals("????")) {
//			//
//			this.mpUser.setPassword(BCryptPasswordEncoder.this.senha);
//		}
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
		this.mpUserList = this.mpUserService.findAllByUsername();

		this.setMpUserAnt(this.mpUser);
		//
		this.indEditavel = true;
		//
		this.txtModoTela = "";
	}
	
	public void mpDesfaz() {
		//
//		System.out.println("MpCadastroUsuarioBean.DESFAZ()");

		this.mpUser = this.mpUserAnt;
		
		this.indEditavel = true;
		//
		this.txtModoTela = "";
	}
	
	// ---

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public boolean filterByValor(Object value, Object filter, Locale locale) {
    	//
//		System.out.println("MpCadastroUsuarioBean.FILTER()");

    	String filterText = (filter == null) ? null : filter.toString().trim();
        if (filterText == null||filterText.equals("")) return true;
         
        if (value == null) return false;
        //
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
	
	// ---

	public MpUser getMpUser() { return mpUser; }
	public void setMpUser(MpUser mpUser) { this.mpUser = mpUser; }

	public MpUser getMpUserAnt() { return mpUserAnt; }
	public void setMpUserAnt(MpUser mpUserAnt) {
		try {
			if (null==this.mpUser) return;
			//
			this.mpUserAnt = (MpUser) this.mpUser.clone();
			this.mpUserAnt.setId(this.mpUser.getId());
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	
	public String getSenha() { return senha; }
	public void setSenha(String senha) { this.senha = senha; }
	
	public boolean isEditando() { return this.mpUser.getId() != null; }
	
	public boolean isIndEditavel() { return indEditavel; }
	public boolean getIndEditavel() { return indEditavel; }
	public void setIndEditavel(Boolean indEditavel) { this.indEditavel = indEditavel; }
	
	public String getTxtTitulo() { return txtTitulo; }
	public void setTxtTitulo(String txtTitulo) { this.txtTitulo = txtTitulo; }
		
	public String getTxtModoTela() { return txtModoTela; }
	public void setTxtModoTela(String txtModoTela) { this.txtModoTela = txtModoTela; }
	
	//
	
	public List<MpUser> getMpUserList() { return mpUserList; }
	
	public List<MpUser> getFiltroMpUserList() { return filtroMpUserList; }
	public void setFiltroMpUserList(List<MpUser> filtroMpUserList) { 
																this.filtroMpUserList = filtroMpUserList; }
	
	public MpRole getMpRole() { return mpRole; }
	public void setMpRole(MpRole mpRole) { this.mpRole = mpRole; }

	public List<MpRole> getMpRoleList() { return mpRoleList; }
	public void setMpRoleList(List<MpRole> mpRoleList) { this.mpRoleList = mpRoleList; }

}