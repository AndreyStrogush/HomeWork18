package com.homeworks.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class BaseEntity {
    private Long id;
    private String name;
    private LocalDate dateOfCreation;

    public BaseEntity() {
        this.dateOfCreation = LocalDate.now();
    }
}
