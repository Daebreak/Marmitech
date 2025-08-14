package com.marmitech.Marmitech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marmitech.Marmitech.Entity.HistoricoCompra;
import com.marmitech.Marmitech.Services.HistoricoCompraService;

@RestController
@RequestMapping("/historicoCompra")
public class HistoricoCompraController {

    @Autowired
    private HistoricoCompraService historicoCompraService;

    @PostMapping("/save")
    public ResponseEntity<HistoricoCompra> save(@RequestBody HistoricoCompra historicoCompra){
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

        
    @GetMapping("/findAll")
    public ResponseEntity<List<HistoricoCompra>> findAll(){
        try {
            return new ResponseEntity<>(historicoCompraService.findAll(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
