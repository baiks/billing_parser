package com.equitybank.billing.parser.services.impl;

import com.equitybank.billing.parser.domain.model.Context;
import com.equitybank.billing.parser.services.ParserService;
import com.microsoft.applicationinsights.boot.dependencies.apachecommons.lang3.StringUtils;
import com.microsoft.applicationinsights.web.dependencies.apachecommons.lang3.StringEscapeUtils;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.Hashtable;
import java.util.Objects;

/**
 * Concrete XmlPath
 */
@Slf4j
@Service("xmlPath")
public class XmlPath implements ParserService {
	@Override
	public Context parse(Context context) {

		Hashtable<String, String> responses = new Hashtable<>();

		String xml = context.getHttpResponseBody();
		log.info("Http Response Body: {}", xml);

		Hashtable<String, Object> keys = context.getPaths();
		log.info("Keys: {}", keys);

		try {
			keys.entrySet().forEach(e -> {
				try {
					responses.put(e.getKey(), evaluate(xml, String.valueOf(e.getValue())));
				} catch (XPathExpressionException xPathExpressionException) {
					log.info("ERROR {} ", xPathExpressionException.getMessage());
				}
			});
			JSONObject response = new JSONObject(responses);

			/* If biller returns customer name but the response has no bill number */
			if (response.has("billNumber") && context.getBillRequest() != null) {
				if (response.has("customerName")) {
					String customerName = response.get("customerName").toString();
					log.info("Customer Name {} ", customerName);
					if (Objects.nonNull(response.get("customerName").toString()) && !Objects.equals("", customerName)) {
						responses.put("billNumber", context.getBillRequest().getBillerReference());
					}
				}
			}
			context.setParserResponse(response.toString());
			log.info("Parser Response: {}", response.toString());
			return context;
		} catch (NullPointerException e) {
			return context;
		}

	}

	@SuppressWarnings("deprecation")
	public String evaluate(String xml, String path) throws XPathExpressionException {
		try {
			xml = xml.replaceAll("\\<\\?xml(.+?)\\?\\>", "").trim();
			InputSource inputXML = new InputSource(new StringReader(xml));
			XPath xPath = XPathFactory.newInstance().newXPath();
			String result = xPath.evaluate(path, inputXML);
			return result;
		} catch (Exception ex) {
			log.info("ERROR {}", ex.getMessage());
		}
		return "not found";
	}
}