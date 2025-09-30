package com.equitybank.billing.parser.services.jsonrpc;

import com.equitybank.billing.parser.domain.model.Context;
import com.equitybank.billing.parser.services.ParserService;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@AutoJsonRpcServiceImpl
public class ServerAPIImpl implements ServerAPI {

    Logger log = LoggerFactory.getLogger(ServerAPIImpl.class);

    private final ParserService jsonParser;
    private final ParserService xmlParser;

    @Autowired
    public ServerAPIImpl(@Qualifier("jsonPath") ParserService jsonParser, @Qualifier("xmlPath") ParserService xmlParser) {
        this.jsonParser = jsonParser;
        this.xmlParser = xmlParser;
    }

    @Override
    public Context parseJson(Context context) {
        try {
            //parsing json

            log.info("Factory {}", context.getParseProperties().get("factory"));
            log.info("Service {}", context.getParseProperties().get("service"));

            if (context.getParseProperties().get("factory").equalsIgnoreCase("JsonFactory") &&
                    context.getParseProperties().get("service").equalsIgnoreCase("JsonPath")
            ) {
                return jsonParser.parse(context);
            } else {
                log.info("{} with given name doesn't exist.", context.getParseProperties().get("service"));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Context parseXml(Context context) {
        try {
            //parsing xml

            log.info("Factory {}", context.getParseProperties().get("factory"));
            log.info("Service {}", context.getParseProperties().get("service"));

            if (context.getParseProperties().get("factory").equalsIgnoreCase("XmlFactory") &&
                    context.getParseProperties().get("service").equalsIgnoreCase("XmlPath")
            ) {
                return xmlParser.parse(context);
            } else {
                log.info("{} with given name doesn't exist.", context.getParseProperties().get("service"));
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}