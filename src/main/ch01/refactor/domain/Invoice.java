package ch01.refactor.domain;

import java.util.List;

public record Invoice(
        String customer,
        List<Performance> performances
){

}
