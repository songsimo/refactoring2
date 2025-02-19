package ch04;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class ProvinceTest {

    private ProvinceDTO provinceDTO;

    @BeforeEach
    void setUp() {
        List<ProducerDTO> producerDTOList = List.of(
                new ProducerDTO("Byzantinum", 10, 9),
                new ProducerDTO("Attalia", 12, 10),
                new ProducerDTO("Sinope", 10, 6)
        );
        provinceDTO = new ProvinceDTO("Asia", producerDTOList, 30, 20);
    }

    @Test
    void shortFall() {
        Province province = new Province(provinceDTO);
        assertThat(province.shortFall()).isEqualTo(5);
    }

    @Test
    void profit() {
        Province province = new Province(provinceDTO);
        assertThat(province.profit()).isEqualTo(230);
    }

    @Test
    void changeProduction() {
        Province province = new Province(provinceDTO);
        province.setProduction(0, 20);

        assertThat(province.shortFall()).isEqualTo(-6);
        assertThat(province.profit()).isEqualTo(292);
    }

    @Test
    void noProducers() {
        Province province = new Province(new ProvinceDTO("No producers", new ArrayList<>(), 30, 20));

        assertAll(
                () -> assertThat(province.shortFall()).isEqualTo(30),
                () -> assertThat(province.profit()).isEqualTo(0)
        );
    }

    @Test
    void zeroDemand() {
        Province province = new Province(provinceDTO);
        province.setDemand(0);

        assertAll(
                () -> assertThat(province.shortFall()).isEqualTo(-25),
                () -> assertThat(province.profit()).isEqualTo(0)
        );
    }

    @Test
    void negativeDemand() {
        Province province = new Province(provinceDTO);
        province.setDemand(-1);

        assertAll(
                () -> assertThat(province.shortFall()).isEqualTo(-26),
                () -> assertThat(province.profit()).isEqualTo(-10)
        );
    }
}