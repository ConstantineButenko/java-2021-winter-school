package ru.croc.javaschool.finaltask.handler;

import ru.croc.javaschool.finaltask.model.Day;

import java.time.LocalDate;

/** Обработчик для объектов класса "День", обращается к соответствующим сеттерам и устанавливает передаваемое значение
 *
 */

public class DayHandler {

    /** Установка id у передаваемого объекта
     *
     * @param day
     * @param id
     */

    public void setId(Day day, int id) { day.setId(id); }

    /** Установка даты у передаваемого объекта
     *
     * @param day
     * @param date
     */


    public void setDate(Day day, LocalDate date) {
        day.setDate(date);
    }

    /** Установка температуры у передаваемого объекта
     *
     * @param day
     * @param temperature
     */

    public void setTemperature(Day day, float temperature) {
        day.setTemperature(temperature);
    }

    /** Установка давления у передаваемого объекта
     *
     * @param day
     * @param pressure
     */

    public void setPressure(Day day, int pressure) {
        day.setPressure(pressure);
    }

    /** Установка соотношения температуры к атмосферному давлению у передаваемого объекта
     *
     * @param day
     */

    public void setCorrelation(Day day) {
        day.setCorrelation(day.getTemperature() / day.getPressure());
    }

    /** Установка строкового представления даты у передаваемого объекта, необходимо для корректной сериализации
     *
     * @param day
     */

    public void setDateString(Day day) {
        day.setDateString(day.getDate().toString());
    }
}
