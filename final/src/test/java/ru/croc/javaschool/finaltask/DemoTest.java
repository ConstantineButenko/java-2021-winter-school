package ru.croc.javaschool.finaltask;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.database.dbprovider.DataSourceProvider;
import ru.croc.javaschool.finaltask.database.repository.DayRepository;
import ru.croc.javaschool.finaltask.database.service.DayRepositoryService;
import ru.croc.javaschool.finaltask.handler.DaysHandler;
import ru.croc.javaschool.finaltask.model.db.Day;
import ru.croc.javaschool.finaltask.model.serializable.Days;
import ru.croc.javaschool.finaltask.xmlconverter.XMLConverter;
import ru.croc.javaschool.finaltask.xmlwriter.XMLWriter;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.Random;

/** Демо-сценарий с демонстрацией работы программы
 * Несколько разъяснений по поводу возможных замечаний к работе программы:
 * 1. Значение float в поле correlation не отформатировано до определенного числа знаков после точки. Дело в том,
 * что реальные значения температур и атмосферного давления - это от -50 до +50 и 730-780 соответственно (не считая
 * нескольких крайних случаев). Отношение всегда будет очень небольшим, и при округлении потеряется необходимая
 * точность. Кроме того, именно такие значения, насколько мне приходилось видеть, используются при анализе данных,
 * визуализации и т.д.
 * 2. Я использую System.lineSeparator() для получения разделителя линий в зависимости от системы.
 * На Windows он работает корректно, на остальных системах тоже должен работать.
 * 3. В качестве PRIMARY KEY в таблице я выбрал дату, по той причине, что двух разных дней с одинаковой датой быть не
 * может.
 * 4. Не знаю, стоило ли делать вывод и списка объектов, и xml-строки, но я подумал, что так будет удобнее.
 * 5. Не знаю, стоило ли реализовывать остальные CRUD-операции для базы данных. Поскольку этого не было в условии,
 * реализацию update и delete я не делал.
 * 6. В классе Day два представления даты - одно типа LocalDate (для работы с базой данных), второе String (для перевода
 * в xml). Это связано с тем, что при переводе в XML LocalDate, как ссылочный тип, нес в себе много лишней информации.
 * 7. Не знаю, нужно ли было делать модульные тесты для модели. Там только геттеры и сеттеры.
 */

public class DemoTest {
    @Test
    public void demo() throws IOException {
        /* Занесение в таблицу рандомизированных значений */

        DayRepositoryService service = new DayRepositoryService(new DayRepository(new DataSourceProvider().
                getDataSource()));
        DaysHandler handler = new DaysHandler();
        Random random = new Random();
         for (int i = 1; i <= 24; ++i) {
            service.create(new Day(i, LocalDate.of(2021, Month.APRIL, i),
                    random.nextInt() % 40 + ((float) random.nextInt() % 10) / 10,
                    (random.nextInt() % 400) + 400));
        }
         /* Получение результата из базы данных и вывод списка объектов после обработки */

        Days days = service.get(LocalDate.of(2021, Month.APRIL, 5),
                LocalDate.of(2021, Month.APRIL, 20));
        for (Day day : handler.handleDaysObject(days).getDays()) {
            System.out.println(day);
        }
        System.out.println("========================================================================================");
        /* Конвертация в формат XML и запись в файл */

        String xml = new XMLConverter().toXML(days);
        System.out.println(xml);
        XMLWriter writer = new XMLWriter();
        writer.convertAndWrite(days, "result.xml");
        /* Чтение из файла и сравнение со строкой, полученной в результате работы программы */

        String result = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("result.xml"))) {
            String line = reader.readLine();
            while (line != null) {
                result += line + System.lineSeparator();
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.getMessage();
        }
        Assertions.assertEquals(xml, result);
    }
}
