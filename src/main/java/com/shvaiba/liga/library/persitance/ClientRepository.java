package com.shvaiba.liga.library.persitance;

import com.shvaiba.liga.library.persitance.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
