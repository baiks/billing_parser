package com.equitybank.billing.parser.services.impl;

import com.equitybank.billing.parser.domain.http.Response;
import com.equitybank.billing.parser.domain.model.Context;
import com.equitybank.billing.parser.services.ParserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Concrete XmlQuery
 */
@Service("xmlQuery")
public class XmlQuery implements ParserService {

    public List<String> keys = new ArrayList<>();
    Logger LOGGER = LoggerFactory.getLogger(XmlQuery.class);

    @Override
    public Context parse(Context context) {
        LOGGER.info("TASK {} DATA {} ","XQuery parsing ....",context);
        keys.add("code");
        keys.add("status");
        keys.add("message");
        return context;
    }
}