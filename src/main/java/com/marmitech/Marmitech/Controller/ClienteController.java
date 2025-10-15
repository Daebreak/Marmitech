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
@CrossOrigin("*")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @PostMapping("/save" )
    public ResponseEntity<Cliente> save(@RequestBody @Valid Cliente cliente) {

            var result = clienteService.save( cliente );
            return new ResponseEntity<>( result, HttpStatus.CREATED );

    }
    @GetMapping("/findAll")
    public ResponseEntity<List<Cliente>> findAll() {

            var result = clienteService.findAll();
            return new ResponseEntity<>( result, HttpStatus.OK );

    }

    @GetMapping("/findById/{id}" )
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) {

            var result = clienteService.findById( id );
            return new ResponseEntity<>( result, HttpStatus.OK );

    }

    @PutMapping("/update/{id}" )
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @RequestBody Cliente cliente) {

            var result = clienteService.update( id, cliente );
            return new ResponseEntity<>( result, HttpStatus.OK );

    }

    @DeleteMapping("/delete/{id}" )
    public ResponseEntity<Void> delete(@PathVariable Integer id) {

            clienteService.delete( id );
            return new ResponseEntity<>( null, HttpStatus.OK );

    }

    @GetMapping("/findByNome/{nome}" )
    public ResponseEntity<List<Cliente>> findByNome(@PathVariable String nome) {

            var result = clienteService.findByNome( nome );
            return new ResponseEntity<>( result, HttpStatus.OK);


    }

    @GetMapping("/findByCpfCnpj/{cpf_cnpj}" )
    public ResponseEntity<Cliente> findByCpfCnpj(@PathVariable String cpf_cnpj) {

            var result = clienteService.findByCpfCnpj( cpf_cnpj );
            return new ResponseEntity<>( result, HttpStatus.OK );

    }
}
