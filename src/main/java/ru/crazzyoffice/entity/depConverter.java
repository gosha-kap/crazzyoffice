package ru.crazzyoffice.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class depConverter implements AttributeConverter<DEPARTMENT,String> {
    @Override
    public String convertToDatabaseColumn(DEPARTMENT department) {
        if (department==null) return null;
        return department.getCode();
    }

    @Override
    public DEPARTMENT convertToEntityAttribute(String s) {
        if(s == null) return null;
        return Stream.of(DEPARTMENT.values()).filter(x->x.getCode().equals(s)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
