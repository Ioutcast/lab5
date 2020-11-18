package org.example.UpdateWorker;

import org.example.Worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class UpdateWorkerEndDateClass {
    /**
     * @param worker
     * @param arguments
     * @param isAuto
     */
    public static void UpdateWorkerEndDate(Worker worker, List<String> arguments, boolean isAuto, BufferedReader reader) {
        System.out.println("Ввод endDate в формате yyyy-MM-dd HH:mm (1986-04-08 12:30 например)");
        LocalDateTime localDateTime = null;
        String endDate = "";
        String nullEndDate = null;
        while (endDate == null || endDate.equals("")) {
            try {
                if (isAuto)
                    endDate = arguments.remove(0);
                else
                    endDate = reader.readLine();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                localDateTime = LocalDateTime.parse(endDate, formatter);
            } catch (NumberFormatException | DateTimeParseException | NullPointerException | IOException e) {
                if (!endDate.trim().equals("")) {
                    endDate = null;
                    System.out.println("Данные введены некорректно. Повторите ввод.");
                } else {
                    nullEndDate = "";
                    break;
                }
            }
        }
        if (!(nullEndDate == null)) {
            worker.setEndDate(nullEndDate);
        } else
            worker.setEndDate(localDateTime);
    }
}
