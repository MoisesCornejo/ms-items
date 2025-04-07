package cl.moises.springcloud.msitem.services;

import cl.moises.springcloud.msitem.dto.ProductoDTO;
import cl.moises.springcloud.msitem.models.Item;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.*;

//@Primary
@Service
public class ItemServiceWebClient implements ItemService {

    private final WebClient.Builder clientBuilder;

    public ItemServiceWebClient(WebClient.Builder clientBuilder) {
        this.clientBuilder = clientBuilder;
    }

    @Override
    public List<Item> findAll() {
        // Construye un cliente WebClient y realiza una solicitud GET a la URI "http://ms-productos"
        return this.clientBuilder.build()
                .get()
                .uri("/productos")
                // Establece el tipo de contenido aceptado como JSON
                .accept(MediaType.APPLICATION_JSON)
                // Recupera la respuesta y la convierte en un flujo de objetos Item
                .retrieve()
                .bodyToFlux(Item.class)
                // Recoge todos los elementos del flujo en una lista
                .collectList()
                // Bloquea hasta que se complete la operación y devuelve la lista de Items
                .block();
    }

    @Override
    public Optional<Item> findById(Long id) {
        // Crea un mapa de parámetros para la URI
        Map<String, Long> params = new HashMap<>();
        params.put("id", id);

        try {
            // Construye un cliente WebClient y realiza una solicitud GET a la URI "http://ms-productos"
            return Optional.ofNullable(clientBuilder.build()
                    .get()
                    // Inserta el parámetro "id" en la URI
                    .uri("/producto/{id}", params)
                    // Establece el tipo de contenido aceptado como JSON
                    .accept(MediaType.APPLICATION_JSON)
                    // Recupera la respuesta y la convierte en un objeto
                    .retrieve()
                    .bodyToMono(ProductoDTO.class)
                    .map(producto -> {
                        return new Item(producto, new Random().nextInt(10) + 1);
                    })
                    // Bloquea hasta que se complete la operación y devuelve el Item
                    .block());
        } catch (WebClientResponseException e) {
            return Optional.empty();
        }

    }

}
