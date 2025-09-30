package com.equitybank.billing.parser.services.jsonrpc;

import com.equitybank.billing.parser.domain.model.Context;
import com.equitybank.billing.parser.services.ParserService;
import com.equitybank.billing.parser.services.impl.XmlPath;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Hashtable;

@SpringBootTest
class ServerAPITest {

	@Autowired
	private ServerAPI serverAPI;
	@Autowired
	@Qualifier("jsonPath")
	private ParserService jsonPath;
	@Autowired
	@Qualifier("xmlPath")
	private XmlPath xmlPath;

	private Context jsonContext;
	private String parserJsonResponse;

	private Context xmlContext;
	private String parserXmlResponse;

	@BeforeEach
	void setUp() {

		String jsonHttpResponseBody = "{\"status\":\"success\",\"data\":{\"declaration\":{\"id_declaration\":12345,\"num_declaration\":402000044,\"id_contribuable\":4870,\"id_propriete\":8065,\"id_centre\":2,\"montant\":3200000,\"taux\":1610,\"exercice\":\"2018\",\"date_depot\":\"2018-03-21\",\"date_limite\":\"2018-03-31\",\"motif\":\"4\",\"id_impot\":2,\"nif\":0,\"id_direction\":1,\"id_etatdeclaration\":1,\"date_saisie\":\"2018-03-21\",\"id_agent\":439,\"paiement\":0,\"degrevement\":0,\"reste\":3200000,\"id_periode\":\"1\",\"id_mep\":0,\"id_med\":0,\"attestation\":32000000,\"ref_declaration\":\"7301RL012018001779\",\"vide\":\"0\",\"annule\":0,\"Contribuable\":{\"nom\":\"MATONDO BAYOKILA CLARA\",\"nif\":\"KN010020BD\"},\"paymentAccount\":[{\"banque\":\"equitybcdc\",\"compte\":\"00011-15051307010153\",\"currency\":\"USD\"},{\"banque\":\"equitybcdc\",\"compte\":\"00011-15051307010043\",\"currency\":\"CDF\"},{\"banque\":\"bgfibank\",\"compte\":\"00031-26900-800018655018-31\",\"currency\":\"CDF\"},{\"banque\":\"bgfibank\",\"compte\":\"00031-26900-80018655019-28\",\"currency\":\"USD\"}],\"montant_usd\":\"1987.58\"}}}";

		Hashtable<String, String> jsonParserProperties = new Hashtable<>();
		jsonParserProperties.put("service", "JsonPath");
		jsonParserProperties.put("factory", "JsonFactory");

		Hashtable<String, Object> jsonPaths = new Hashtable<>();
		jsonPaths.put("currencyCode", "$.data.declaration.date_depot");
		jsonPaths.put("localCurrency", "$.data.declaration.paymentAccount[1].currency");
		jsonPaths.put("customerName", "$.data.declaration.Contribuable.nom");
		jsonPaths.put("usdCurrency", "$.data.declaration.paymentAccount[0].currency");
		jsonPaths.put("amount", "$.data.declaration.montant");
		jsonPaths.put("billNumber", "$.data.declaration.id_declaration");
		jsonPaths.put("billName", "$.data.declaration.Contribuable.nom");
		jsonPaths.put("remarks", "$.status");
		jsonPaths.put("type", "$.data.declaration.id_impot");
		jsonPaths.put("localCurrencyAccount", "$.data.declaration.paymentAccount[1].compte");
		jsonPaths.put("customerRefNumber", "$.data.declaration.Contribuable.nif");
		jsonPaths.put("dueDate", "$.data.declaration.date_limite");
		jsonPaths.put("usdAmount", "$.data.declaration.montant_usd");
		jsonPaths.put("createdOn", "$.data.declaration.date_depot");
		jsonPaths.put("usdCurrencyAccount", "$.data.declaration.paymentAccount[0].compte");

		jsonContext = new Context(jsonHttpResponseBody, null, jsonParserProperties, jsonPaths, null);

		parserJsonResponse = "{\"localCurrencyAccount\":\"00011-15051307010043\",\"amount\":3200000,\"usdAmount\":\"1987.58\",\"dueDate\":\"2018-03-31\",\"localCurrency\":\"CDF\",\"type\":2,\"createdOn\":\"2018-03-21\",\"customerName\":\"MATONDO BAYOKILA CLARA\",\"billName\":\"MATONDO BAYOKILA CLARA\",\"usdCurrency\":\"USD\",\"customerRefNumber\":\"KN010020BD\",\"usdCurrencyAccount\":\"00011-15051307010153\",\"billNumber\":12345,\"currencyCode\":\"2018-03-21\",\"remarks\":\"success\"}";

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

		xmlContext = new Context(xmlHttpResponseBody, null, xmlParserProperties, xmlPaths, null);
		parserXmlResponse = "{\"billName\":\"ECITIZEN\",\"doc_ref\":\"Bill does not exist, check reference_number and amount\",\"billNumber\":\"ST1007\",\"type\":\"1\",\"responseCode\":\"500\"}";

	}

	@Test
	void givenJsonPath_whenParse_ShouldReturnParsedResponse() throws JsonProcessingException {
		Context enrichedContext = serverAPI.parseJson(this.jsonContext);
		Assertions.assertEquals(parserJsonResponse, enrichedContext.getParserResponse());
	}

	@Test
	void givenXmlPath_whenParse_ShouldReturnParsedResponse() throws JsonProcessingException {
		Context enrichedContext = serverAPI.parseXml(this.xmlContext);
		Assertions.assertEquals(parserXmlResponse, enrichedContext.getParserResponse());
	}
}