package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * Класс организации - поле внутри рабочего
 */
@ToString
@EqualsAndHashCode
public class Organization {
    @Getter
    private Long employeesCount; //Значение поля должно быть больше 0
    @Getter
    private OrganizationType type; //Поле может быть null
    @Getter
    private Address officialAddress; //Поле не может быть null

    /**
     * @param employeesCount
     * @param type
     * @param officialAddress
     */
    public Organization(long employeesCount, OrganizationType type, Address officialAddress) {
        setEmployeesCount(employeesCount);
        setType(type);
        setOfficialAddress(officialAddress);
    }

    /**
     * @param employeesCount
     * @param officialAddress
     */
    public Organization(long employeesCount, Address officialAddress) {
        setEmployeesCount(employeesCount);
        setType();
        setOfficialAddress(officialAddress);
    }


    /**
     * @param employeesCount
     */
    public void setEmployeesCount(long employeesCount) {
        if (employeesCount > 0) {
            this.employeesCount = employeesCount;
        } else throw new IllegalArgumentException("Значение не должно быть меньше нуля");
    }

    /**
     * @param type
     */
    public void setType(OrganizationType type) {
        this.type = type;
    }


    public void setType() {
        this.type = null;
    }

    /**
     * @param officialAddress
     */
    public void setOfficialAddress(@NonNull Address officialAddress) {
        this.officialAddress = officialAddress;
    }


    public int compareTo(Organization o) {
        int result = this.employeesCount.compareTo(o.employeesCount);
        if (result == 0) {
            result = this.officialAddress.compareTo(o.getOfficialAddress());
        }
        if (result == 0) {
            result = this.type.compareTo(o.type);
        }
        return result;
    }


}
