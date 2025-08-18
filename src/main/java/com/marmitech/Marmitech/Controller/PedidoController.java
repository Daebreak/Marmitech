package com.marmitech.Marmitech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.marmitech.Marmitech.Entity.Pedido;
import com.marmitech.Marmitech.Services.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/save")
    public ResponseEntity<Pedido> save(@RequestBody Pedido pedido) {
        try {
            return new ResponseEntity<Pedido>(pedidoService.save(pedido), HttpStatus.CREATED );
        } catch (Exception e) {
            return new ResponseEntity<>( pedido, HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Pedido>> findAll() {
        try {
            var result = pedidoService.findAll();
            return new ResponseEntity<>( result, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Integer id) {
        try {
            var result = pedidoService.findById( id );
            return new ResponseEntity<>( result, HttpStatus.FOUND );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findByStatus")
    public ResponseEntity<List<Pedido>> findByStatus(@RequestParam String status) {
        try {
            List<Pedido> result = pedidoService.findByStatus( status );
            
            return new ResponseEntity<>( result, HttpStatus.FOUND );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findByProdutoNome")
    public ResponseEntity<List<Pedido>> findByProdutoNome(@RequestParam String nomeProduto) {
        try {
            List<Pedido> result = pedidoService.findByProdutoNome( nomeProduto );
            
            return new ResponseEntity<>( result, HttpStatus.FOUND );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }


    @GetMapping("/findByProduto")
    public ResponseEntity<List<Pedido>> findByProduto(@RequestParam int produtoId) {
        try {
            List<Pedido> result = pedidoService.findByProduto( produtoId );
            
            return new ResponseEntity<>( result, HttpStatus.FOUND );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Pedido> update(@PathVariable Integer id, @RequestBody Pedido pedido) {
        try {
            var result = pedidoService.findById( id );
            return new ResponseEntity<>( result, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            pedidoService.delete( id );
            return new ResponseEntity<>( null, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }
}
