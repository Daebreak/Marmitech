package com.marmitech.Marmitech.Controller;

import com.marmitech.Marmitech.Model.ClienteEntity;
import com.marmitech.Marmitech.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/save")
    public ResponseEntity<ClienteEntity> save(@RequestBody ClienteEntity clienteEntity) {
        try {
            ClienteEntity clienteSalvo = this.clienteService.ClienteService(clienteEntity);
            return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
