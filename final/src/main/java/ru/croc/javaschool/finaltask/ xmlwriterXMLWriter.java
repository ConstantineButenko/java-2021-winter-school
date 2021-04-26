package ru.croc.javaschool.finaltask.xmlwriter;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.croc.javaschool.finaltask.model.Days;
import ru.croc.javaschool.finaltask.xmlconverter.XMLConverter;

import java.io.FileWriter;
import java.io.IOException;

/** Класс, предоставляющий метод для конвертирования и записи в XML-файл
 *
 */

public class XMLWriter {
    /** Конвертация и запись в файл результата запроса к базе данных и обработки полученных значений
     *
     * @param days Объект класса Days
     * @param fileName Путь к файлу
     * @throws JsonProcessingException
     */

    public void convertAndWrite(Days days, String fileName) throws JsonProcessingException {
        XMLConverter converter = new XMLConverter();
        String xml = converter.toXML(days);
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(xml);
        } catch (IOException e) {
            System.out.println("Something bad happened: " + e.getMessage());
        }
    }
}
