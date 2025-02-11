package com.example.account.DAO;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<DAOPersona, Long> {

    Optional<DAOPersona> findByIdentificacion(String identificacion);
}
