package com.irojas.demojwt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "transporte")
public class Transporte {
@Id
    private String placa;
    private String tipo_placa;
    private String marca;
    private String color;
    private String linea;
    private String modelo;
    private String estado;
    private String observaciones;
    private int usuario;
}
