package com.equitybank.billing.parser.api;

import java.util.Hashtable;

import javax.xml.xpath.XPathExpressionException;

import com.equitybank.billing.parser.domain.model.Context;
import com.equitybank.billing.parser.services.impl.JsonPath;
import com.equitybank.billing.parser.services.impl.XmlPath;
import com.equitybank.billing.parser.services.jsonrpc.ServerAPIImpl;
import com.google.gson.Gson;
import com.jayway.jsonpath.Configuration;

public class Test {

	public static void main(String[] args) {

		Test test = new Test();
		// test.evaluate();
		test.evaluate();
	}

	private void Contextbuild() {
		Gson gson = new Gson();
		ServerAPIImpl serverAPI;
		String data = "{\n" + "  \"authType\": \"None\",\n" + "  \"authCredentials\": {\n" + "    \"username\": \"\",\n"
				+ "    \"password\": \"\"\n" + "  },\n" + "  \"headers\": [\n"
				+ "    \"Content-Type: application/json\"\n" + "  ],\n" + "  \"timeouts\": {\n"
				+ "    \"read\": 120000,\n" + "    \"connection\": 120000\n" + "  },\n" + "  \"ssl\": {\n"
				+ "    \"trustchain\": \"\",\n" + "    \"identity\": \"\",\n" + "    \"ssl_type\": \"one-way\"\n"
				+ "  },\n" + "  \"validationRequest\": {\n"
				+ "    \"payload\": \"<xsl:stylesheet version=\\\"2.0\\\" xmlns:saxon=\\\"http://saxon.sf.net/\\\"  xmlns:xsl=\\\"http://www.w3.org/1999/XSL/Transform\\\"><xsl:template match=\\\"/\\\" xmlns:customf=\\\"com.billing.template.services.XqueryCustomFunctions\\\"><root><soap:Envelope xmlns:xsi=\\\"http://www.w3.org/2001/XMLSchema-instance\\\" xmlns:xsd=\\\"http://www.w3.org/2001/XMLSchema\\\" xmlns:soap=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\"><soap:Body><IsThisBonaFideStudent xmlns=\\\"unisol:bankapi\\\"><strRegNo>$.bill_no</strRegNo><strUN>equityconnect</strUN><strPWD>M@c</strPWD></IsThisBonaFideStudent></soap:Body></soap:Envelope></root></xsl:template></xsl:stylesheet>\",\n"
				+ "    \"url\": \"\",\n" + "    \"dateFormat\": \"\",\n" + "    \"httpMethod\": \"POST\",\n"
				+ "    \"TemplatingEngine\": \"json\"\n" + "  },\n" + "  \"notificationRequest\": {\n"
				+ "    \"payload\": \"<xsl:stylesheet version=\\\"1.0\\\" xmlns:saxon=\\\"http://saxon.sf.net/\\\" xmlns:xsl=\\\"http://www.w3.org/1999/XSL/Transform\\\"><xsl:template match=\\\"/\\\" xmlns:customf=\\\"com.billing.template.services.XqueryCustomFunctions\\\"><root><soap:Envelope xmlns:xsi=\\\"http://www.w3.org/2001/XMLSchema-instance\\\" xmlns:xsd=\\\"http://www.w3.org/2001/XMLSchema\\\" xmlns:soap=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\"><soap:Body><ProcessStudentFees xmlns=\\\"unisol:bankapi\\\"><strRegNo>${bill_no}</strRegNo><dblAmount>${tran_amt}</dblAmount><strTransNo>$.tran_id</strTransNo><dtTransDate>${tran_date}</dtTransDate><strUN>equityconnect</strUN><strPWD>M@ch@k05@equity</strPWD></ProcessStudentFees></soap:Body></soap:Envelope></root></xsl:template></xsl:stylesheet>\",\n"
				+ "    \"url\": \"https://payments.swifthub.co.ke/api/gateway/invoive/payment\",\n"
				+ "    \"dateFormat\": \"\",\n" + "    \"httpMethod\": \"POST\",\n"
				+ "    \"TemplatingEngine\": \"json\"\n" + "  },\n" + "  \"validationResponse\": {\n"
				+ "    \"parser\": \"JsonPath\",\n" + "    \"factory\": \"JsonFactory\",\n"
				+ "    \"Content-Type\": \"application/json\",\n" + "    \"data\": {\n"
				+ "      \"billNumber\": \"$.bill_number\",\n" + "      \"customerName\": \"$.bill_name\",\n"
				+ "      \"billId\": \"$.doc_ref\"\n" + "    }\n" + "  },\n" + "  \"notificationResponse\": {\n"
				+ "    \"parser\": \"JsonPath\",\n" + "    \"factory\": \"JsonFactory\",\n"
				+ "    \"Content-Type\": \"application/json\",\n" + "    \"data\": {\n"
				+ "      \"responseCode\": \"$.status\",\n" + "      \"responseMessage\": \"$.response_desc\"\n"
				+ "    }\n" + "  }\n" + "}";
		Context context = gson.fromJson(data, Context.class);
		String d = "";
	}

	private void evaluate() {
		XmlPath xmlPath = new XmlPath();
		String xml = "<soapenv:Envelope\n" + "	xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n"
				+ "	xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"\n"
				+ "	xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">\n" + "	<soapenv:Body>\n"
				+ "		<getDecResponse\n" + "			xmlns=\"http://WS.epay.rra.rw\">\n"
				+ "			<getDecReturn>\n" + "				<TO_BANK ID=\"77\">\n"
				+ "					<DECLARATION>\n" + "						<RRA_REF>0453718324</RRA_REF>\n"
				+ "						<DEC_ID>86779808</DEC_ID>\n" + "						<TIN>100011737</TIN>\n"
				+ "						<TAX_PAYER_NAME>RAJ VERMA</TAX_PAYER_NAME>\n"
				+ "						<AMOUNT_TO_PAY>500</AMOUNT_TO_PAY>\n"
				+ "						<TAX_TYPE_NO>57</TAX_TYPE_NO>\n"
				+ "						<TAX_TYPE_DESC>IQP-PIT-QUART PREPAY</TAX_TYPE_DESC>\n"
				+ "						<TAX_CENTRE_NO>24</TAX_CENTRE_NO>\n"
				+ "						<TAX_CENTRE_DESC>NYARUGENGE TC</TAX_CENTRE_DESC>\n"
				+ "						<ASSESS_NO>12010194</ASSESS_NO>\n"
				+ "						<RRA_ORIGIN_NO>3</RRA_ORIGIN_NO>\n"
				+ "						<DEC_DATE>06/04/2021 11:04:27</DEC_DATE>\n"
				+ "						<REQUEST_DATE>20/04/2022 03:07:26</REQUEST_DATE>\n"
				+ "					</DECLARATION>\n" + "				</TO_BANK>\n" + "			</getDecReturn>\n"
				+ "		</getDecResponse>\n" + "	</soapenv:Body>\n" + "</soapenv:Envelope>";
		xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<bill>\n"
				+ "   <reference_number>ST1007</reference_number>\n" + "   <bill_name>ECITIZEN</bill_name>\n"
				+ "   <bill_type>1</bill_type>      \n"
				+ "   <status_desc>Bill does not exist, check reference_number and amount</status_desc>\n"
				+ "  <status>500</status>\n" + "</bill>";
		String path = "//*[name()='soapenv:Envelope']//*[name()='soapenv:Body']//*[name()='getDecResponse']//*[name()='getDecReturn']//*[name()='TO_BANK']//*[name()='DECLARATION']//*[name()='TAX_PAYER_NAME']";
		path = "/bill/reference_number";
		try {
			String data = xmlPath.evaluate(xml, path);
			System.out.println("Data: " + data);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void JsonParse() {
		Context context = new Context();
		JsonPath jsonPath = new JsonPath();
		Hashtable<String, Object> keys = new Hashtable<>();
		keys.put("responseCode", "$.code");
		keys.put("responseMessage", "$.xml_token");
		keys.put("xml_field_token", "$.xml_token#/token/tk60");
		keys.put("xml_field_time", "$.xml_token#/token/tk3");
		keys.put("xml_field_date", "$.xml_token#/token/tk2");
		keys.put("xml_field_unit", "$.xml_token#/token/tk71");

		context.setHttpResponseBody(
				"{\"code\":200,\"xml_token\":\"<token><tk1>SOCODEE SA<\\/tk1><tk2>24\\/07\\/21<\\/tk2><tk3>18:50<\\/tk3><tk10>98911\\/5685<\\/tk10><tk20>07\\/37\\/37224174179<\\/tk20><tk23>Pr\\u00e9paiement Electricit\\u00e9<\\/tk23><tk30>Kavuke Katembo Lusi<\\/tk30><tk31>010612359<\\/tk31><tk32>06<\\/tk32><tk21>Cr\\u00e9dit Service<\\/tk21><tk22>Cr\\u00e9dit d\\\\'\\u00e9lectrict\\u00e9<\\/tk22><tk11>1<\\/tk11><tk60>62099627056858807214<\\/tk60><tk14>94000817\\/15021650<\\/tk14><tk61>600636<\\/tk61><tk62>01<\\/tk62><tk63>1<\\/tk63><tk80>400,00<\\/tk80><tk50>1604<\\/tk50><tk40>400,00<\\/tk40><tk41>17,24<\\/tk41><tk90>Tarif &gt;0,0kWh<\\/tk90><tk71>&gt;0,0kWh @ 0.2151\\/kWh<\\/tk71><tk43>2,76<\\/tk43><tk92>TVA<\\/tk92><tk73>@ 16.0%<\\/tk73><tk12>UMOJA_POWER<\\/tk12><tk13>Accueil : +243978886017 Client\\u00e8le : +243978886018      &lt;&lt; SOCODEE S.A &gt;&gt;<\\/tk13><tk15>C A S H P O W E R<\\/tk15><\\/token>\"}");
		context.setPaths(keys);
		jsonPath.parse(context);

	}
}
