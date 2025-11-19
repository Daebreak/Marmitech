package com.marmitech.Marmitech.Controller;

import com.marmitech.Marmitech.Entity.Usuario;
import com.marmitech.Marmitech.Services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private  UsuarioService usuarioService;

    @PreAuthorize("hasRole('admin')")
    @PostMapping("/save")
    public ResponseEntity<Usuario> save(@RequestBody @Valid Usuario usuario) {
        var result = usuarioService.save( usuario );
        return new ResponseEntity<>( result, HttpStatus.CREATED );
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("/findAll")
    public ResponseEntity<List<Usuario>> findAll() {
        var result = usuarioService.findAll();
        return new ResponseEntity<>( result, HttpStatus.OK );
    }
    @PreAuthorize("hasRole('admin')")
    @GetMapping("findById/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id) {
        var result = usuarioService.findById( id );
        return new ResponseEntity<>( result, HttpStatus.OK );
    }
    @PreAuthorize("hasRole('admin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Integer id, @RequestBody Usuario usuario) {
        var result = usuarioService.update( id, usuario );
        return new ResponseEntity<>( result, HttpStatus.OK );
    }
    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        usuarioService.delete( id );
        return new ResponseEntity<>( HttpStatus.OK );
    }
    @PreAuthorize("hasRole('admin')")
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestParam String nome, @RequestParam String senha) {
        usuarioService.login( nome );
        //usuarioService.login(senha);
        return new ResponseEntity<>( HttpStatus.ACCEPTED );
    }
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/findByCargo/{cargo}")
    public ResponseEntity<List<Usuario>> findByCargo(@PathVariable String cargo) {
        var result = usuarioService.findByCargo( cargo );
        return new ResponseEntity<>( result, HttpStatus.OK );
    }
    @PreAuthorize("hasRole('admin')")
    @GetMapping("/findByNome/{nome}")
    public ResponseEntity<List<Usuario>> findByNome(@PathVariable String nome) {
        var result = usuarioService.findByNome( nome );
        return new ResponseEntity<>( result, HttpStatus.OK );
    }

}