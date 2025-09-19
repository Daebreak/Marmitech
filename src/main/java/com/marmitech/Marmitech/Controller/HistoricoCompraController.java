package com.marmitech.Marmitech.Controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.marmitech.Marmitech.Entity.HistoricoCompra;
import com.marmitech.Marmitech.Services.HistoricoCompraService;

@RestController
@RequestMapping("/historicoCompra")
@CrossOrigin(origins = "*")
public class HistoricoCompraController {

    @Autowired
    private HistoricoCompraService historicoCompraService;

    @PostMapping("/save")
    public ResponseEntity<HistoricoCompra> save(@RequestBody @Valid HistoricoCompra historicoCompra){
        try {
            return new ResponseEntity<>(historicoCompraService.save(historicoCompra), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{historicoCompraId}")
    public ResponseEntity<HistoricoCompra> update(@RequestBody HistoricoCompra historicoCompra, @PathVariable int historicoCompraId){
        try {
            return new ResponseEntity<>(historicoCompraService.update(historicoCompra, historicoCompraId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/delete/{historicoCompraId}")
    public ResponseEntity<String> delete(@PathVariable int historicoCompraId){
        try {
            return new ResponseEntity<>(historicoCompraService.delete(historicoCompraId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar historico de compra!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{historicoCompraId}")
    public ResponseEntity<HistoricoCompra> findById(@PathVariable int historicoCompraId){
        try {
            return new ResponseEntity<>(historicoCompraService.findById(historicoCompraId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity<List<HistoricoCompra>> findAll(){
        try {
            return new ResponseEntity<>(historicoCompraService.findAll(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
