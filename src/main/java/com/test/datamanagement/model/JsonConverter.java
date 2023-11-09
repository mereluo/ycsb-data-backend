package com.test.datamanagement.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

@Converter
public class JsonConverter implements AttributeConverter<List<TimeSeries>, String> {
    private final Gson gson = new GsonBuilder().create();

    @Override
    public String convertToDatabaseColumn(List<TimeSeries> attribute) {
        if (attribute == null) {
            return null;
        }
        Type type = new TypeToken<List<TimeSeries>>() {}.getType();
        return gson.toJson(attribute, type);
    }

    @Override
    public List<TimeSeries> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        Type type = new TypeToken<List<TimeSeries>>() {}.getType();
        try {
            return gson.fromJson(dbData, type);
        } catch (JsonSyntaxException e) {
            throw new IllegalArgumentException("Error deserializing JSON", e);
        }
    }
}

