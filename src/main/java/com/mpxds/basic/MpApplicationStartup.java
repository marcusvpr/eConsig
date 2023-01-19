package com.mpxds.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.mpxds.basic.mail.MpSendEmail;
import com.mpxds.basic.model.MpUser;
import com.mpxds.basic.repository.MpClienteConsignadoRepository;
import com.mpxds.basic.repository.MpContratoRepository;
import com.mpxds.basic.repository.MpDocumentoDigitalRepository;
import com.mpxds.basic.repository.MpPropostaRepository;
import com.mpxds.basic.repository.MpTabelaInternaRepository;
import com.mpxds.basic.service.MpUserService;

@Component
public class MpApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
	//
    @Autowired
    public MpSendEmail mpSendEmail;

    @Autowired
    public MpUserService mpUserService;

    @Autowired
    public MpTabelaInternaRepository mpTabelaInternaRepository;
    @Autowired
    public MpClienteConsignadoRepository mpClienteConsignadoRepository;
    @Autowired
    public MpDocumentoDigitalRepository mpDocumentoDigitalRepository;
    @Autowired
    public MpPropostaRepository mpPropostaRepository;
    @Autowired
    public MpContratoRepository mpContratoRepository;

    // ---
    
	@Override
	public void onApplicationEvent(final ApplicationReadyEvent event) {
		//
		System.out.println("MpApplicationStartup ON");
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		//
        InetAddress ip;
        String hostname = "";
        String numIp = "";
        
        try {
            ip = InetAddress.getLocalHost();
            
            hostname = ip.getHostName();
            numIp = ip.getHostAddress();
            //
        } catch (UnknownHostException e) {
        	System.out.println("MpApplicationStartup Exception: " + e);
        }
        //
		String to = "marcus_vpr@hotmail.com";
		String subject = "MpConsig - StartupLOG ( " + sdf.format(new Date()) + " Host/IP : " + hostname + " / " + numIp;
		String msg = "";
		//
		List<MpUser> mpUserList = mpUserService.findAllByUsername();
		
		for (MpUser mpUserX : mpUserList) {
			//
			msg = msg + " [ Nome: " + mpUserX.getUsername() + ", E-mail: " + mpUserX.getEmail() + " ]\n" ;
		}
		//
		msg = msg + "\n\n" ;
		msg = msg + " [ Total : ]\n" ;
		msg = msg + " [ . Tabela Interna : " + mpTabelaInternaRepository.count() + " ]\n" ;
		msg = msg + " [ . Cliente Consignado : " + mpClienteConsignadoRepository.count() + " ]\n" ;
		msg = msg + " [ . Documento Digital : " + mpDocumentoDigitalRepository.count() + " ]\n" ;
		msg = msg + " [ . Proposta : " + mpPropostaRepository.count() + " ]\n" ;
		msg = msg + " [ . Contrato : " + mpContratoRepository.count() + " ]\n" ;
		//
		try { 
			//
//			System.out.println("MpApplicationStartup SEND - ( " + msg);

			this.mpSendEmail.sendSimpleMessage(to, subject, msg);
			
			System.out.println("MpApplicationStartup SEND");
		}		 
		catch (Exception e) { 
			//
			System.out.println("MpApplicationStartup ERROR ( e = " + e);
		}
		//
		return;
	}
 
}