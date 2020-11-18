package org.example.UpdateWorker;

import org.example.Worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class UpdateWorkerSalaryClass {
    /**
     * @param worker
     * @param arguments
     * @param isAuto
     * @throws IOException
     */
    public static void UpdateWorkerSalary(Worker worker, List<String> arguments, boolean isAuto, BufferedReader reader) throws IOException {

        System.out.println("Введите  зарплату:");
        Double salary = null;
        while (salary == null || salary <= 0) {
            try {
                if (isAuto)
                    salary = Double.parseDouble(arguments.remove(0));
                else
                    salary = Double.parseDouble(reader.readLine());
                if (salary <= 0) {
                    System.out.println("зарплата меньше нуля. Повторите ввод.");
                }
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println("Данные введены некорректно. Повторите ввод.");
            }
        }
        worker.setSalary(salary);
    }

}
