package ru.croc.javaschool.finaltask.database.dbprovider;

import org.apache.derby.jdbc.EmbeddedDataSource;
import ru.croc.javaschool.finaltask.exceptionhandler.ExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/** Класс, предоставляющий объект для связи с базой данных
 *
 */

public class DataSourceProvider {
    /** Источник данных, объект, через который происходит обращение к БД */

    private EmbeddedDataSource dataSource;

    /** Коллекция "ключ-значение", содержащая настройки базы данных */

    private Map<String, String> properties = new HashMap<>();

    public DataSourceProvider() throws IOException {
        loadProperties();
    }

    /** Метод получения параметров конфигурации базы данных */

    private void loadProperties() throws IOException {
        Properties properties = new Properties();
        try {
            properties.load(
                    Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
            for (Map.Entry<Object, Object> entry : properties.entrySet()) {
                this.properties.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            ExceptionHandler handler = new ExceptionHandler();
            handler.handleException(e, "errorlog.txt");
            System.out.println("An error occured during loading properties");
        }
    }

    /** Метод получения объекта для связи с базой данных, инициализирует один раз (в случае, если не инициализирован),
     * в дальнейшем возвращает уже созданный объект, что увеличивает быстродействие
     * @return dataSource
     */

    public EmbeddedDataSource getDataSource() {
        if (dataSource == null) {
            dataSource = new EmbeddedDataSource();
            dataSource.setDatabaseName(properties.get("database.name"));
            String username = properties.get("database.login");
            String password = properties.get("database.password");
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                dataSource.setUser(username);
                dataSource.setPassword(password);
            }
            dataSource.setCreateDatabase("create");
        }
        return dataSource;
    }
}
