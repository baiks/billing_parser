package com.equitybank.billing.parser.services.factories;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;

/**
 * Factory provider
 */
@Slf4j
public class FactoryProvider {

    public static AbstractFactory getFactory(String factoryType) {

        try {
            Class<?> abstractFactoryClass = Class.forName("com.equitybank.billing.parser.services.factories." + factoryType);
            return (AbstractFactory) abstractFactoryClass.getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException exception) {
            log.info("Error {}", exception.getMessage());
            return null;
        }
    }
}