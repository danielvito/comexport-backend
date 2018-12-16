package com.ddvitos.app.comexportbackend.ui.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.ddvitos.app.comexportbackend.service.impl.LancamentoContabilImpl;
import com.ddvitos.app.comexportbackend.shared.dto.ContaContabilDTO;
import com.ddvitos.app.comexportbackend.shared.dto.LancamentoContabilDTO;
import com.ddvitos.app.comexportbackend.ui.model.response.LancamentoContabilRest;

public class LancamentosContabeisControllerUTest {

	@InjectMocks
	LancamentosContabeisController lancamentosContabeisController;

	@Mock
	LancamentoContabilImpl lancamentoContabilService;

	ContaContabilDTO contaContabilDTO;
	LancamentoContabilDTO lancamentoContabilDTO;

	final String CONTA_CONTABIL_ID = "98765432";
	final String LANCAMENTO_CONTABIL_ID = "12345678";
	final String NUMERO_CONTA_CONTABIL = "1111001";

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		contaContabilDTO = new ContaContabilDTO();
		contaContabilDTO.setContaContabilId(CONTA_CONTABIL_ID);
		contaContabilDTO.setDescricao("Teste");
		contaContabilDTO.setId(2);
		contaContabilDTO.setNumero(NUMERO_CONTA_CONTABIL);

		lancamentoContabilDTO = new LancamentoContabilDTO();
		lancamentoContabilDTO.setLancamentoContabilId(LANCAMENTO_CONTABIL_ID);
		lancamentoContabilDTO.setData(new Date());
		lancamentoContabilDTO.setValor(BigDecimal.valueOf(1000000));
		lancamentoContabilDTO.setId(4);
		lancamentoContabilDTO.setContaContabil(contaContabilDTO);
	}

	@Test
	@DisplayName("Controller Lancamento Contabil Unit Test")
	void testGetBankSlip() {
		when(lancamentoContabilService.getByLancamentoContabilId(anyString())).thenReturn(lancamentoContabilDTO);

		LancamentoContabilRest lancamentoContabilRest = lancamentosContabeisController
				.getLancamentoContabil(LANCAMENTO_CONTABIL_ID);

		assertNotNull(lancamentoContabilRest);
		assertEquals(NUMERO_CONTA_CONTABIL, lancamentoContabilRest.getContaContabil());
		assertEquals(lancamentoContabilDTO.getValor(), lancamentoContabilRest.getValor());
		assertEquals(lancamentoContabilDTO.getData(), lancamentoContabilRest.getData());
	}
}
