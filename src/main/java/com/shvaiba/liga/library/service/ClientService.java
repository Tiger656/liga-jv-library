package com.shvaiba.liga.library.service;

import com.shvaiba.liga.library.persitance.BookRepository;
import com.shvaiba.liga.library.persitance.ClientRepository;
import com.shvaiba.liga.library.persitance.entity.Client;
import com.shvaiba.liga.library.rest.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(BookRepository bookRepository, ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDto addClient(ClientDto clientDto) {
        clientRepository.save(new Client(null, clientDto.getName()));
        return clientDto;
    }

    public ClientDto updateClient(ClientDto clientDto, Long clientId) { //TO DO: replace dto for updateDto and return type
       Client client = clientRepository.getReferenceById(clientId);
       client.setName(clientDto.getName());
       clientRepository.save(client);
       return clientDto;
    }

    public ClientDto getClient(Long clientId) { //TO DO
        Client client = clientRepository.getReferenceById(clientId);
        return new ClientDto(client.getId(), client.getName());
    }

    public List<ClientDto> getClients() {
        return clientRepository.findAll().stream().map(client -> new ClientDto(client.getId(), client.getName())).toList();
    }

    public void deleteClient(Long clientId) {
        clientRepository.deleteById(clientId);
    }
}



