package com.equitybank.billing.parser.services.jsonrpc;

import com.equitybank.billing.parser.domain.model.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcService;

@JsonRpcService("json-rpc")
public interface ServerAPI {
    Context parseJson(@JsonRpcParam(value = "context") Context context) throws JsonProcessingException;

    Context parseXml(@JsonRpcParam(value = "context")Context context);
}