package com.marmitech.Marmitech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marmitech.Marmitech.Entity.PedidoItem;
import com.marmitech.Marmitech.Service.PedidoItemService;

@RestController
@RequestMapping("/pedidoItem")
public class PedidoItemController {

    @Autowired
    private PedidoItemService pedidoItemService;

    @RequestMapping("/save")
    public ResponseEntity<PedidoItem> save(@RequestBody PedidoItem pedidoItem){
        try {
            return new ResponseEntity<>(pedidoItemService.save(pedidoItem), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/update/{pedidoItemId}")
    public ResponseEntity<PedidoItem> update(@RequestBody PedidoItem pedidoItem, @PathVariable int pedidoItemId){
        try {
            return new ResponseEntity<>(pedidoItemService.update(pedidoItem, pedidoItemId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    
    @RequestMapping("/delete/{pedidoItemId}")
    public ResponseEntity<String> delete(@PathVariable int pedidoItemId){
        try {
            return new ResponseEntity<>(pedidoItemService.delete(pedidoItemId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao deletar pedido item!", HttpStatus.BAD_REQUEST);
        }
    }
        
    @RequestMapping("/findById/{pedidoItemId}")
    public ResponseEntity<PedidoItem> findById(@PathVariable int pedidoItemId){
        try {
            return new ResponseEntity<>(pedidoItemService.findById(pedidoItemId), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

        
    @RequestMapping("/findAll")
    public ResponseEntity<List<PedidoItem>> findAll(){
        try {
            return new ResponseEntity<>(pedidoItemService.findAll(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
