package com.bushemi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoaderService {
    private static final Logger LOG = LoggerFactory.getLogger("PropertiesLoaderService");
    private static final String PROPERTY_FILE_NAME = "application.properties";
    private static final Properties props = new Properties();

    public PropertiesLoaderService() {
        try (InputStream in =
                     DbConnectionService.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME)) {
            props.load(in);
        } catch (IOException e) {
            LOG.error("Не могу загрузить проперти файл с указанным именем {}", PROPERTY_FILE_NAME);
        }
    }

    public Properties getProps() {
        return props;
    }
}
