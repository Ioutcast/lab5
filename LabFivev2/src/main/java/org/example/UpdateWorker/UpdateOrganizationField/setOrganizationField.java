package org.example.UpdateWorker.UpdateOrganizationField;

import org.example.OrganizationType;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class setOrganizationField {
    public static String setLocationName(List<String> arguments, boolean isAuto, BufferedReader reader, String locationName) throws IOException {
        while (locationName == "") {
            try {
                if (isAuto)
                    locationName = arguments.remove(0);
                else
                    locationName = reader.readLine();
                if (locationName == "") {
                    throw new NullPointerException("locationName==null");
                }
            } catch (NullPointerException e) {
                locationName = "";
                System.out.println(e);
                System.out.println("Данные введены некорректно. Повторите ввод locationName.");
            }
        }
        return locationName;
    }

    /**
     * @param arguments
     * @param isAuto
     * @param reader
     * @param locationY
     * @return
     * @throws IOException
     */
    public static Integer setLocationY(List<String> arguments, boolean isAuto, BufferedReader reader, Integer locationY) throws IOException {

        while (locationY == null) {
            try {
                if (isAuto)
                    locationY = Integer.parseInt(arguments.remove(0));
                else
                    locationY = Integer.parseInt(reader.readLine());
                if (locationY == null) {
                    throw new NullPointerException("locationY==null");
                }
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println(e);
                System.out.println("Данные введены некорректно. Повторите ввод locationY.");
            }
        }
        return locationY;
    }

    /**
     * @param arguments
     * @param isAuto
     * @param reader
     * @param locationX
     * @return
     */
    public static Double setLocationX(List<String> arguments, boolean isAuto, BufferedReader reader, Double locationX) {
        while (locationX == null) {
            try {
                if (isAuto)
                    locationX = Double.parseDouble(arguments.remove(0));
                else
                    locationX = Double.parseDouble(reader.readLine());
                if (locationX == null) {
                    throw new NullPointerException("locationX==null");
                }
            } catch (NumberFormatException | NullPointerException | IOException e) {
                System.out.println(e);
                System.out.println("Данные введены некорректно. Повторите ввод locationX.");
            }
        }
        return locationX;
    }

    /**
     * @param arguments
     * @param isAuto
     * @param reader
     * @param zipCode
     * @return
     */
    public static String setZipCode(List<String> arguments, boolean isAuto, BufferedReader reader, String zipCode) {
        while (zipCode == "") {
            try {
                if (isAuto)
                    zipCode = arguments.remove(0);
                else
                    zipCode = reader.readLine();
                if (zipCode == "") {
                    throw new NullPointerException("zipCode==null");
                }
            } catch (NullPointerException | IOException e) {
                System.out.println(e);
                System.out.println("Данные введены некорректно. Повторите ввод zipCode.");
            }
        }
        return zipCode;
    }

    /**
     * @param arguments
     * @param isAuto
     * @param reader
     * @param street
     * @return
     */
    public static String setStreet(List<String> arguments, boolean isAuto, BufferedReader reader, String street) {
        while (street == "") {
            try {
                if (isAuto)
                    street = arguments.remove(0);
                else
                    street = reader.readLine();
                if (street == "") {
                    throw new NullPointerException("street==null");
                }
            } catch (NullPointerException | IOException e) {
                System.out.println(e);
                System.out.println("Повторите ввод street.");
            }
        }
        return street;
    }

    /**
     * @param arguments
     * @param isAuto
     * @param reader
     * @param type
     * @return
     */
    public static OrganizationType setOrganizationType(List<String> arguments, boolean isAuto, BufferedReader reader, OrganizationType type) {
        while (type == null) {
            try {
                if (isAuto)
                    type = OrganizationType.valueOf(arguments.remove(0));
                else
                    type = OrganizationType.valueOf(reader.readLine());
                if (type == null) {
                    throw new NullPointerException("type==null");
                }
            } catch (IllegalArgumentException | NullPointerException | IOException e) {
                System.out.println(e);
                System.out.println("Данные введены некорректно. Повторите ввод type.");
            }
        }
        return type;
    }

    /**
     * @param arguments
     * @param isAuto
     * @param reader
     * @param employeesCount
     * @return
     */
    public static Long setEmployeesCount(List<String> arguments, boolean isAuto, BufferedReader reader, Long employeesCount) {
        while (employeesCount == null || employeesCount <= 0) {
            try {
                if (isAuto)
                    employeesCount = Long.parseLong(arguments.remove(0));
                else
                    employeesCount = Long.parseLong(reader.readLine());
                if (employeesCount <= 0)
                    throw new ArithmeticException("Данные введены некорректно. <0");
            } catch (NumberFormatException | ArithmeticException | IOException e) {
                System.out.println(e);
                System.out.println("Повторите ввод employeesCount.");
            }
        }
        return employeesCount;
    }
}
