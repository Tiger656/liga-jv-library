package com.shvaiba.liga.library.service;

import com.shvaiba.liga.library.persitance.BookRepository;
import com.shvaiba.liga.library.persitance.ClientRepository;
import com.shvaiba.liga.library.persitance.entity.Client;
import com.shvaiba.liga.library.rest.dto.ClientDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    ClientRepository clientRepository;

    @Mock
    BookRepository bookRepository;

    @Test
    void addClient_whenValidClientDto_thenClientShouldBeSaved() {
        //given
        ClientService clientService = new ClientService(bookRepository, clientRepository);
        ClientDto clientDto = new ClientDto(1L, "Alex");
        Client client = new Client(null, clientDto.getName());

        //when
        when(clientRepository.save(client)).thenReturn(client);
        ClientDto actual = clientService.addClient(clientDto);
        //then

        verify(clientRepository).save(client);
        assertEquals(clientDto, actual);
    }


    @Test
    void updateClient_whenDataValid_thenUpdateClient() {
        ClientService clientService = new ClientService(bookRepository, clientRepository);
        Long clientId = 34L;
        String newName = "Alex";
        String oldName = "Gena";
        ClientDto clientDto = new ClientDto(null, newName);
        Client oldClient = new Client(clientId, oldName);
        Client newClient = new Client(clientId, newName);

        //when
        when(clientRepository.getReferenceById(clientId)).thenReturn(oldClient);
        when(clientRepository.save(newClient)).thenReturn(newClient);
        ClientDto actual = clientService.updateClient(clientDto, clientId);
        //then
        verify(clientRepository).getReferenceById(clientId);
        verify(clientRepository).save(newClient);
        assertEquals(clientDto, actual);
    }

    @Test
    void getClient_whenIdCorrectAndExist_thenReturnClient() {
        ClientService clientService = new ClientService(bookRepository, clientRepository);
        Long clientId = 34L;
        String name = "Alex";
        Client client = new Client(clientId, name);
        ClientDto expected = new ClientDto(clientId, name);

        //when
        when(clientRepository.getReferenceById(clientId)).thenReturn(client);
        ClientDto actual = clientService.getClient(clientId);

        //then
        verify(clientRepository).getReferenceById(clientId);
        assertEquals(expected, actual);
    }

    @Test
    void getClients_whenClientsExist_ThenReturnAllClients() {
        ClientService clientService = new ClientService(bookRepository, clientRepository);
        Long clientOneId = 34L;
        Long clientTwoId = 35L;
        String nameOne = "Alex";
        String nameTwo = "Gena";

        Client clientOne = new Client(clientOneId, nameOne);
        Client clientTwo = new Client(clientTwoId, nameTwo);
        ClientDto clientOneDto = new ClientDto(clientOneId, nameOne);
        ClientDto clientTwoDto = new ClientDto(clientTwoId, nameTwo);

        List<Client> clients = Arrays.asList(clientOne, clientTwo);
        List<ClientDto> expected = Arrays.asList(clientOneDto, clientTwoDto);

        //when
        when(clientRepository.findAll()).thenReturn(clients);
        List<ClientDto> actual = clientService.getClients();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void deleteClient_whenDataCorrect_thenDeleteClient() {
        ClientService clientService = new ClientService(bookRepository, clientRepository);
        Long clientId = 34L;

        clientService.deleteClient(clientId);

        verify(clientRepository).deleteById(clientId);
    }
}