package com.mpxds.basic.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mpxds.basic.util.jsf.MpFacesUtil;
import com.mpxds.basic.util.report.MpExecutorRelatorio;

@Named
public class MpRelatorioClienteConsignadoController implements Serializable {
	//
	private static final long serialVersionUID = 1L;

    @Autowired
	private FacesContext facesContext;

    @Autowired
	private HttpServletResponse response;

    @Autowired
	private EntityManager manager;

	// ---
	
    @Transactional
    public void emitir() {
		//
		Map<String, Object> parametros = new HashMap<>();
		
		MpExecutorRelatorio executor = new MpExecutorRelatorio("/relatorios/mpRelatorio_clientes_consignados.jasper", 
												this.response, parametros, "MpRelatorio_clientes_consignados.pdf");
		//
		Session session = manager.unwrap(Session.class);
		
		session.doWork(executor);
		
		if (executor.isRelatorioGerado()) {
			facesContext.responseComplete();
		} else {
			MpFacesUtil.addErrorMessage("A execução do relatório não retornou dados.");
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
	

}