package ch04;

import java.util.List;

public record ProvinceDTO(
        String name,
        List<ProducerDTO> producers,
        int demand,
        int price
) {
}
