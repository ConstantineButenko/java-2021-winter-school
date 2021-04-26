package ru.croc.javaschool.lesson7.service;

import ru.croc.javaschool.lesson7.model.Film;
import ru.croc.javaschool.lesson7.repository.FilmRepository;

import java.util.List;

/** Прикладной сервис для работы с методами репозитория
 *
 */

public class FilmService {
    /** Объект используемого репозитория, инициализируется один раз и используется при дальнейшей работе с методами */
    private final FilmRepository repository;

    public FilmService (FilmRepository repository) {
        this.repository = repository;
    }

    /** Метод read, вызывает соответствующий метод репозитория
     *
     * @param id
     * @return объект класса "Фильм"
     */

    public Film read(int id) {
        return repository.read(id);
    }

    /** Метод readAll, вызывает соответствующий метод репозитория
     *
     * @return ArrayList объектов класса "Фильм"
     */

    public List<Film> readAll() {
        return repository.readAll();
    }
    /** Метод create, вызывает соответствующий метод репозитория */

    public void create(Film film) {
        repository.create(film);
    }

    /** Метод delete, вызывает соответствующий метод репозитория
     *
     * @param id
     */

    public void delete(int id) {
        repository.delete(id);
    }

    /** Метод deleteAll, вызывает соответствующий метод репозитория */

    public void deleteAll() {
        repository.deleteAll();
    }

    /** Метод update, вызывает соответствующий метод репозитория
     *
     * @param id
     * @param column
     * @param value
     * @param <T>
     */

    public<T> void update(int id, String column, T value) {
        repository.update(id, column, value);
    }

    /** Метод updateAll, вызывает соответствующий метод репозитория
     *
     * @param column
     * @param value
     * @param <T>
     */

    public<T> void updateAll(String column, T value) {
        repository.updateAll(column, value);
    }

}
