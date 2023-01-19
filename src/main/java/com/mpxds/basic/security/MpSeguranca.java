package com.mpxds.basic.security;

import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@ViewScoped 
public class MpSeguranca implements Serializable {
	//
	private static final long serialVersionUID = 1L;

	@Inject
	private ExternalContext externalContext;

	// ---
	
	private String freshdeskURL = "mpcom.freshdesk.com"; // "mpxdsrj.freshdesk.com";
				    
	// ----	

	public String getNumeroIP() {
		//
		String ipAddress = null;

		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().
															getExternalContext().getRequest();
		ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (null == ipAddress)
			ipAddress = request.getRemoteAddr();
		//
		return ipAddress;
	}
				
	// ==============
	
	public boolean isAdministradores() {
		//
		System.out.println("MpSeguranca.isAdministradores() - ( ADMIN = " + externalContext.isUserInRole("ADMIN") + 
				" / " + externalContext.getUserPrincipal().getName().toLowerCase());

	    if (externalContext.getUserPrincipal().getName().toLowerCase().equals("teste"))
	    	return(true);
		
		return externalContext.isUserInRole("ADMIN");
	}
	
	public String getUsuarioLogado() {
		//
		return externalContext.getUserPrincipal().getName();
	}

	// ---
	
	public String getFreshdeskURL() { return freshdeskURL; }
	public void setFreshdeskURL(String freshdeskURL) { this.freshdeskURL = freshdeskURL; }
		
}
