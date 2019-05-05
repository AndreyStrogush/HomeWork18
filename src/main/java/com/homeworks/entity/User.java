package com.homeworks.entity;

import com.homeworks.annotations.InjectRandomInt;
import lombok.Data;

import java.time.LocalDate;
import java.time.Period;

@Data
public class User extends BaseEntity {
    private String password;
    private Integer yearOfBerth;

    @InjectRandomInt(min = 1, max = 12)
    private Integer randomMonth;
    @InjectRandomInt(min = 1,max = 31)
    private Integer randomDay;
    private LocalDate berthDate = LocalDate.of(yearOfBerth, randomMonth, randomDay);
    private Period dif = Period.between(berthDate, getDateOfCreation());

    private Integer age  = dif.getYears();
}
