package com.marmitech.Marmitech.Controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marmitech.Marmitech.DTO.ResponseDTO.PedidoItemResponseDTO;
import com.marmitech.Marmitech.Entity.PedidoItem;
import com.marmitech.Marmitech.Services.PedidoItemService;

@RestController
@RequestMapping("/pedidoItem")
@CrossOrigin("*")
public class PedidoItemController {

    @Autowired
    private PedidoItemService pedidoItemService;

    @PostMapping("/save")
    public ResponseEntity<PedidoItem> save(@RequestBody @Valid PedidoItem pedidoItem) {
        return new ResponseEntity<>( pedidoItemService.save( pedidoItem ), HttpStatus.CREATED );
    }

    @PutMapping("/update/{pedidoItemId}")
    public ResponseEntity<PedidoItem> update(@RequestBody PedidoItem pedidoItem, @PathVariable int pedidoItemId) {
        return new ResponseEntity<>( pedidoItemService.update( pedidoItem, pedidoItemId ), HttpStatus.CREATED );
    }


    @DeleteMapping("/delete/{pedidoItemId}")
    public ResponseEntity<String> delete(@PathVariable int pedidoItemId) {
        return new ResponseEntity<>( pedidoItemService.delete( pedidoItemId ), HttpStatus.CREATED );
    }

    @GetMapping("/findById/{pedidoItemId}")
    public ResponseEntity<PedidoItem> findById(@PathVariable int pedidoItemId) {
        return new ResponseEntity<>( pedidoItemService.findById( pedidoItemId ), HttpStatus.CREATED );
    }


    @GetMapping("/findAll")
    public ResponseEntity<List<PedidoItemResponseDTO>> findAll() {
        return new ResponseEntity<>( pedidoItemService.findAll(), HttpStatus.OK );
    }
}