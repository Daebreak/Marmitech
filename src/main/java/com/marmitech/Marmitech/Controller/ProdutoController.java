package com.marmitech.Marmitech.Controller;

import com.marmitech.Marmitech.DTO.RequestDTO.ProdutoSaveDTO;
import com.marmitech.Marmitech.DTO.ResponseDTO.ProdutoListaDTO;
import com.marmitech.Marmitech.Entity.Produto;
import com.marmitech.Marmitech.Mapper.ResponseMapper.ProdutoListaMapper;
import com.marmitech.Marmitech.Services.ProdutoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProdutoController {
    private final ProdutoService produtoService;

    @PostMapping("/save")
    public ResponseEntity<ProdutoListaDTO> save(@RequestBody @Valid ProdutoSaveDTO produtoDto) {
        try {
            ProdutoListaDTO produto = produtoService.save( produtoDto );
            return new ResponseEntity<>( produto, HttpStatus.CREATED );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProdutoListaDTO>> findAll() {
        try {
            return new ResponseEntity<>( produtoService.findAll(), HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<ProdutoListaDTO> findById(@PathVariable Integer id) {
        try {
            var result = produtoService.findById( id );

            ProdutoListaDTO produtoDto = ProdutoListaMapper.toDto(result);

            return new ResponseEntity<>( produtoDto, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Produto> update(@PathVariable Integer id, @RequestBody Produto produto) {
        try {
            var result = produtoService.update( id, produto );
            return new ResponseEntity<>( result, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            produtoService.delete( id );
            return new ResponseEntity<>( null, HttpStatus.OK );
        } catch (Exception ex) {
            return new ResponseEntity<>( null, HttpStatus.BAD_REQUEST );
        }
    }

}