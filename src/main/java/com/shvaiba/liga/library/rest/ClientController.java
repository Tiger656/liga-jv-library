package com.shvaiba.liga.library.rest;

import com.shvaiba.liga.library.rest.dto.BookDto;
import com.shvaiba.liga.library.rest.dto.ClientDto;
import com.shvaiba.liga.library.service.BookService;
import com.shvaiba.liga.library.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/client")
public class ClientController {

    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping(path = "/")
    public ClientDto addClient(ClientDto clientDto) {
        return clientService.addClient(clientDto);
    }

    @PutMapping(path = "/{id}")
    public ClientDto updateClient(@PathVariable("id") Long clientId, ClientDto clientDto) {
        return clientService.updateClient(clientDto, clientId);

    }

    @GetMapping(path = "/{id}")
    public ClientDto getClient(@PathVariable("id") Long clientId) {
        return clientService.getClient(clientId);
    }

    @GetMapping("/")
    public List<ClientDto> getClients() {
        return clientService.getClients();
    }

    @DeleteMapping(path = "/")
    public void deleteClient(@PathVariable("id") Long clientId) {
        clientService.deleteClient(clientId);
    }


}
