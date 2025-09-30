package com.equitybank.billing.parser.services.factories;

import com.equitybank.billing.parser.services.ParserService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * Concrete json factory
 */
@Slf4j(topic = "parserService")
public class JsonFactory extends AbstractFactory {
    @Override
    public ParserService getParser(String name) {
        try {
            Class<?> parserServiceClass = Class.forName("com.equitybank.billing.parser.services.impl." + name);
            return (ParserService) parserServiceClass.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException exception) {
            log.info("Error {}", exception.getMessage());
            return null;
        }
    }
}