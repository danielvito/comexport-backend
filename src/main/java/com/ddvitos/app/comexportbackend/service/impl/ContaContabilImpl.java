package com.ddvitos.app.comexportbackend.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddvitos.app.comexportbackend.exceptions.RecordAlreadyExistsException;
import com.ddvitos.app.comexportbackend.exceptions.RecordNotFoundException;
import com.ddvitos.app.comexportbackend.io.entity.ContaContabilEntity;
import com.ddvitos.app.comexportbackend.io.repository.ContaContabilRepository;
import com.ddvitos.app.comexportbackend.io.repository.LancamentoContabilRepository;
import com.ddvitos.app.comexportbackend.service.ContaContabilService;
import com.ddvitos.app.comexportbackend.shared.Utils;
import com.ddvitos.app.comexportbackend.shared.dto.ContaContabilDTO;

@Service
public class ContaContabilImpl implements ContaContabilService {

	@Autowired
	LancamentoContabilRepository lancamentoContabilRepository;

	@Autowired
	ContaContabilRepository contaContabilRespository;

	@Autowired
	Utils utils;

	@Override
	public ContaContabilDTO createContaContabil(ContaContabilDTO contaContabilDTO) {
		ContaContabilEntity contaContabilEntity = contaContabilRespository.findByNumero(contaContabilDTO.getNumero());
		if (contaContabilEntity != null)
			throw new RecordAlreadyExistsException("Conta already exists");

		ModelMapper modelMapper = new ModelMapper();
		ContaContabilEntity contaContaContabilEntity = modelMapper.map(contaContabilDTO, ContaContabilEntity.class);

		contaContaContabilEntity.setContaContabilId(utils.generateRandomString(30));

		ContaContabilEntity storedContaContabilEntity = contaContabilRespository.save(contaContaContabilEntity);

		return modelMapper.map(storedContaContabilEntity, ContaContabilDTO.class);
	}

	@Override
	public ContaContabilDTO getByNumero(String numero) {
		ContaContabilDTO returnValue = new ContaContabilDTO();
		ContaContabilEntity contaContabilEntity = contaContabilRespository.findByNumero(numero);

		if (contaContabilEntity == null)
			throw new RecordNotFoundException("Conta contabil numero: " + numero + " not found.");

		BeanUtils.copyProperties(contaContabilEntity, returnValue);

		return returnValue;
	}

	@Override
	public ContaContabilDTO getByContaContabilId(String contaContabilId) {
		ContaContabilDTO returnValue = new ContaContabilDTO();
		ContaContabilEntity contaContabilEntity = contaContabilRespository.findByContaContabilId(contaContabilId);

		if (contaContabilEntity == null)
			throw new RecordNotFoundException("Conta contabil id: " + contaContabilId + " not found.");

		BeanUtils.copyProperties(contaContabilEntity, returnValue);

		return returnValue;
	}

}
