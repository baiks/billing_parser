package com.equitybank.billing.parser.api;

import com.equitybank.billing.parser.domain.model.Context;
import com.equitybank.billing.parser.services.jsonrpc.ServerAPIImpl;
//import com.microsoft.applicationinsights.TelemetryClient;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/parser/v1")
@Api(value = "parser",tags = {"Operations pertaining to parser endpoints"})
public class ParserApi {
    Logger LOGGER = LoggerFactory.getLogger(ParserApi.class);
   // @Autowired
   // TelemetryClient telemetryClient;
    @Autowired
    ServerAPIImpl serverAPI;

   // @ApiOperation("parser")

    @PostMapping( "parser")
    Context validateBiller(@RequestBody Context context) {
        LOGGER.info("DATA {} ", context);
        if (context.getParseProperties().get("service").equalsIgnoreCase("XmlPath")) {
            return serverAPI.parseXml(context);
        }
        return serverAPI.parseJson(context);
    }
      @PostMapping( "parse")
    public String toconfirm(){
        //LOGGER.info("DATA {} ", context);
     
        return "reached pipeline";
    }
}
