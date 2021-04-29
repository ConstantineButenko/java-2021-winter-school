package ru.croc.javaschool.finaltask.handlertest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.handler.DayHandler;
import ru.croc.javaschool.finaltask.handler.DaysHandler;
import ru.croc.javaschool.finaltask.model.db.Day;
import ru.croc.javaschool.finaltask.model.serializable.Days;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/** Тест класса обработки полученных из базы данных значений (назначение даты и соотношения температуры к давлению)
 *
 */

public class DaysHandlerTest {
    @Test
    public void handleDaysObjectTest() {
        DaysHandler handler = new DaysHandler();
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
        List<Day> newDayList = List.copyOf(dayList);
        DayHandler dayHandler = new DayHandler();
        for (Day day : newDayList) {
            dayHandler.setCorrelation(day);
            dayHandler.setDateString(day);
        }
        Assertions.assertEquals(newDayList, handler.handleDaysObject(days).getDays());
    }
}
