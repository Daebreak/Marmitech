package com.marmitech.Marmitech.controller;
import com.marmitech.Marmitech.Entity.pedidos;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping ("/api/pedido")
@RequiredArgsConstructor
public class pedidosController {

    @PostMapping("/save")
    public ResponseEntity<pedidos> save (@RequestBody pedidos Pedidos){
        return save(Pedidos);
    }
    @GetMapping("/findAll")
    public ResponseEntity<List<pedidos>>findAll(){
        return findAll();
    }
    @PutMapping("/updatepedidos/{id}")
        public ResponseEntity<pedidos> update (@PathVariable Integer id, @RequestBody pedidos Pedidos){
        return update(id, Pedidos);
        }
        @DeleteMapping("/delete/{id}")
    public RequestEntity<Void> delete (@PathVariable Integer id){

            return null;
        }
    }

