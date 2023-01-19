package com.mpxds.basic.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mpxds.basic.model.MpProposta;
import com.mpxds.basic.repository.MpPropostaRepository;
import com.mpxds.basic.util.jsf.MpFacesUtil;
import com.mpxds.basic.util.report.MpExecutorRelatorioDTO;

@Named
public class MpRelatorioPropostaController implements Serializable {
	//
	private static final long serialVersionUID = 1L;

    @Autowired
	private FacesContext facesContext;

    @Autowired
	private HttpServletResponse response;

    @Autowired
	private MpPropostaRepository mpPropostaRepository;

	// ---
	
    @Transactional
    public void emitirDTO() {
		//
		Map<String, Object> parametros = new HashMap<>();
		
		MpExecutorRelatorioDTO executor = new MpExecutorRelatorioDTO("/relatorios/mpRelatorio_proposta_dto.jasper", 
												this.response, parametros, "MpRelatorio_proposta.pdf");
		//
		Collection<Object> data = new ArrayList<Object>();
		//
		List<MpProposta> mpPropostaList = mpPropostaRepository.findAll();
		
		for (MpProposta mpProposta : mpPropostaList) {
			//
			data.add(mpProposta);
		}
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