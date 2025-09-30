package com.equitybank.billing.parser.services;

import com.equitybank.billing.parser.domain.model.Context;

/**
 * ParserService interface
 */
public interface ParserService {
    Context parse(Context context);
}