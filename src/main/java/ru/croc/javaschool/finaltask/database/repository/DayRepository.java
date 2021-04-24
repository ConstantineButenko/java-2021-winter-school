package ru.croc.javaschool.finaltask.database.repository;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.javaschool.finaltask.model.Day;
import ru.croc.javaschool.finaltask.model.Days;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** Класс, содержащий методы работы с записями в базе данных (CRUD-операции) и инициализацию таблицы
 *
 */

public class DayRepository {

        /**
         * Название таблицы, к которой будут выполняться все запросы
         */

        private static final String TABLE_NAME = "date";

        /**
         * Объект, обеспечивающий доступ к базе данных
         */

        private EmbeddedDataSource dataSource;

        public DayRepository(EmbeddedDataSource dataSource) {
            this.dataSource = dataSource;
            initTable();
        }

        /**
         * Получение таблицы из базы данных/ее создание, если не существует
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
                } else {
                    statement.executeUpdate(
                            "CREATE TABLE "
                                    + TABLE_NAME
                                    + " ("
                                    + "id INTEGER, "
                                    + "date DATE PRIMARY KEY , "
                                    + "temperature FLOAT, "
                                    + "pressure INTEGER"
                                    + ")");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error occurred during table initializing: " + e.getMessage());
            } finally {
                System.out.println("================================================================================");
            }
        }

    /** Добавление записи в базу данных
     *
     * @param day передаваемый объект класса "День"
     */

    public void create(Day day) {
            String sqlQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?)";
            try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setInt(1, day.getId());
                statement.setDate(2, Date.valueOf(day.getDate()));
                statement.setFloat(3, day.getTemperature());
                statement.setInt(4, day.getPressure());
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    /** Получение списка дней в заданном интервале в формате объекта "Дни", пригодного для конвертирования в xml
     *
     * @param from Объект LocalDate (нижняя граница диапазона)
     * @param to Объект LocalDate (верхняя граница диапазона)
     * @return Объект класса Days
     */

    public Days get(LocalDate from, LocalDate to) {
            String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE date BETWEEN ? AND ?";
            try (Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setDate(1, Date.valueOf(from));
                statement.setDate(2, Date.valueOf(to));
                ResultSet resultSet = statement.executeQuery();
                List<Day> days = new ArrayList<>();
                while (resultSet.next()) {
                    days.add(new Day(resultSet.getInt("id"),
                            resultSet.getDate("date").toLocalDate(),
                            resultSet.getFloat("temperature"),
                            resultSet.getInt("pressure")));
                }
                return new Days(days);
            } catch (SQLException e) {
                System.out.println("Something bad happened: " + e.getMessage());
            }
            return new Days();
        }
    }
