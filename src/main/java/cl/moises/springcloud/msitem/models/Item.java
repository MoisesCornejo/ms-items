package cl.moises.springcloud.msitem.models;

import cl.moises.springcloud.msitem.dto.ProductoDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

    private ProductoDTO productoDTO;
    private Integer cantidad;

    public Double getTotal() {
        return productoDTO.getPrecio() * cantidad;
    }

}
