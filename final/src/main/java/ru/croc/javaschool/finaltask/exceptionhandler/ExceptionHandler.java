package ru.croc.javaschool.finaltask.exceptionhandler;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/** Класс-обработчик возникающих исключений
 * 
 */

public class ExceptionHandler {
    /** Метод обработки исключения. Записывает в текстовый файл сообщение об исключении и дату-время его возникновения.
     * 
     * @param e передаваемое исключение
     * @param fileName файл, в который будет осуществляться запись лога
     */
        public void handleException(Exception e, String fileName) {

            try (FileWriter writer = new FileWriter(fileName, true)) {
                writer.write(String.format("Error: %s \nDatetime: %s \n ========================================= \n",
                        e.getMessage(), LocalDateTime.now().toString().substring(0, 19)));
            } catch (IOException ioException) {
                System.out.println("Something very-very bad happened");
            }
        }
}
