package com.ddvitos.app.comexportbackend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ddvitos.app.comexportbackend.exceptions.RecordNotFoundException;
import com.ddvitos.app.comexportbackend.io.entity.ContaContabilEntity;
import com.ddvitos.app.comexportbackend.io.entity.LancamentoContabilEntity;
import com.ddvitos.app.comexportbackend.io.repository.ContaContabilRepository;
import com.ddvitos.app.comexportbackend.io.repository.LancamentoContabilRepository;
import com.ddvitos.app.comexportbackend.service.LancamentoContabilService;
import com.ddvitos.app.comexportbackend.shared.Utils;
import com.ddvitos.app.comexportbackend.shared.dto.ContaContabilDTO;
import com.ddvitos.app.comexportbackend.shared.dto.LancamentoContabilDTO;

@Service
public class LancamentoContabilImpl implements LancamentoContabilService {

	@Autowired
	LancamentoContabilRepository lancamentoContabilRepository;

	@Autowired
	ContaContabilRepository contaContabilRespository;

	@Autowired
	Utils utils;

	@Override
	public LancamentoContabilDTO createLancamentoContabil(LancamentoContabilDTO lancamentoContabilDTO) {
		ContaContabilEntity contaContabilEntity = contaContabilRespository
				.findByNumero(lancamentoContabilDTO.getContaContabil().getNumero());
		if (contaContabilEntity == null)
			throw new RecordNotFoundException("Conta contabil not found");

		ModelMapper modelMapper = new ModelMapper();
		LancamentoContabilEntity lancamentoContaContabilEntity = modelMapper.map(lancamentoContabilDTO,
				LancamentoContabilEntity.class);

		lancamentoContaContabilEntity.setLancamentoContabilId(utils.generateRandomString(30));

		LancamentoContabilEntity storedlancamentoContaContabilEntity = lancamentoContabilRepository
				.save(lancamentoContaContabilEntity);

		return modelMapper.map(storedlancamentoContaContabilEntity, LancamentoContabilDTO.class);
	}

	@Override
	public LancamentoContabilDTO getByLancamentoContabilId(String id) {
		LancamentoContabilDTO returnValue = new LancamentoContabilDTO();
		LancamentoContabilEntity lancamentoContabilEntity = lancamentoContabilRepository.findByLancamentoContabilId(id);
		if (lancamentoContabilEntity == null)
			throw new RecordNotFoundException("Lancamento Contabil with ID: " + id + " not found.");

		BeanUtils.copyProperties(lancamentoContabilEntity, returnValue);

		ContaContabilDTO contaContabilDTO = new ContaContabilDTO();

		BeanUtils.copyProperties(lancamentoContabilEntity.getContaContabil(), contaContabilDTO);

		returnValue.setContaContabil(contaContabilDTO);

		return returnValue;
	}

	@Override
	public List<LancamentoContabilDTO> getLancamentosByContaContabil(ContaContabilDTO contaContabilDTO, int page,
			int limit) {
		ContaContabilEntity contaContabilEntity = new ContaContabilEntity();
		BeanUtils.copyProperties(contaContabilDTO, contaContabilEntity);

		if (page > 0)
			page--;

		Pageable pageableRequest = PageRequest.of(page, limit);

		Page<LancamentoContabilEntity> lancamentosPage = lancamentoContabilRepository
				.findByContaContabil(contaContabilEntity, pageableRequest);
		List<LancamentoContabilEntity> lancamentos = lancamentosPage.getContent();
		return toDTOList(contaContabilDTO, lancamentos);
	}

	@Override
	public List<LancamentoContabilDTO> getLancamentosByContaContabil(ContaContabilDTO contaContabilDTO) {
		ContaContabilEntity contaContabilEntity = new ContaContabilEntity();
		BeanUtils.copyProperties(contaContabilDTO, contaContabilEntity);

		List<LancamentoContabilEntity> lancamentos = lancamentoContabilRepository
				.findByContaContabil(contaContabilEntity);

		return toDTOList(contaContabilDTO, lancamentos);
	}

	@Override
	public List<LancamentoContabilDTO> getAll() {
		List<LancamentoContabilEntity> lancamentos = lancamentoContabilRepository.findAll();
		return toDTOList(null, lancamentos);
	}

	private List<LancamentoContabilDTO> toDTOList(ContaContabilDTO contaContabilDTO,
			List<LancamentoContabilEntity> lancamentos) {
		List<LancamentoContabilDTO> returnValue = new ArrayList<>();
		for (LancamentoContabilEntity lancamentoContabilEntity : lancamentos) {
			LancamentoContabilDTO lancamentoContabilDTO = new LancamentoContabilDTO();
			BeanUtils.copyProperties(lancamentoContabilEntity, lancamentoContabilDTO);
			lancamentoContabilDTO.setContaContabil(contaContabilDTO);
			returnValue.add(lancamentoContabilDTO);
		}
		return returnValue;
	}

}
