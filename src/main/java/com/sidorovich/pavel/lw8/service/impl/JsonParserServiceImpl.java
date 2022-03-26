package com.sidorovich.pavel.lw8.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sidorovich.pavel.lw8.model.User;
import com.sidorovich.pavel.lw8.service.JsonParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class JsonParserServiceImpl implements JsonParserService<User> {

    private final ObjectMapper objectMapper;

    public JsonParserServiceImpl() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public List<User> findAll(String filename) {
        try {
            final File input = new File(filename);

            return objectMapper.readValue(input, new TypeReference<List<User>>() {
            });
        } catch (IOException ex) {
            log.error("JSON marshalling error", ex);
        }
        return Collections.emptyList();
    }

    @Override
    public void saveAll(List<User> models, String filename) {
        try {
            final File output = new File(filename);

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(output, models);
        } catch (IOException ex) {
            log.error("JSON marshalling error", ex);
        }
    }

}
