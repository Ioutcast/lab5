package org.example.UpdateWorker;

import org.example.Worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class UpdateWorkerStartDateClass {
    /**
     * @param worker
     * @param arguments
     * @param isAuto
     */
    public static void UpdateWorkerStartDate(Worker worker, List<String> arguments, boolean isAuto, BufferedReader reader) {

        System.out.println("Ввод startDate в формате d/MM/yyyy(08/12/2020 например): ");
        LocalDate localDate = null;
        String startDate1 = null;
        while (startDate1 == null) {
            try {
                if (isAuto)
                    startDate1 = arguments.remove(0);
                else
                    startDate1 = reader.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
                localDate = LocalDate.parse(startDate1, formatter);
            } catch (NumberFormatException | DateTimeParseException | NullPointerException | IOException e) {
                System.out.println("Данные введены некорректно. Повторите ввод.");
                startDate1 = null;
            }
        }
        worker.setStartDate(localDate);
    }

}
