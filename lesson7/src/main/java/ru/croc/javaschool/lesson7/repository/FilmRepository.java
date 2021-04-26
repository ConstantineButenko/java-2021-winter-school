package ru.croc.javaschool.lesson7.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.javaschool.lesson7.model.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Класс, содержащий методы работы с записями в базе данных (CRUD-операции) и инициализацию таблицы
 *
 */

public class FilmRepository {
    /** Название таблицы, к которой будут выполняться все запросы */

    private static final String TABLE_NAME = "film";

    /** Объект, обеспечивающий доступ к базе данных */

    private EmbeddedDataSource dataSource;

    public FilmRepository(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /** Получение таблицы из базы данных/ее создание, если не существует
     *
     */

    private void initTable() {
        System.out.println(String.format("Start initializing %s table", TABLE_NAME));
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null,
                                                                    TABLE_NAME.toUpperCase(Locale.ROOT),
                                                                     new String[]{"TABLE"});
            if (resultSet.next()) {
                System.out.println("Table has already been initialized");
            }
            else {
                statement.executeUpdate(
                             "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + "id INTEGER PRIMARY KEY, "
                                + "title VARCHAR(255), "
                                + "genre VARCHAR(255), "
                                + "budget INTEGER, "
                                + "oscar SMALLINT,  "
                                + "foreign_language SMALLINT, "
                                + "release DATE, "
                                + "duration TIME"
                                + ")");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occurred during table initializing: " + e.getMessage());
        }
        finally {
            System.out.println("=========================");
        }
    }

    /** Метод получения объекта класса "Фильм" из данных по уникальному ключу (id)
     *
     * @param id
     * @return Объект класса фильм
     */

    public Film read(int id) {
        try (Connection connection = dataSource.getConnection()) {
           Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s WHERE id = %s",
                   TABLE_NAME, id));
           if (resultSet.next())  {return new Film(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getBoolean(5),
                    resultSet.getBoolean(6),
                    resultSet.getDate(7).toLocalDate(),
                    resultSet.getTime(8).toLocalTime());
           }
            } catch (SQLException e) {
            System.out.println("Something bad happened: " + e.getMessage());
        }
        return new Film();
    }

    /** Метод получения всех объектов класса "Фильм", содержащихся в таблице
     *
     * @return ArrayList со всеми фильмами, содержащимися в таблице
     */

    public List<Film> readAll() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM %s", TABLE_NAME));
            List<Film> filmList = new ArrayList<>();
            while (resultSet.next()) {
                filmList.add(new Film(resultSet.getInt("id"),
                                      resultSet.getString("title"),
                                      resultSet.getString("genre"),
                                      resultSet.getInt("budget"),
                                      resultSet.getBoolean("oscar"),
                                      resultSet.getBoolean("foreign_language"),
                                      resultSet.getDate("release").toLocalDate(),
                                      resultSet.getTime("duration").toLocalTime()));
            }
            return filmList;
        } catch (SQLException e) {
            System.out.println("Something bad happened: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    /** Метод добавления нового фильма в таблицу
     *
     * @param film
     */

    public void create(Film film) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " VALUES( ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

                statement.setInt(1, film.getId());
                statement.setString(2, film.getTitle());
                statement.setString(3, film.getGenre());
                statement.setInt(4, film.getBudget());
                statement.setBoolean(5, film.isGotOscar());
                statement.setBoolean(6, film.isOnForeignLanguage());
                statement.setDate(7, Date.valueOf(film.getReleaseDate()));
                statement.setTime(8, Time.valueOf(film.getDuration()));
                statement.execute();
            }
        catch (SQLException e) {
            System.out.println("Something bad happened: " + e.getMessage());
        }
    }

    /** Метод удаления фильма из таблицы по ключу(id)
     *
     * @param id
     */

    public void delete(int id) {
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Something bad happened: " + e.getMessage());
        }
    }

    /** Метод удаления всех фильмов из таблицы
     *
     */

    public void deleteAll() {
        String sqlQuery = "DELETE FROM " + TABLE_NAME;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Something bad happened: " + e.getMessage());
        }
    }

    /** Метод изменения определенного значения определенного фильма в таблице
     *
     * @param id
     * @param column изменяемый столбец
     * @param value новое значение
     * @param <T>
     */

    public<T> void update(int id, String column, T value ) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET " + column + " = ?"  + " WHERE id = ?";
            try(Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                if (value instanceof Boolean) {
                    statement.setBoolean(1, Boolean.valueOf(value.toString()));
                }
                else {
                        statement.setString(1, value.toString());
                }
                statement.setInt(2, id);
                statement.execute();
        } catch (SQLException e) {
                System.out.println("Something bad happened: " + e.getMessage());
        }
    }

    /** Метод изменения значения в определенном столбце для всех фильмов
     *
     * @param column изменяемый столбец
     * @param value новое значение
     * @param <T>
     */

    public<T> void updateAll(String column, T value ) {
        String sqlQuery = "UPDATE " + TABLE_NAME + " SET " + column + " = ?";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            if (value instanceof Boolean) {
                statement.setBoolean(1, Boolean.valueOf(value.toString()));
            }
            else {
                statement.setString(1, value.toString());
            }
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Something bad happened: " + e.getMessage());
        }
    }

}


