package com.equitybank.billing.parser.domain.model;


import java.util.Hashtable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Context {

    private String httpResponseBody;
    private String parserResponse;
    private Hashtable<String, String> parseProperties;
    private Hashtable<String, Object> paths;
    private BillRequest billRequest;
    public Context() {
    }
}
