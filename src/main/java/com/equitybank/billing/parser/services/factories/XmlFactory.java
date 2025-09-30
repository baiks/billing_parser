package com.equitybank.billing.parser.services.factories;

import com.equitybank.billing.parser.services.ParserService;
import lombok.extern.slf4j.Slf4j;

/**
 * Concrete factory for xml parsing
 */

@Slf4j
public class XmlFactory extends AbstractFactory {
    @Override
    public ParserService getParser(String name) {

        try {
            Class<?> parserServiceClass = Class.forName("com.equitybank.billing.parser.services.impl." + name);
            return (ParserService) parserServiceClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException exception) {
            log.info("Error {}", exception.getMessage());
            return null;
        }
    }
}