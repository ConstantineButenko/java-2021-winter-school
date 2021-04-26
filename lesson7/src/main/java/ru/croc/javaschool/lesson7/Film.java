package ru.croc.javaschool.lesson7.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/** Объект "Фильм"
 *
 */
public class Film {

    /** Статическое поле для реализации автоинкремента */

    private static int idCounter = 1; // У меня не получилось придумать способ реализовать автоинкремент
                                      // без дополнительной переменной, возможно ли другое решение?

    /** Уникальный идентификатор-первичный ключ */

    private int id;

    /** Название фильма */

    private String title;

    /** Жанр (один или несколько) */

    private String genre;

    /** Бюджет (млн. долларов) */

    private int budget;

    /** Получал ли премию "Оскар" хотя бы в одной номинации */

    private boolean gotOscar;

    /** Фильм на иностранном (не английском) языке */

    private boolean onForeignLanguage;

    /** Дата выхода на экран */

    private LocalDate releaseDate;

    /** Продолжительность */

    private LocalTime duration;

    public Film(String title, String genre, int budget, boolean gotOscar, boolean onForeignLanguage,
                LocalDate releaseDate, LocalTime duration) {
        id = idCounter++;
        this.title = title;
        this.genre = genre;
        this.budget = budget;
        this.gotOscar = gotOscar;
        this.onForeignLanguage = onForeignLanguage;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public Film(int id, String title, String genre, int budget, boolean gotOscar, boolean onForeignLanguage,
                LocalDate releaseDate, LocalTime duration) {
        this(title, genre, budget, gotOscar, onForeignLanguage, releaseDate, duration);
        this.id = id;
    }

    public Film() {
    }

    public Film(int id) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public boolean isGotOscar() {
        return gotOscar;
    }

    public void setGotOscar(boolean gotOscar) {
        this.gotOscar = gotOscar;
    }

    public boolean isOnForeignLanguage() {
        return onForeignLanguage;
    }

    public void setOnForeignLanguage(boolean onForeignLanguage) {
        this.onForeignLanguage = onForeignLanguage;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", budget=" + budget +
                ", gotOscar=" + gotOscar +
                ", onForeignLanguage=" + onForeignLanguage +
                ", releaseDate=" + releaseDate +
                ", duration=" + duration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Film)) return false;
        Film film = (Film) o;
        return getId() == film.getId() && getBudget() == film.getBudget() && isGotOscar() == film.isGotOscar() && isOnForeignLanguage() == film.isOnForeignLanguage() && getTitle().equals(film.getTitle()) && getGenre().equals(film.getGenre()) && getReleaseDate().equals(film.getReleaseDate()) && getDuration().equals(film.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getGenre(), getBudget(), isGotOscar(), isOnForeignLanguage(), getReleaseDate(), getDuration());
    }
}
