package ru.croc.javaschool.finaltask.exceptionhandler;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class ExceptionHandler {
        public void handleException(Exception e, String fileName) {

            try (FileWriter writer = new FileWriter(fileName, true)) {
                writer.write(String.format("Error: %s \nDatetime: %s \n ========================================= \n",
                        e.getMessage(), LocalDateTime.now().toString().substring(0, 19)));
            } catch (IOException ioException) {
                System.out.println("Something very-very bad happened");
            }
        }
}
