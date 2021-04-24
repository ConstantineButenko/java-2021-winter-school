package ru.croc.javaschool.finaltask.xmlconvertertest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.model.Day;
import ru.croc.javaschool.finaltask.xmlconverter.XMLConverter;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

/** Тест конвертации объектов в формат xml */


public class ConverterTest {
    @Test
    public void converterTest() throws IOException {
        XMLConverter converter = new XMLConverter();
        Day day = new Day(1, LocalDate.of(2021, Month.MARCH, 3), 11.6f, 760);
        day.setDateString(day.getDate().toString());
        day.setCorrelation(day.getTemperature() / day.getPressure());
        System.out.println(day);
        String xml = converter.toXML(day);
        day.setDate(null);
        day.setId(0);
        day.setTemperature(0);
        day.setPressure(0);
        System.out.println(converter.fromXML(xml, Day.class));
        Assertions.assertEquals(day, converter.fromXML(xml, Day.class));
    }
}
