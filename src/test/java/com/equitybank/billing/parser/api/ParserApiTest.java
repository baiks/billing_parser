package com.equitybank.billing.parser.api;

import com.equitybank.billing.parser.domain.model.Context;
import com.equitybank.billing.parser.services.jsonrpc.ServerAPIImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Hashtable;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParserApi.class)
class ParserApiTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ServerAPIImpl serverAPI;

	@Test
	void givenJsonContext_whenJsonParse_shouldReturnJsonEnrichedContext() throws Exception {
		// Given
		String httpResponseBody = "{\"status\":\"success\",\"data\":{\"declaration\":{\"id_declaration\":12345,\"num_declaration\":402000044,\"id_contribuable\":4870,\"id_propriete\":8065,\"id_centre\":2,\"montant\":3200000,\"taux\":1610,\"exercice\":\"2018\",\"date_depot\":\"2018-03-21\",\"date_limite\":\"2018-03-31\",\"motif\":\"4\",\"id_impot\":2,\"nif\":0,\"id_direction\":1,\"id_etatdeclaration\":1,\"date_saisie\":\"2018-03-21\",\"id_agent\":439,\"paiement\":0,\"degrevement\":0,\"reste\":3200000,\"id_periode\":\"1\",\"id_mep\":0,\"id_med\":0,\"attestation\":32000000,\"ref_declaration\":\"7301RL012018001779\",\"vide\":\"0\",\"annule\":0,\"Contribuable\":{\"nom\":\"MATONDO BAYOKILA CLARA\",\"nif\":\"KN010020BD\"},\"paymentAccount\":[{\"banque\":\"equitybcdc\",\"compte\":\"00011-15051307010153\",\"currency\":\"USD\"},{\"banque\":\"equitybcdc\",\"compte\":\"00011-15051307010043\",\"currency\":\"CDF\"},{\"banque\":\"bgfibank\",\"compte\":\"00031-26900-800018655018-31\",\"currency\":\"CDF\"},{\"banque\":\"bgfibank\",\"compte\":\"00031-26900-80018655019-28\",\"currency\":\"USD\"}],\"montant_usd\":\"1987.58\"}}}";

		Hashtable<String, String> parserProperties = new Hashtable<>();
		parserProperties.put("service", "JsonPath");
		parserProperties.put("factory", "JsonFactory");

		Hashtable<String, Object> paths = new Hashtable<>();
		paths.put("currencyCode", "$.data.declaration.date_depot");
		paths.put("localCurrency", "$.data.declaration.paymentAccount[1].currency");
		paths.put("customerName", "$.data.declaration.Contribuable.nom");
		paths.put("usdCurrency", "$.data.declaration.paymentAccount[0].currency");
		paths.put("amount", "$.data.declaration.montant");
		paths.put("billNumber", "$.data.declaration.id_declaration");
		paths.put("billName", "$.data.declaration.Contribuable.nom");
		paths.put("remarks", "$.status");
		paths.put("type", "$.data.declaration.id_impot");
		paths.put("localCurrencyAccount", "$.data.declaration.paymentAccount[1].compte");
		paths.put("customerRefNumber", "$.data.declaration.Contribuable.nif");
		paths.put("dueDate", "$.data.declaration.date_limite");
		paths.put("usdAmount", "$.data.declaration.montant_usd");
		paths.put("createdOn", "$.data.declaration.date_depot");
		paths.put("usdCurrencyAccount", "$.data.declaration.paymentAccount[0].compte");

		Context context = new Context(httpResponseBody, null, parserProperties, paths, null);

		String parserJsonResponse = "{\"localCurrencyAccount\":\"00011-15051307010043\",\"amount\":3200000,\"usdAmount\":\"1987.58\",\"dueDate\":\"2018-03-31\",\"localCurrency\":\"CDF\",\"type\":2,\"createdOn\":\"2018-03-21\",\"customerName\":\"MATONDO BAYOKILA CLARA\",\"billName\":\"MATONDO BAYOKILA CLARA\",\"usdCurrency\":\"USD\",\"customerRefNumber\":\"KN010020BD\",\"usdCurrencyAccount\":\"00011-15051307010153\",\"billNumber\":12345,\"currencyCode\":\"2018-03-21\",\"remarks\":\"success\"}";

		Context enrichedContext = new Context(httpResponseBody, parserJsonResponse, parserProperties, paths, null);

		when(serverAPI.parseJson(context)).thenReturn(enrichedContext);

		// When
		ResultActions resultActions = mockMvc.perform(post("/api/parser/v1/parser")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(context)));

		// Then
		verify(serverAPI, times(1)).parseJson(context);

		resultActions.andExpect(status().isOk());

		Assertions.assertEquals(new ObjectMapper().writeValueAsString(enrichedContext),
				resultActions.andReturn().getResponse().getContentAsString());

	}

	@Test
	void givenXmlContext_whenXmlParse_shouldReturnXmlEnrichedContext() throws Exception {
		// Given
		String xmlHttpResponseBody = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<bill>\n"
				+ "   <reference_number>ST1007</reference_number>\n" + "   <bill_name>ECITIZEN</bill_name>\n"
				+ "   <bill_type>1</bill_type>      \n"
				+ "   <status_desc>Bill does not exist, check reference_number and amount</status_desc>\n"
				+ "  <status>500</status>\n" + "</bill>";

		Hashtable<String, String> xmlParserProperties = new Hashtable<>();
		xmlParserProperties.put("service", "XmlPath");
		xmlParserProperties.put("factory", "XmlFactory");

		Hashtable<String, Object> xmlPaths = new Hashtable<>();
		xmlPaths.put("billNumber", "/bill/reference_number");
		xmlPaths.put("doc_ref", "/bill/status_desc");
		xmlPaths.put("responseCode", "/bill/status");
		xmlPaths.put("type", "/bill/bill_type");
		xmlPaths.put("billName", "/bill/bill_name");

		Context xmlContext = new Context(xmlHttpResponseBody, null, xmlParserProperties, xmlPaths, null);
		String parserXmlResponse = "{\"billName\":\"ECITIZEN\",\"doc_ref\":\"Bill does not exist, check reference_number and amount\",\"billNumber\":\"ST1007\",\"type\":\"1\",\"responseCode\":\"500\"}";

		Context enrichedContext = new Context(xmlHttpResponseBody, parserXmlResponse, xmlParserProperties, xmlPaths,
				null);

		when(serverAPI.parseXml(xmlContext)).thenReturn(enrichedContext);

		// When
		ResultActions resultActions = mockMvc.perform(post("/api/parser/v1/parser")
				.contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(xmlContext)));

		// Then
		verify(serverAPI, times(1)).parseXml(xmlContext);

		resultActions.andExpect(status().isOk());

		Assertions.assertEquals(new ObjectMapper().writeValueAsString(enrichedContext),
				resultActions.andReturn().getResponse().getContentAsString());

	}
}