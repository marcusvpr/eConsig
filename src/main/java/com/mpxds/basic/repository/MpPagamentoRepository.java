package com.mpxds.basic.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mpxds.basic.model.MpPagamento;

public interface MpPagamentoRepository extends JpaRepository<MpPagamento, Long> {
	//
	Optional<MpPagamento> findByDataMovimento(Date dataMovimento);
	
	@Transactional(readOnly=true)
	public List<MpPagamento> findAllByOrderByDataMovimento();

}
