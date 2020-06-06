package br.com.bertino.backend.controller;

import br.com.bertino.backend.model.Usuarios;
import br.com.bertino.backend.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    UsuariosRepository usuariosRepository;

    public UsuariosController(UsuariosRepository usuariosRepository){
        this.usuariosRepository = usuariosRepository;
    }

    @GetMapping(path = "/findAll")
    public List findAll() {
        return usuariosRepository.findAll();
    }

    @RequestMapping(value = "/create", method =  RequestMethod.POST)
    public Usuarios create(@Valid @RequestBody Usuarios usuarios)
    {
        return usuariosRepository.save(usuarios);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Usuarios usuarios) {
        return usuariosRepository.findById(id)
                .map(record -> {
                    record.setUsuario(usuarios.getUsuario());
                    record.setSenha(usuarios.getSenha());
                    Usuarios updated = usuariosRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "{/id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        return usuariosRepository.findById(id)
                .map(record -> {
                    usuariosRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
