package cl.moises.springcloud.msitem.services;

import cl.moises.springcloud.msitem.models.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    List<Item> findAll();
    Optional<Item> findById(Long id);

}
