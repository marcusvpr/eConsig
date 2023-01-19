package com.mpxds.basic.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpxds.basic.exception.MpNegocioException;
import com.mpxds.basic.model.MpClienteConsignado;
import com.mpxds.basic.repository.MpClienteConsignadoRepository;

@Service
public class MpClienteConsignadoService {
	//
    @Autowired
    private MpClienteConsignadoRepository mpClienteConsignadoRepository;

    // ---

    public MpClienteConsignado guardar(MpClienteConsignado mpClienteConsignado) throws MpNegocioException {
    	//
		try {
			MpClienteConsignado mpObj = this.findByNome(mpClienteConsignado.getNome());
			
			if (mpObj != null && !mpObj.equals(mpClienteConsignado)) {
				throw new MpNegocioException("Já existe um CLIENTE CONSIGNADO com o NOME informado.");
			}
			
	    	return this.mpClienteConsignadoRepository.saveAndFlush(mpClienteConsignado);
	    	//
		} catch (OptimisticLockException e) {
			//
			throw new MpNegocioException(
								"Erro de concorrência. Esse CLIENTE CONSIGNADO... já foi alterado anteriormente!");
		}
    }

    public void remover(MpClienteConsignado mpClienteConsignado)  {
    	//
    	this.mpClienteConsignadoRepository.delete(mpClienteConsignado);
    }

    public List<MpClienteConsignado> findAllByNome() {
    	//
    	List<MpClienteConsignado> mpObjs = this.mpClienteConsignadoRepository.findAllByOrderByNome();

    	return mpObjs;
    }
    
    public MpClienteConsignado findById(Long id) {
    	//
    	Optional<MpClienteConsignado> mpObj = this.mpClienteConsignadoRepository.findById(id);

    	return mpObj.orElse(null);
    }

    public MpClienteConsignado findByNome(String nome) {
    	//
    	Optional<MpClienteConsignado> mpObj = this.mpClienteConsignadoRepository.findByNome(nome);

    	return mpObj.orElse(null);
    }

}