package com.marmitech.Marmitech.Controller;

import com.marmitech.Marmitech.Entity.Cliente;
import com.marmitech.Marmitech.Services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping("/save")
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        try {
            var result = clienteService.save( cliente );
            return new ResponseEntity<>( result, HttpStatus.CREATED );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.CREATED );
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Cliente>> findAll() {
        try {
            var result = clienteService.findAll();
            return new ResponseEntity<>( result, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.CREATED );
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
        try {
            var result = clienteService.findById( id );
            return new ResponseEntity<>( result, HttpStatus.FOUND );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.CREATED );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody Cliente cliente) {
        try {
            var result = clienteService.findById( id );
            return new ResponseEntity<>( result, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            clienteService.delete( id );
            return new ResponseEntity<>( null, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }
}