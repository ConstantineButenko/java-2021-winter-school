package ru.croc.javaschool.finaltask.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/** Класс, представляющий множество объектов класса Day
 *
 */

@XmlRootElement(name = "days")
public class Days {
    /** Список объектов класа Day */
    @XmlElement(name = "day")
    private List<Day> days;

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public Days(List<Day> days) {
        this.days = days;
    }

    public Days() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Days days1 = (Days) o;
        return days.equals(days1.days);
    }

    @Override
    public int hashCode() {
        return Objects.hash(days);
    }

    @Override
    public String toString() {
        return "Days{" +
                "days=" + days +
                '}';
    }
}
