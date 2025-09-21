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
@CrossOrigin("*")
public class HistoricoCompraController {

    @Autowired
    private HistoricoCompraService historicoCompraService;

    @PostMapping("/save")
    public ResponseEntity<HistoricoCompra> save(@RequestBody @Valid HistoricoCompra historicoCompra) {
        return new ResponseEntity<>( historicoCompraService.save( historicoCompra ), HttpStatus.CREATED );
    }

    @PutMapping("/update/{historicoCompraId}")
    public ResponseEntity<HistoricoCompra> update(@RequestBody HistoricoCompra historicoCompra, @PathVariable int historicoCompraId) {
        return new ResponseEntity<>( historicoCompraService.update( historicoCompra, historicoCompraId ), HttpStatus.OK );
    }

    @DeleteMapping("/delete/{historicoCompraId}")
    public ResponseEntity<String> delete(@PathVariable int historicoCompraId) {
        return new ResponseEntity<>( historicoCompraService.delete( historicoCompraId ), HttpStatus.OK );
    }

    @GetMapping("/findById/{historicoCompraId}")
    public ResponseEntity<HistoricoCompra> findById(@PathVariable int historicoCompraId) {
        return new ResponseEntity<>( historicoCompraService.findById( historicoCompraId ), HttpStatus.OK );
    }

    @GetMapping
    public ResponseEntity<List<HistoricoCompra>> findAll() {
        return new ResponseEntity<>( historicoCompraService.findAll(), HttpStatus.OK );
    }
}