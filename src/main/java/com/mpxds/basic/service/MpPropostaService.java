package com.mpxds.basic.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpxds.basic.exception.MpNegocioException;
import com.mpxds.basic.model.MpProposta;
import com.mpxds.basic.repository.MpPropostaRepository;

@Service
public class MpPropostaService {
	//
    @Autowired
    private MpPropostaRepository mpPropostaRepository;

    // ---

    public MpProposta guardar(MpProposta mpProposta) throws MpNegocioException {
    	//
		try {
//			MpProposta mpObj = this.findByDataMovimento(mpProposta.getDataMovimento());
//			
//			if (mpObj != null && !mpObj.equals(mpProposta)) {
//				throw new MpNegocioException("Já existe uma PROPOSTA com a Data informada.");
//			}
			//
	    	return this.mpPropostaRepository.saveAndFlush(mpProposta);
	    	//
		} catch (OptimisticLockException e) {
			//
			throw new MpNegocioException("Erro de concorrência. Essa PROPOSTA... já foi alterada anteriormente!");
		}
    }

    public void remover(MpProposta mpProposta)  {
    	//
    	this.mpPropostaRepository.delete(mpProposta);
    }

    public List<MpProposta> findAllByDataMovimento() {
    	//
    	List<MpProposta> mpObjs = this.mpPropostaRepository.findAllByOrderByDataMovimento();

    	return mpObjs;
    }
    
    public MpProposta findById(Long id) {
    	//
    	Optional<MpProposta> mpObj = this.mpPropostaRepository.findById(id);

    	return mpObj.orElse(null);
    }

    public MpProposta findByDataMovimento(Date dataMovimento) {
    	//
    	Optional<MpProposta> mpObj = this.mpPropostaRepository.findByDataMovimento(dataMovimento);

    	return mpObj.orElse(null);
    }

}
