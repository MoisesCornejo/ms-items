package cl.moises.springcloud.msitem.services;

import cl.moises.springcloud.msitem.client.ProductoFeignClient;
import cl.moises.springcloud.msitem.dto.ProductoDTO;
import cl.moises.springcloud.msitem.models.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ItemServiceFeign implements ItemService {

    private final ProductoFeignClient productoFeignClient;

    public ItemServiceFeign(ProductoFeignClient productoFeignClient) {
        this.productoFeignClient = productoFeignClient;
    }

    @Override
    public List<Item> findAll() {
        return productoFeignClient.findAll()
                .stream()
                .map(producto -> {
                    return new Item(producto, new Random().nextInt(10) + 1);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Item> findById(Long id) {
        ProductoDTO productoDTO = productoFeignClient.findById(id);
        if (productoDTO == null) {
            return Optional.empty();
        }
        return Optional.of(new Item(productoDTO, new Random().nextInt(10) + 1));
    }

}
