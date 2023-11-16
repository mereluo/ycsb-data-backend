package com.test.datamanagement.model;

import com.google.gson.Gson;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class JsonConverter implements AttributeConverter<TimeSeries, String> {
    private final static Gson GSON = new Gson();

    @Override
    public String convertToDatabaseColumn(TimeSeries mjo) {
        return GSON.toJson(mjo);
    }

    @Override
    public TimeSeries convertToEntityAttribute(String dbData) {
        return GSON.fromJson(dbData, TimeSeries.class);
    }
}
