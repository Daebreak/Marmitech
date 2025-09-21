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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProdutoController {
    private final ProdutoService produtoService;

    @PostMapping("/save")
    public ResponseEntity<ProdutoListaDTO> save(@RequestBody @Valid ProdutoSaveDTO produtoDto) {
        ProdutoListaDTO produto = produtoService.save( produtoDto );
        return new ResponseEntity<>( produto, HttpStatus.CREATED );
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ProdutoListaDTO>> findAll() {
        return new ResponseEntity<>( produtoService.findAll(), HttpStatus.OK );

    }

    @GetMapping("findById/{id}")
    public ResponseEntity<ProdutoListaDTO> findById(@PathVariable Integer id) {
        var result = produtoService.findById( id );

        ProdutoListaDTO produtoDto = ProdutoListaMapper.toDto(result);

        return new ResponseEntity<>( produtoDto, HttpStatus.OK );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Produto> update(@PathVariable Integer id, @RequestBody Produto produto) {
        var result = produtoService.update( id, produto );
        return new ResponseEntity<>( result, HttpStatus.OK );

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        produtoService.delete( id );
        return new ResponseEntity<>( null, HttpStatus.OK );
    }

}