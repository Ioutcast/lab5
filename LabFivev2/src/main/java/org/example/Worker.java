package org.example;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Класс рабочего, который записывается в коллекцию.
 */
@ToString
@EqualsAndHashCode
public class Worker implements Comparable<Worker> {
    private static int globalID = 0;
    @Getter
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    @Getter
    private String name; //Поле не может быть null, Строка не может быть пустой
    @Getter
    private Coordinates coordinates; //Поле не может быть null
    @Getter
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    @Getter
    private Double salary; //Поле не может быть null, Значение поля должно быть больше 0
    @Getter
    private LocalDate startDate; //Поле не может быть null
    @Getter
    private LocalDateTime endDate; //Поле может быть null
    @Getter
    private Status status; //Поле не может быть null
    @Getter

    private Organization organization; //Поле может быть null

    /**
     * Сесстер без параметров для дальнейшего заполнения
     */
    public Worker() {
        globalID++;
        this.id = globalID;
        this.creationDate = LocalDateTime.now();
    }

    /**
     * Сеттер с параметрами
     *
     * @param id
     * @param name
     * @param coordinates
     * @param salary
     * @param startDate
     * @param endDate
     * @param status
     * @param organization
     */
    public Worker(int id, String name, Coordinates coordinates, Double salary, LocalDate startDate, LocalDateTime endDate, Status status, Organization organization) {
        globalID++;
        this.id = globalID;
        this.name = name;
        this.coordinates = coordinates;
        assert salary > 0;
        this.salary = salary;
        this.startDate = startDate;
        this.creationDate = LocalDateTime.now();
        this.endDate = endDate;
        this.status = status;
        this.organization = organization;
    }


    /**
     * сеттер имени
     *
     * @param name
     */
    public void setName(@NonNull String name) {
        if (name.trim().equals("")) {
            throw new NullPointerException("name is marked NotNull but is null");
        }
        this.name = name;
    }

    /**
     * сеттер координат
     *
     * @param coordinates
     */
    public void setCoordinates(@NonNull Coordinates coordinates) {
        this.coordinates = coordinates;

    }

    /**
     * сеттер зарплаты
     *
     * @param salary
     */
    public void setSalary(@NonNull Double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("salary<0!!?");
        }
        this.salary = salary;
    }

    /**
     * сеттер даты начала
     *
     * @param startDate
     */
    public void setStartDate(@NonNull LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * сеттер статуса
     *
     * @param status
     */
    public void setStatus(@NonNull Status status) {
        this.status = status;
    }

    /**
     * сеттер организации
     *
     * @param organization
     */
    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    /**
     * сравнение
     *
     * @param o
     * @return result
     */
    @Override
    public int compareTo(Worker o) {
        int result = this.name.compareTo(o.name);
        result = getResult(result, this.coordinates.compareTo(o.coordinates));
        result = getResult(result, this.salary.compareTo(o.salary));
        result = getResult(result, this.organization.compareTo(o.organization));
        result = getResult(result, this.status.compareTo(o.status));
        result = getResult(result, this.creationDate.compareTo(o.creationDate));
        result = getResult(result, this.startDate.compareTo(o.startDate));
        result = getResult(result, this.endDate.compareTo(o.endDate));
        return result;
    }

    /**
     * результат сравнения
     *
     * @param result
     * @param i
     * @return
     */
    private int getResult(int result, int i) {
        if (result == 0) {
            result = i;
        }
        return result;
    }

    /**
     * сеттер даты конца, localDatetime
     *
     * @param endDate
     */
    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * сеттер даты конца , стринг
     *
     * @param endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = null;
    }

}

/**
 * класс локации - элемент адреса
 */
@ToString
class Location extends Worker {
    @Getter
    private int y;
    @Getter
    private Double x; //Поле не может быть null
    @Getter
    private String name; //Поле может быть null

    /**
     * конструктор
     *
     * @param x
     * @param y
     * @param name
     */
    public Location(Double x, int y, String name) {
        assert x > 0;
        assert y > 0;
        setX(x);
        setY(y);
        setName(name);
    }

    /**
     * сеттер x
     *
     * @param x
     */
    public void setX(@NonNull Double x) {
        this.x = x;
    }

    /**
     * сеттер y
     *
     * @param y
     */
    public void setY(int y) {

        this.y = y;
    }

    /**
     * сеттер имени локации
     *
     * @param name
     */
    public void setName(@NonNull String name) {
        if (name.trim().equals("")) {
            throw new NullPointerException("zipCode is marked NotNull but is null");
        }
        this.name = name;
    }

}

/**
 * класс адреса - элемент рабочего
 */
@ToString
class Address implements Comparable<Address> {
    @Getter
    private String street; //Поле не может быть null
    @Getter
    private String zipCode; //Поле может быть null
    @Getter
    private Location town; //Поле не может быть null

    /**
     * конструктор
     *
     * @param zipCode
     * @param street
     * @param town
     */
    public Address(String zipCode, String street, Location town) {
        setZipCode(zipCode);
        setStreet(street);
        setTown(town);
    }

    /**
     * сеттер улицы
     *
     * @param street
     */
    public void setStreet(@NonNull String street) {
        if (street.trim().equals("")) {
            throw new NullPointerException("street is marked NotNull but is null");
        }
        this.street = street;
    }

    /**
     * сеттер zipCode
     *
     * @param zipCode
     */
    public void setZipCode(@NonNull String zipCode) {
        if (zipCode.trim().equals("")) {
            throw new NullPointerException("zipCode is marked NotNull but is null");
        }
        this.zipCode = zipCode;
    }

    /**
     * сеттер города
     *
     * @param town
     */
    public void setTown(@NonNull Location town) {
        this.town = town;
    }

    /**
     * сравнение
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Address o) {
        if (this.zipCode.length() > o.getZipCode().length()) {
            return 1;
        } else if (this.zipCode.length() < o.getZipCode().length()) {
            return -1;
        } else return 0;
    }
}


