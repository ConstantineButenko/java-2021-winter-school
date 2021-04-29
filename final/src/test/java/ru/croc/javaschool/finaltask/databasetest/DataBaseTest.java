package ru.croc.javaschool.finaltask.databasetest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.database.dbprovider.DataSourceProvider;
import ru.croc.javaschool.finaltask.database.repository.DayRepository;
import ru.croc.javaschool.finaltask.database.service.DayRepositoryService;
import ru.croc.javaschool.finaltask.model.db.Day;
import ru.croc.javaschool.finaltask.model.serializable.Days;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/** Тест методов работы с базой данных
 *
 */

public class DataBaseTest {
    @Test
    public void dataBaseTest() throws IOException {
        DayRepositoryService service = new DayRepositoryService(new DayRepository(
                new DataSourceProvider().getDataSource()));
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

        service.create(day1);
        service.create(day2);
        service.create(day3);
        service.create(day4);

        Assertions.assertEquals(days, service.get(LocalDate.of(2020, Month.JANUARY, 1),
                LocalDate.of(2020, Month.DECEMBER, 31)));
    }
}
