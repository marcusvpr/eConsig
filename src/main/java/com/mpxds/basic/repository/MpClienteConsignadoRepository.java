package com.mpxds.basic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mpxds.basic.model.MpClienteConsignado;

public interface MpClienteConsignadoRepository extends JpaRepository<MpClienteConsignado, Long> {
	//
	Optional<MpClienteConsignado> findByNome(String nome);
	
	@Transactional(readOnly=true)
	public List<MpClienteConsignado> findAllByOrderByNome();
    
}
