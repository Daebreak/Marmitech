package com.marmitech.Marmitech.Controller;

import com.marmitech.Marmitech.Entity.Categoria;
import com.marmitech.Marmitech.Services.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoriaController {
    private final CategoriaService categoriaService;


    @PreAuthorize("hasRole('admin') or hasRole('Caixa')")
    @PostMapping("/save" )
    public ResponseEntity<Object> save(@RequestBody @Valid Categoria categoria) {
            var result = categoriaService.save( categoria );
            return new ResponseEntity<>( result, HttpStatus.CREATED );
    }
    @PreAuthorize("hasRole('admin') or hasRole('Caixa')")
    @GetMapping("/findAll")
    public ResponseEntity<List<Categoria>> findAll(){
            var result = categoriaService.findAll();
            return new ResponseEntity<>( result, HttpStatus.OK );

    }
    @PreAuthorize("hasRole('admin') or hasRole('Caixa')")
    @DeleteMapping("/delete/{id}" )
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
            categoriaService.delete( id );
            return new ResponseEntity<>( null, HttpStatus.OK );

    }
    @PreAuthorize("hasRole('admin') or hasRole('Caixa')")
    @PutMapping("/update/{id}" )
    public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody Categoria categoria) {
            var result = categoriaService.update(id, categoria);
            return new ResponseEntity<>( result, HttpStatus.OK );
    }
}