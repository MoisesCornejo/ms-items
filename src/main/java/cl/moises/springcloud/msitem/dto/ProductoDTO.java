package cl.moises.springcloud.msitem.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductoDTO {

    private Long id;
    private String nombre;
    private Double precio;
    private LocalDate creado;

}
