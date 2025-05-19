package org.iplacex.proyectos.discografia.discos;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "discos")

public class Disco {

     @Id
    public String idArtista;
    public String nombre;
    public int aniofundacion; 
    public List<String> canciones;

    

}
