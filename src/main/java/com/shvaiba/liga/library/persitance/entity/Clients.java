package com.shvaiba.liga.library.persitance.entity;

import lombok.Data;

import java.util.List;

@Data
public class Clients {
    private List<Client> clients;
    private Long availableId;

    public Clients(List<Client> clients, Long availableId) {
        this.clients = clients;
        this.availableId = availableId;
    }

    public Client addClient(Client client) {
        List<Client> clientsList = getClients();
        clientsList.add(new Client(availableId, client.getName()));
        
        return null;
    }
    
    private Long getAvailableId() {
        return availableId++;
    }
}
