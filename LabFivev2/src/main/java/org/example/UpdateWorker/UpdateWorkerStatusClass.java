package org.example.UpdateWorker;

import org.example.Status;
import org.example.Worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class UpdateWorkerStatusClass {
    /**
     * @param worker
     * @param arguments
     * @param isAuto
     */
    public static void UpdateWorkerStatus(Worker worker, List<String> arguments, boolean isAuto, BufferedReader reader) {
        System.out.println("Введите  статус (FIRED/HIRED/RECOMMENDED_FOR_PROMOTION/REGULAR):");
        Status status = null;
        while (status == null) {
            try {
                if (isAuto)
                    status = Status.valueOf(arguments.remove(0));
                else
                    status = Status.valueOf(reader.readLine());
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("Данные введены некорректно. Повторите ввод.");
            }
        }
        worker.setStatus(status);
    }
}
