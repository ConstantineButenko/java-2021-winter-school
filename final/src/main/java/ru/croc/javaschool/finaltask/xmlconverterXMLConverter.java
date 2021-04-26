package ru.croc.javaschool.finaltask.xmlconverter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

import java.io.IOException;

/** Класс "Обработчик XML", позволяет создавать объекты, предоставляющие методы сериализации в формат XML и
 * десериализации из него
 *
 */

public class XMLConverter {
    /** Метод "Перевести в формат XML", принимает объект произвольного типа, возвращает строку в формате xml.
     * Выбрасывает исключение.
     * @param object Объект типа, который требуется сериализовать
     * @return Строка в формате XML
     * @throws JsonProcessingException Ошибка сериализации
     */
    public String toXML(Object object) throws JsonProcessingException {
        return createXMLMapper().writeValueAsString(object);
    }

    /** Метод "Получить объект из формата XML", принимает строку в формате XML и класс ожидаемого объекта, возвращает
     * объект ожидаемого типа. Выбрасывает исключение ввода-вывода.
     * @param xml Строка в формате xml
     * @param type Класс ожидаемого объекта, задается через T.class
     * @param <T> Любой тип
     * @return Объект ожидаемого типа
     * @throws IOException Ошибка, связанная с возможным неправильным прочтением данных
     */
    public <T> T fromXML(String xml, Class <T> type) throws IOException {
        return createXMLMapper().readValue(xml, type);
    }

    /** Метод "Создать XML-Mapper". Возвращает настроенный mapper для работы с XML.
     *
     * @return XmlMapper
     */
    private XmlMapper createXMLMapper() {
        XmlMapper mapper = new XmlMapper();
        mapper.registerModule(new JaxbAnnotationModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setDefaultUseWrapper(false);
        return mapper;
    }
}
