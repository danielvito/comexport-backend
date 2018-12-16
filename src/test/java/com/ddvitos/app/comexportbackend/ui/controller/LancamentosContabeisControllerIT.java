package com.ddvitos.app.comexportbackend.ui.controller;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import com.ddvitos.app.comexportbackend.ui.model.response.LancamentoContabilRest;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LancamentosContabeisControllerIT {

	public LancamentosContabeisControllerIT() {
	}

	private final String CONTEXT_PATH = "/comexport";

	@Before
	public void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 8888;
	}

	@Test
	// @Ignore
	@DisplayName("Create Conta Contabil")
	public void testCreateContaContabil() {
		int numero = findAvaiableNumero();
		String contaContabilID = createContaContabil(numero);

		assertNotNull(contaContabilID);
	}

	@Test
	// @Ignore
	@DisplayName("Cria Lancamento Contabil")
	public void testCreateLancamentoContabil() {
		int numero = findAvaiableNumero();
		createContaContabil(numero);
		String id = createLancamentoContabil(numero, "20170130", "25000.15");

		assertNotNull(id);
	}

	@Test
	// @Ignore
	@DisplayName("Busca Lancamento Contabil por Id")
	public void testLancamentoContabilById() {
		int numero = findAvaiableNumero();
		createContaContabil(numero);
		String lancamentoContabilID = createLancamentoContabil(numero, "20180630", "1234");
		Response response = given().contentType("application/json").accept("application/json").when()
				.get(CONTEXT_PATH + "/lancamentos-contabeis/" + lancamentoContabilID).then()
				.contentType("application/json").extract().response();
		String contaContabil = response.jsonPath().getString("contaContabil");
		String data = response.jsonPath().getString("data");
		String valor = response.jsonPath().getString("valor");

		assertEquals(contaContabil, String.valueOf(numero));
		assertNotNull(contaContabil);
		assertNotNull(data);
		assertNotNull(valor);
	}

	@Test
	// @Ignore
	@DisplayName("Busca Lista de Lancamentos Contabeis por Numero")
	public void testLancamentosContabeisByNumero() {
		int numero = findAvaiableNumero();
		createContaContabil(numero);
		createLancamentoContabil(numero, "20180518", "-2000.15");
		createLancamentoContabil(numero, "20180518", "-1000.15");
		createLancamentoContabil(numero, "20180518", "45000.75");
		createLancamentoContabil(numero, "20180518", "122000.46");

		Response response = given().contentType("application/json").accept("application/json").when()
				.get(CONTEXT_PATH + "/lancamentos-contabeis/?contaContabil=" + numero).then()
				.contentType("application/json").extract().response();

		List<LancamentoContabilRest> lancamentos = response.jsonPath().getList("", LancamentoContabilRest.class);

		assertEquals(lancamentos.size(), 4);
	}

	@Test
	// @Ignore
	@DisplayName("Stats de Lancamentos Contabeis por Numero")
	public void testStats() {
		int numero = findAvaiableNumero();
		createContaContabil(numero);
		createLancamentoContabil(numero, "20180518", "-2000.15");
		createLancamentoContabil(numero, "20180518", "-1000.15");
		createLancamentoContabil(numero, "20180518", "45000.75");
		createLancamentoContabil(numero, "20180518", "122000.46");

		Response response = given().contentType("application/json").accept("application/json").when()
				.get(CONTEXT_PATH + "/lancamentos-contabeis/_stats/?contaContabil=" + numero).then()
				.contentType("application/json").extract().response();

		String soma = response.jsonPath().getString("soma");
		String min = response.jsonPath().getString("min");
		String max = response.jsonPath().getString("max");
		String media = response.jsonPath().getString("media");
		String qtde = response.jsonPath().getString("qtde");

		assertEquals(Double.valueOf(soma), Double.valueOf("164000.91"), 0.1);
		assertEquals(Double.valueOf(min), Double.valueOf("-2000.15"), 0.1);
		assertEquals(Double.valueOf(max), Double.valueOf("122000.46"), 0.1);
		assertEquals(Double.valueOf(media), Double.valueOf("41000.2275"), 0.1);
		assertEquals(qtde, "4");
	}

	private int findAvaiableNumero() {
		int numero = 1111000;
		while (true) {
			Response responseContaExists = given().contentType("application/json").accept("application/json").when()
					.get(CONTEXT_PATH + "/contas-contabeis/?numero=" + numero).then().contentType("application/json")
					.extract().response();

			if (responseContaExists.getStatusCode() == 404)
				break;
			numero++;
		}
		return numero;
	}

	private String createContaContabil(int numero) {
		Map<String, Object> contaContabil = new HashMap<String, Object>();
		contaContabil.put("numero", numero);
		contaContabil.put("descricao", "Teste " + numero);
		Response response = given().contentType("application/json").accept("application/json").body(contaContabil)
				.when().post(CONTEXT_PATH + "/contas-contabeis/").then().statusCode(201).contentType("application/json")
				.extract().response();
		return response.jsonPath().getString("contaContabilId");
	}

	private String createLancamentoContabil(int numero, String data, String valor) {
		Map<String, Object> lancamentoContabil = new HashMap<String, Object>();
		lancamentoContabil.put("contaContabil", numero);
		lancamentoContabil.put("data", data);
		lancamentoContabil.put("valor", valor);
		Response response = given().contentType("application/json").accept("application/json").body(lancamentoContabil)
				.when().post(CONTEXT_PATH + "/lancamentos-contabeis/").then().statusCode(201)
				.contentType("application/json").extract().response();
		return response.jsonPath().getString("id");
	}
}
