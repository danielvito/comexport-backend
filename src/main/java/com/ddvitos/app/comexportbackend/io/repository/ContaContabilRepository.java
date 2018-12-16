package com.ddvitos.app.comexportbackend.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.ddvitos.app.comexportbackend.io.entity.ContaContabilEntity;

@Repository
public interface ContaContabilRepository extends PagingAndSortingRepository<ContaContabilEntity, Long> {

	ContaContabilEntity findByNumero(String numero);

	ContaContabilEntity findByContaContabilId(String contaContabilId);

}
