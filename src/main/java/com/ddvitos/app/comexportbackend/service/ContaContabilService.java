package com.ddvitos.app.comexportbackend.service;

import com.ddvitos.app.comexportbackend.shared.dto.ContaContabilDTO;

public interface ContaContabilService {

	ContaContabilDTO createContaContabil(ContaContabilDTO contaContabilDTO);

	ContaContabilDTO getByNumero(String contaContabil);

}
