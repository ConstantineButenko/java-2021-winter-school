package ru.croc.javaschool.finaltask.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDate;
import java.util.Objects;

/** Класс "День"
 *
 */

@XmlRootElement(name = "day")
public class Day {
    /** id объекта */
    @XmlTransient
    private int id;

    /** Представление даты в формате LocalDate */
    @XmlTransient
    private LocalDate date;

    /** Температура */
    @XmlTransient
    private float temperature;

    /** Атмосферное давление */
    @XmlTransient
    private int pressure;

    /** Строковое представление даты */
    @XmlElement(name = "date")
    private String dateString;

    /** Соотношение температуры к атмосферному давлению */
    @XmlElement(name = "correlation")
    private float correlation;


    public Day(int id, LocalDate date, float temperature, int pressure) {
        this.id = id;
        this.date = date;
        this.temperature = temperature;
        this.pressure = pressure;
    }

    public Day() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public float getCorrelation() {
        return correlation;
    }

    public void setCorrelation(float correlation) {
        this.correlation = correlation;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return id == day.id && Float.compare(day.temperature, temperature) == 0 && pressure == day.pressure &&
                Float.compare(day.correlation, correlation) == 0 && Objects.equals(date, day.date) &&
                Objects.equals(dateString, day.dateString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, temperature, pressure, dateString, correlation);
    }

    @Override
    public String toString() {
        return "Day{" +
                "id=" + id +
                ", date=" + date +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                ", dateString='" + dateString + '\'' +
                ", correlation=" + correlation +
                '}';
    }
}
