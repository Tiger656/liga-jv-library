package com.shvaiba.liga.library.persitance.entity;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Book {

    private Long id;
    private String name;
    private Long clientId;
}
