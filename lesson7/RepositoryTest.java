package ru.croc.javaschool.lesson7;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.lesson7.dbprovider.DataSourceProvider;
import ru.croc.javaschool.lesson7.model.Film;
import ru.croc.javaschool.lesson7.repository.FilmRepository;
import ru.croc.javaschool.lesson7.service.FilmService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

/** Класс-тест для методов репозитория
 *
 */

public class RepositoryTest {
    private FilmService service = new FilmService(new FilmRepository(new DataSourceProvider().getDataSource()));
        public RepositoryTest() throws IOException {
    }

    /** Демо-сценарий с поочередным вызовом всех методов и проверками Assertions. Так как значения в таблице изменяются
     * в процессе прохождения тестов для некоторых методов репозитория (create, update) и в последующем тесты могут
     * выдавать ошибки, удобнее будет объединить все тесты в одном методе.
     *
     */
    @Test
    public void repositoryDemoTest() {
            Film film1 = new Film("Джанго освобожденный", "Боевик, драма, комедия", 100,
            true, false, LocalDate.of(2012, Month.DECEMBER, 25),
            LocalTime.of(2, 45, 21));
        service.create(film1);

        Film film2 = new Film("Темный рыцарь", "Боевик, драма, триллер", 185,
                true, false, LocalDate.of(2008, Month.JULY, 18),
                LocalTime.of(2, 32, 17));
        service.create(film2);

        Film film3 = new Film( "Форрест Гамп", "Трагикомедия", 55,
                true, false, LocalDate.of(1994, Month.JUNE, 23),
                LocalTime.of(2, 22, 48));
        service.create(film3);

        List<Film> filmList = new ArrayList<>();
        filmList.add(film1);
        filmList.add(film2);
        filmList.add(film3);



        /** Тест методов create/readAll, а так же вывод списка фильмов для наглядности */
        for (Film film : service.readAll()) {
            System.out.println(film);
        }
        Assertions.assertEquals(filmList, service.readAll());

        /** Тест метода read (параметры: id получаемого объекта) */
        Assertions.assertEquals(film1, service.read(1));
        Assertions.assertEquals(film2, service.read(2));

        /** Тест метода update (параметры: id фильма, изменяемый столбец, новое значение) */
        film1.setBudget(9999);
        film2.setTitle("Бэтмен");
        service.update(1, "budget", 9999);
        service.update(2, "title", "Бэтмен");
        Assertions.assertEquals(film1, service.read(1));
        Assertions.assertEquals(film2, service.read(2));

        /** Тест метода updateAll (параметры: изменяемый столбец, новое значение) */
        for (Film film : filmList) {
            film.setOnForeignLanguage(true);
            film.setDuration(LocalTime.of(10, 10, 10));
            film.setGenre("Мультфильм");
        }
        service.updateAll("foreign_language", true);
        service.updateAll("duration", LocalTime.of(10, 10, 10));
        service.updateAll("genre", "Мультфильм");
        Assertions.assertEquals(filmList, service.readAll());
        /** Тест метода delete (параметры: id удаляемой записи) */
        filmList.remove(1);
        service.delete(2);
        Assertions.assertEquals(filmList, service.readAll());

        /** Тест метода deleteAll */
        filmList.clear();
        service.deleteAll();
        Assertions.assertEquals(filmList, service.readAll());
    }
}
