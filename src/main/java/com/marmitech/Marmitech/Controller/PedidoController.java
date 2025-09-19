package com.marmitech.Marmitech.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.marmitech.Marmitech.DTO.ResponseDTO.PedidoResponseDTO;
import com.marmitech.Marmitech.Entity.Pedido;
import com.marmitech.Marmitech.Mapper.ResponseMapper.PedidoResponseMapper;
import com.marmitech.Marmitech.Services.PedidoService;

@RestController
@RequestMapping("/api/pedido")
@CrossOrigin("*")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @PostMapping("/save")
    public ResponseEntity<Pedido> save(@RequestBody Pedido pedido) {
        return new ResponseEntity<Pedido>( pedidoService.save( pedido ), HttpStatus.CREATED );
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> findAll() {
        var result = pedidoService.findAll();
        return new ResponseEntity<>( result, HttpStatus.OK );
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<PedidoResponseDTO> findById(@PathVariable Integer id) {
        var result = pedidoService.findById( id );
        PedidoResponseDTO pedidoDto = PedidoResponseMapper.toDto( result );
        return new ResponseEntity<>( pedidoDto, HttpStatus.FOUND );
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
        var result = pedidoService.findById( id );
        return new ResponseEntity<>( result, HttpStatus.OK );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        pedidoService.delete( id );
        return new ResponseEntity<>( null, HttpStatus.OK );
    }
}
