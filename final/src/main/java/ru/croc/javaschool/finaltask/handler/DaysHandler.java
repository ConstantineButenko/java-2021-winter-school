package ru.croc.javaschool.finaltask.handler;

import ru.croc.javaschool.finaltask.model.db.Day;
import ru.croc.javaschool.finaltask.model.serializable.Days;

/** Обработчик объекта класса Days
 *
 */

public class DaysHandler {
    /** Метод для установки значений всем объектам класса Day для последующей сериализации. Применяет к ним методы
     * setDateString и setCorrelation соответствующего обработчика.
     * @param days Объект класса Days
     * @return Измененный объект класса Days
     */
    public Days handleDaysObject(Days days) {
        DayHandler handler = new DayHandler();
        for (Day day : days.getDays()) {
            handler.setDateString(day);
            handler.setCorrelation(day);
        }
        return days;
    }
}
