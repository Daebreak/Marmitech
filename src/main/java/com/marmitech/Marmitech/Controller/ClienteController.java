package com.marmitech.Marmitech.Controller;

import com.marmitech.Marmitech.Entity.Cliente;
import com.marmitech.Marmitech.Services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/cliente" )
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping("/save" )
    public ResponseEntity<Cliente> save(@RequestBody @Valid Cliente cliente) {
        try {
            var result = clienteService.save( cliente );
            return new ResponseEntity<>( result, HttpStatus.CREATED );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findAll" )
    public ResponseEntity<List<Cliente>> findAll() {
        try {
            var result = clienteService.findAll();
            return new ResponseEntity<>( result, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findById/{id}" )
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
        try {
            var result = clienteService.findById( id );
            return new ResponseEntity<>( result, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @PutMapping("/update/{id}" )
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody Cliente cliente) {
        try {
            var result = clienteService.update( id, cliente );
            return new ResponseEntity<>( result, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @DeleteMapping("/delete/{id}" )
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            clienteService.delete( id );
            return new ResponseEntity<>( null, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findByNome/{nome}" )
    public ResponseEntity<List<Cliente>> findByNome(@PathVariable String nome) {
        try {
            var result = clienteService.findByNome( nome );
            return new ResponseEntity<>( result, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findByCpfCnpj/{cpf_cnpj}" )
    public ResponseEntity<Cliente> findByCpfCnpj(@PathVariable String cpf_cnpj) {
        try {
            var result = clienteService.findByCpfCnpj( cpf_cnpj );
            return new ResponseEntity<>( result, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }
}
