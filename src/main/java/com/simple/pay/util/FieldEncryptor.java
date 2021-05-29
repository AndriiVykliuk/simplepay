package com.simple.pay.util;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.Base64;

public class FieldEncryptor implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (StringUtils.isBlank(attribute)) {
            return null;
        }
        return Base64.getEncoder().encodeToString(attribute.getBytes());
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (StringUtils.isBlank(dbData)) {
            return null;
        }
        byte[] decodedBytes = Base64.getDecoder().decode(dbData);
        return new String(decodedBytes);
    }
}
