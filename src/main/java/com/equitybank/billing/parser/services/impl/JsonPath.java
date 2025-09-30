package com.equitybank.billing.parser.services.impl;

import com.equitybank.billing.parser.domain.model.Context;
import com.equitybank.billing.parser.services.ParserService;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.PathNotFoundException;
import com.microsoft.applicationinsights.boot.dependencies.apachecommons.lang3.StringUtils;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.Hashtable;
import java.util.Objects;

import javax.xml.xpath.XPathExpressionException;

/**
 * Concrete JsonPath
 */

@Service("jsonPath")
public class JsonPath implements ParserService {

	Logger log = LoggerFactory.getLogger(JsonPath.class);
	@Autowired
	XmlPath xmlPath = new XmlPath();

	@Override
	public Context parse(Context context) {
		Hashtable<String, Object> responses = new Hashtable<>();
		String data = context.getHttpResponseBody();
		log.info("data={}", data);
		Hashtable<String, Object> keys = context.getPaths();
		log.info("===========" + keys);
		try {
			Object document = Configuration.defaultConfiguration().jsonProvider().parse(data);
			log.info("Document: {}", document);
			keys.keySet().forEach(key -> {
				try {
					log.info("parsing key {} {}", key, keys.get(key));
					if (key.contains("xml_field")) {
						String field = keys.get(key).toString().split("#")[0];
						String path = keys.get(key).toString().split("#")[1];
						String xml_data = xmlPath.evaluate(com.jayway.jsonpath.JsonPath.read(document, field), path);
						responses.put(key, xml_data);
					} else if (keys.get(key).toString().contains("$")) {
						responses.put(key, com.jayway.jsonpath.JsonPath.read(document, String.valueOf(keys.get(key))));
					} else {
						responses.put(key, keys.get(key));
					}
				} catch (Exception  exception) {
					log.error("Path {} not found", key);
					responses.put(key, "Value for " + key + " not found");
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
			log.info("response= {} ", response);
			context.setParserResponse(response.toString());
			responses.clear();
			return context;
		} catch (Exception e) {
			log.info("Exception:  ", e);
			return context;
		}
	}
}