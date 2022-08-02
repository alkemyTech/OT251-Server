package com.alkemy.ong.dto.response.category;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data

public class CategoryDTO implements Serializable{
    
    private UUID id;

    private String name;
}

/*Descripción

COMO usuario administrador
QUIERO listar las Categorías
PARA poder gestionarlas

Criterios de aceptación:
GET a /categories. Devolverá el listado de categories. Solamente devolverá el campo name de las mismas*/