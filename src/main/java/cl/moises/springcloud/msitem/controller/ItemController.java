package cl.moises.springcloud.msitem.controller;

import cl.moises.springcloud.msitem.models.Item;
import cl.moises.springcloud.msitem.services.ItemService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ItemController {

    private final ItemService itemService;

    public ItemController(@Qualifier("itemServiceWebClient") ItemService itemService) {
        this.itemService = itemService;
    }

    // Mostramos request enviado por ms-gateway-server
    @GetMapping("/items")
    public ResponseEntity<List<Item>> findAll(@RequestParam(name= "name", required = false) String name,
                                              @RequestHeader(name = "token-request", required = false) String tokenRequest) {
        System.out.println("Request name: " + name);
        System.out.println("Request token: " + tokenRequest);
        return ResponseEntity.ok().body(itemService.findAll());
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Item> itemOptional = itemService.findById(id);
        if (itemOptional.isPresent()) {
            return ResponseEntity.ok().body(itemOptional.orElseThrow());
        }
        return ResponseEntity.status(404).body(Collections.singletonMap("message", "Item not found"));
    }

}
