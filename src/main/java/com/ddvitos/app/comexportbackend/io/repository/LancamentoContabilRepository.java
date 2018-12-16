package com.ddvitos.app.comexportbackend.io.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ddvitos.app.comexportbackend.io.entity.ContaContabilEntity;
import com.ddvitos.app.comexportbackend.io.entity.LancamentoContabilEntity;

@Repository
public interface LancamentoContabilRepository extends PagingAndSortingRepository<LancamentoContabilEntity, Long> {

	LancamentoContabilEntity findByLancamentoContabilId(String id);

	Page<LancamentoContabilEntity> findByContaContabil(ContaContabilEntity contaContabilEntity,
			Pageable pageableRequest);

	List<LancamentoContabilEntity> findByContaContabil(ContaContabilEntity contaContabilEntity);
	
	List<LancamentoContabilEntity> findAll();

}
