package cl.moises.springcloud.msitem.client;

import cl.moises.springcloud.msitem.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-productos")
public interface ProductoFeignClient {

    @GetMapping("/api/v1/productos")
    List<ProductoDTO> findAll();

    @GetMapping("/api/v1/producto/{id}")
    ProductoDTO findById(@PathVariable Long id);

}
