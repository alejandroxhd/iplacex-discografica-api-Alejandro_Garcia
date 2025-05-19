package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import java.util.Optional;

import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")

public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepository;

    @Autowired
    private IArtistaRepository artistaRepository;

    @PostMapping(value= "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disco> HandlePostDiscoRequest(@RequestBody Disco disco){
        if(!artistaRepository.existsById(disco.idArtista)){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        Disco temp = discoRepository.insert(disco);
        return new ResponseEntity<>(temp,null,HttpStatus.CREATED);
    }

    @GetMapping(value="/disco", produces = MediaType.APPLICATION_JSON_VALUE)
        public ResponseEntity<List<Disco>> HandleGetDiscosRequest(){
        List<Disco> disco = discoRepository.findAll();
        return new ResponseEntity<>(disco,null, HttpStatus.OK);
    }

    @GetMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Disco> HandleGetDiscoRequest(@PathVariable("id") String id){
        Optional<Disco> temp = discoRepository.findById(id);

        if(!temp.isPresent()){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(temp.get(),null, HttpStatus.OK);
    }

    @GetMapping(value = "/artistas/{id}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> handleGetDiscosByArtistaRequest(@PathVariable("id") String idArtista) {
        List<Disco> temp = discoRepository.findDiscoByIdArista(idArtista);

        if (temp.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(temp, HttpStatus.OK);
    }


}
