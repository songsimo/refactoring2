package ch01.before.entity;

import java.util.List;

public record Invoice(
        String customer,
        List<Performance> performances
){

}
