package com.equitybank.billing.parser.services.impl;

import com.equitybank.billing.parser.domain.model.Context;
import com.equitybank.billing.parser.services.ParserService;
import org.springframework.stereotype.Service;

@Service("xslt")
public class Xslt implements ParserService {
    @Override
    public Context parse(Context context) {
        return context;
    }
}
