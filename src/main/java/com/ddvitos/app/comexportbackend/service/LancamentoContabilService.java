package com.ddvitos.app.comexportbackend.service;

import java.util.List;

import com.ddvitos.app.comexportbackend.shared.dto.ContaContabilDTO;
import com.ddvitos.app.comexportbackend.shared.dto.LancamentoContabilDTO;

public interface LancamentoContabilService {

	LancamentoContabilDTO createLancamentoContabil(LancamentoContabilDTO lancamentoContabilDTO);

	LancamentoContabilDTO getByLancamentoContabilId(String id);

	List<LancamentoContabilDTO> getLancamentosByContaContabil(ContaContabilDTO contaContabilDTO, int page, int limit);

	List<LancamentoContabilDTO> getLancamentosByContaContabil(ContaContabilDTO contaContabilDTO);

	List<LancamentoContabilDTO> getAll();

}
