package ru.croc.javaschool.finaltask.database.service;

import ru.croc.javaschool.finaltask.database.repository.DayRepository;
import ru.croc.javaschool.finaltask.model.Day;
import ru.croc.javaschool.finaltask.model.Days;

import java.time.LocalDate;

/** Промежуточный объект, осуществляющий доступ к методам репозитория
 *
 */

public class DayRepositoryService {
    /** Объект репозитория, инициализируется один раз и используется постоянно в дальнейшем */

    private final DayRepository repository;

    public DayRepositoryService(DayRepository repository) {
        this.repository = repository;
    }

    /** Обращение к соответствующему методу create репозитория
     *
     * @param day Объект класса "День"
     */

    public void create(Day day) {
        repository.create(day);
    }

    /** Обращение к соответствующему методу репозитория
     *
     * @param from Объект LocalDate (нижняя граница диапазона)
     * @param to Объект LocalDate (верхняя граница диапазона)
     * @return Объект класса Days
     */

    public Days get(LocalDate from, LocalDate to) {
        return repository.get(from, to);
    }
}
