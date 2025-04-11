package com.irojas.demojwt.repository;

import com.irojas.demojwt.model.Transportista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportistaRepository extends JpaRepository<Transportista, Long> {
}
