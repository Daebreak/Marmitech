package com.marmitech.Marmitech.Controller;
import com.marmitech.Marmitech.Entity.Categoria;
import com.marmitech.Marmitech.Services.CategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CategoriaController {
    private final CategoriaService categoriaService;


    @PostMapping("/save" )
    public ResponseEntity<Object> save(@RequestBody @Valid Categoria categoria) {
        try {
            var result = categoriaService.save( categoria );
            return new ResponseEntity<>( result, HttpStatus.CREATED );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }
    @GetMapping("/list")
    public ResponseEntity<List<Categoria>> findAll(){

        try {
            var result = categoriaService.findAll();
            return new ResponseEntity<>( result, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }
    @DeleteMapping("/delete/{id}" )
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            categoriaService.delete( id );
            return new ResponseEntity<>( null, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }
    @PutMapping("/update/{id}" )
    public ResponseEntity<Categoria> update(@PathVariable Integer id, @RequestBody Categoria categoria) {
        try {
            var result = categoriaService.update(id, categoria);
            return new ResponseEntity<>( result, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }
    }

