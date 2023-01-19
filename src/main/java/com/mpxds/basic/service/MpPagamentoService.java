package com.mpxds.basic.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpxds.basic.exception.MpNegocioException;
import com.mpxds.basic.model.MpPagamento;
import com.mpxds.basic.repository.MpPagamentoRepository;

@Service
public class MpPagamentoService {
	//
    @Autowired
    private MpPagamentoRepository mpPagamentoRepository;

    // ---

    public MpPagamento guardar(MpPagamento mpPagamento) throws MpNegocioException {
    	//
		try {
//			MpPagamento mpObj = this.findByDataMovimento(mpPagamento.getDataMovimento());
//			
//			if (mpObj != null && !mpObj.equals(mpPagamento)) {
//				throw new MpNegocioException("Já existe um PAGAMENTO com a Data informado.");
//			}
			
	    	return this.mpPagamentoRepository.saveAndFlush(mpPagamento);
	    	//
		} catch (OptimisticLockException e) {
			//
			throw new MpNegocioException("Erro de concorrência. Esse PAGAMENTO... já foi alterado anteriormente!");
		}
    }

    public void remover(MpPagamento mpPagamento)  {
    	//
    	this.mpPagamentoRepository.delete(mpPagamento);
    }

    public List<MpPagamento> findAllByDataMovimento() {
    	//
    	List<MpPagamento> mpObjs = this.mpPagamentoRepository.findAllByOrderByDataMovimento();

    	return mpObjs;
    }
    
    public MpPagamento findById(Long id) {
    	//
    	Optional<MpPagamento> mpObj = this.mpPagamentoRepository.findById(id);

    	return mpObj.orElse(null);
    }

    public MpPagamento findByDataMovimento(Date dataMovimento) {
    	//
    	Optional<MpPagamento> mpObj = this.mpPagamentoRepository.findByDataMovimento(dataMovimento);

    	return mpObj.orElse(null);
    }

}
