package ru.croc.javaschool.finaltask.exceptionhandlertest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.croc.javaschool.finaltask.exceptionhandler.ExceptionHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/** Тест класса ExceptionHandler
 *
 */

public class ExceptionHandlerTest {
    @Test
    public void test() throws IOException {
        String error = "Error: ";
        ExceptionHandler handler = new ExceptionHandler();
        try {
            throw new Exception("Тестовая ошибка");
        } catch (Exception e) {
           handler.handleException(e, "exceptionTest.txt");
           error += e.getMessage();
        }
        BufferedReader reader = new BufferedReader(new FileReader("exceptionTest.txt"));
        Assertions.assertEquals(error, reader.readLine().strip());
        reader.close();
    }

}
