package ru.croc.javaschool.finaltask.xmlwritertest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.model.db.Day;
import ru.croc.javaschool.finaltask.model.serializable.Days;
import ru.croc.javaschool.finaltask.xmlconverter.XMLConverter;
import ru.croc.javaschool.finaltask.xmlwriter.XMLWriter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/** Тест класса XMLWriter
 *
 */

public class XMLWriterTest {
    @Test
    public void handleAndWriteTest() throws JsonProcessingException, FileNotFoundException {
        XMLWriter writer = new XMLWriter();
        List<Day> dayList = new ArrayList<>();
        Day day1 = new Day(1, LocalDate.of(2020, Month.MARCH, 3),
                17.5f, 750);
        Day day2 = new Day(2, LocalDate.of(2020, Month.SEPTEMBER, 17),
                19.6f, 755);
        Day day3 = new Day(3, LocalDate.of(2020, Month.OCTOBER, 17),
                11.4f, 748);
        Day day4 = new Day(4, LocalDate.of(2020, Month.OCTOBER, 31),
                9.7f, 753);
        dayList.add(day1);
        dayList.add(day2);
        dayList.add(day3);
        dayList.add(day4);
        Days days = new Days(dayList);
        String xml = new XMLConverter().toXML(days);
        writer.convertAndWrite(days, "testFile.xml");
        try (BufferedReader reader = new BufferedReader(new FileReader("testFile.xml")) ) {
            String line = reader.readLine();
            String string = "";
            while (line != null) {
                string += line + System.lineSeparator();
                line = reader.readLine();
            }
            Assertions.assertEquals(xml, string);
        } catch (IOException e) {
            System.out.println("Something bad happened: " + e.getMessage());
        }
    }

}
