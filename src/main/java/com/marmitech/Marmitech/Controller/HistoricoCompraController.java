package com.marmitech.Marmitech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marmitech.Marmitech.Entity.HistoricoCompra;
import com.marmitech.Marmitech.Service.HistoricoCompraService;

@RestController
@RequestMapping("/historicoCompra")
public class HistoricoCompraController {

    @Autowired
    private HistoricoCompraService historicoCompraService;

    @RequestMapping("/save")
    public ResponseEntity<HistoricoCompra> save(@RequestBody HistoricoCompra historicoCompra){
        try {
            return new ResponseEntity<>(historicoCompraService.save(historicoCompra), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/update/{historicoCompraId}")
    public ResponseEntity<HistoricoCompra> update(@RequestBody HistoricoCompra historicoCompra, @PathVariable int historicoCompraId){
        try {
            return new ResponseEntity<>(historicoCompraService.update(historicoCompra, historicoCompraId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    
    @RequestMapping("/delete/{historicoCompraId}")
    public ResponseEntity<String> delete(@PathVariable int historicoCompraId){
        try {
            return new ResponseEntity<>(historicoCompraService.delete(historicoCompraId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar historico de compra!", HttpStatus.BAD_REQUEST);
        }
    }
        
    @RequestMapping("/findById/{historicoCompraId}")
    public ResponseEntity<HistoricoCompra> findById(@PathVariable int historicoCompraId){
        try {
            return new ResponseEntity<>(historicoCompraService.findById(historicoCompraId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

        
    @RequestMapping("/findAll")
    public ResponseEntity<List<HistoricoCompra>> findAll(){
        try {
            return new ResponseEntity<>(historicoCompraService.findAll(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
