package com.shvaiba.liga.library.persitance.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class Library {

   Books books;
   Clients clients;
}
