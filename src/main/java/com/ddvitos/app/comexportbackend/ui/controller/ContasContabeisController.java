package com.ddvitos.app.comexportbackend.ui.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ddvitos.app.comexportbackend.exceptions.ContaContabilServiceException;
import com.ddvitos.app.comexportbackend.service.ContaContabilService;
import com.ddvitos.app.comexportbackend.shared.dto.ContaContabilDTO;
import com.ddvitos.app.comexportbackend.ui.model.request.ContaContabilRequestModel;
import com.ddvitos.app.comexportbackend.ui.model.response.ContaContabilRest;

@RestController
@RequestMapping("contas-contabeis") // http://localhost:8888/contas-contabeis
public class ContasContabeisController {

	@Autowired
	ContaContabilService contaContabilService;

	// adiciona uma conta contabil
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public ContaContabilRest createContaContabel(@RequestBody ContaContabilRequestModel contaContabil) {

		if (contaContabil.getNumero().isEmpty())
			throw new ContaContabilServiceException("Missing field numero");

		ModelMapper modelMapper = new ModelMapper();
		ContaContabilDTO contaContabilDTO = modelMapper.map(contaContabil, ContaContabilDTO.class);

		ContaContabilDTO newContaContabil = contaContabilService.createContaContabil(contaContabilDTO);
		return modelMapper.map(newContaContabil, ContaContabilRest.class);
	}

	// detalhe de uma conta contabil
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public String getContaContabil(@PathVariable String id) {
		// @TODO
		return "2";
	}

}
