package com.equitybank.billing.parser.services.factories;


import com.equitybank.billing.parser.services.ParserService;

/**
 * Abstract Factory
 */
public abstract class AbstractFactory {
    public abstract ParserService getParser(String name);
}