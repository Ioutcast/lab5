package org.example.UpdateWorker;

import org.example.Coordinates;
import org.example.Worker;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.example.Coordinates.getXMax;
import static org.example.Coordinates.getYMin;

public class UpdateWorkerCoordinatesClass {
    /**
     * @param worker
     * @param arguments
     * @param isAuto
     * @throws IOException
     */
    public static void UpdateWorkerCoordinates(Worker worker, List<String> arguments, boolean isAuto, BufferedReader reader) throws IOException {
        System.out.println("Введите  координаты, по одной координате в строке:");
        int x = 235;
        int y = -245;
        while (x > getXMax() || y <= getYMin()) {
            try {
                if (isAuto) {
                    x = Integer.parseInt(arguments.remove(0));
                    y = Integer.parseInt(arguments.remove(0));
                } else {
                    System.out.println("Введите  X:");
                    x = Integer.parseInt(reader.readLine());
                    System.out.println("Введите  Y:");
                    y = Integer.parseInt(reader.readLine());
                }

            } catch (NumberFormatException e) {
                System.out.println("Данные введены некорректно. Повторите ввод.");
            }

        }
        worker.setCoordinates(new Coordinates(x, y));
    }

}
