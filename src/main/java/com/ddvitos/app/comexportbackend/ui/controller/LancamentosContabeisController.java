package com.ddvitos.app.comexportbackend.ui.controller;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ddvitos.app.comexportbackend.exceptions.LancamentoContabilServiceException;
import com.ddvitos.app.comexportbackend.service.ContaContabilService;
import com.ddvitos.app.comexportbackend.service.LancamentoContabilService;
import com.ddvitos.app.comexportbackend.shared.dto.ContaContabilDTO;
import com.ddvitos.app.comexportbackend.shared.dto.LancamentoContabilDTO;
import com.ddvitos.app.comexportbackend.ui.model.request.LancamentoContabilRequestModel;
import com.ddvitos.app.comexportbackend.ui.model.response.LancamentoContabilRest;

@RestController
@RequestMapping("lancamentos-contabeis") // http://localhost:8888/lancamentos-contabeis
public class LancamentosContabeisController {

	@Autowired
	ContaContabilService contaContabilService;

	@Autowired
	LancamentoContabilService lancamentoContabilService;

	// adiciona um lancamento contabil
	@PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseStatus(HttpStatus.CREATED)
	public Map<String, String> updateLancamentoContabel(
			@RequestBody LancamentoContabilRequestModel lancamentoContabil) {

		if (lancamentoContabil.getContaContabil().isEmpty())
			throw new LancamentoContabilServiceException("Missing field contaContabil");

		ModelMapper modelMapper = new ModelMapper();
		LancamentoContabilDTO lancamentoContabilDTO = modelMapper.map(lancamentoContabil, LancamentoContabilDTO.class);

		ContaContabilDTO contaContabilDTO = contaContabilService.getByNumero(lancamentoContabil.getContaContabil());

		lancamentoContabilDTO.setContaContabil(contaContabilDTO);

		LancamentoContabilDTO newLancamentoContabil = lancamentoContabilService
				.createLancamentoContabil(lancamentoContabilDTO);

		Map<String, String> returnValue = new HashMap<String, String>();
		returnValue.put("id", newLancamentoContabil.getLancamentoContabilId());

		return returnValue;
	}

	// detalhe de um lancamento contabil
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public LancamentoContabilRest getLancamentoContabil(@PathVariable String id) {
		LancamentoContabilDTO lancamentoContabilDTO = lancamentoContabilService.getByLancamentoContabilId(id);
		return toRest(lancamentoContabilDTO);
	}

	// lista de lancamentos contabeis por conta contabil
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<LancamentoContabilRest> listLancamentosContabeis(
			@RequestParam(value = "contaContabil") String contaContabil,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "25") int limit) {
		List<LancamentoContabilRest> returnValue = new ArrayList<>();

		ContaContabilDTO contaContabilDTO = contaContabilService.getByNumero(contaContabil);

		List<LancamentoContabilDTO> lancamentos = lancamentoContabilService
				.getLancamentosByContaContabil(contaContabilDTO, page, limit);

		for (LancamentoContabilDTO lancamentoContabilDTO : lancamentos) {
			returnValue.add(toRest(lancamentoContabilDTO));
		}

		return returnValue;
	}

	private LancamentoContabilRest toRest(LancamentoContabilDTO lancamentoContabilDTO) {
		LancamentoContabilRest lancamentoContabilRest = new LancamentoContabilRest();
		lancamentoContabilRest.setContaContabtil(lancamentoContabilDTO.getContaContabil().getNumero());
		lancamentoContabilRest.setData(lancamentoContabilDTO.getData());
		lancamentoContabilRest.setValor(lancamentoContabilDTO.getValor());
		return lancamentoContabilRest;
	}

	// stats
	@GetMapping(path = "/_stats", produces = { MediaType.APPLICATION_JSON_VALUE })
	public Map<String, Object> stats(@RequestParam(value = "contaContabil", defaultValue = "") String contaContabil) {

		List<LancamentoContabilDTO> lancamentos = null;

		if (contaContabil == null || "".equals(contaContabil)) {
			lancamentos = lancamentoContabilService.getAll();
		} else {
			ContaContabilDTO contaContabilDTO = contaContabilService.getByNumero(contaContabil);
			lancamentos = lancamentoContabilService.getLancamentosByContaContabil(contaContabilDTO);
		}

		List<Double> values = new ArrayList<>();

		for (LancamentoContabilDTO lancamento : lancamentos)
			values.add(lancamento.getValor().doubleValue());

		DoubleSummaryStatistics stats = values.stream().mapToDouble((x) -> x).summaryStatistics();

		Map<String, Object> returnValue = new HashMap<String, Object>();
		returnValue.put("soma", stats.getSum());
		returnValue.put("min", stats.getMin());
		returnValue.put("max", stats.getMax());
		returnValue.put("media", stats.getAverage());
		returnValue.put("qtde", stats.getCount());

		return returnValue;
	}

}
