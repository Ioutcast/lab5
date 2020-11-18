package org.example.UpdateWorker;

import org.example.Worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class UpdateWorkerNameClass {

    /**
     * @param worker
     * @param arguments
     * @param isAuto
     * @throws IOException
     */
    public static void UpdateWorkerName(Worker worker, List<String> arguments, boolean isAuto, BufferedReader reader) throws IOException {
        String name = null;
        do {
            try {
                System.out.println("Введите  имя:");
                if (isAuto)
                    name = arguments.remove(0);
                else
                    name = reader.readLine();
                if (name == null || name.trim().equals("")) {
                    throw new NullPointerException("Ошибка ввода имени");
                } else if (isNumeric(name))
                    throw new IllegalArgumentException("Ошибка ввода имени");
                else break;
            } catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(e);
            }
        } while (true);


        worker.setName(name);
    }

    /**
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        for (char c : str.toCharArray())
            if (Character.isDigit(c)) {
                return true;

            }
        return false;
    }
}
