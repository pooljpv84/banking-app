package com.example.account.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<DAOCliente, Long> {
    Optional<DAOCliente> findById(Long id);

    void deleteById(Long id);



}
