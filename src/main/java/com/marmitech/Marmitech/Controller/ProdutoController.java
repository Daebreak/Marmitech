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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProdutoController {
    private final ProdutoService produtoService;
    @PreAuthorize("hasRole('admin') or hasRole('Caixa')")
    @PostMapping("/save")
    public ResponseEntity<ProdutoListaDTO> save(@RequestBody @Valid ProdutoSaveDTO produtoDto) {
        ProdutoListaDTO produto = produtoService.save( produtoDto );
        return new ResponseEntity<>( produto, HttpStatus.CREATED );
    }
    @PreAuthorize("hasRole('admin') or hasRole('Caixa')")
    @GetMapping("/findAll")
    public ResponseEntity<List<ProdutoListaDTO>> findAll() {
        return new ResponseEntity<>( produtoService.findAll(), HttpStatus.OK );

    }
    @PreAuthorize("hasRole('admin') or hasRole('Caixa')")
    @GetMapping("findById/{id}")
    public ResponseEntity<ProdutoListaDTO> findById(@PathVariable Integer id) {
        var result = produtoService.findById( id );

        ProdutoListaDTO produtoDto = ProdutoListaMapper.toDto(result);

        return new ResponseEntity<>( produtoDto, HttpStatus.OK );
    }
    @PreAuthorize("hasRole('admin') or hasRole('Caixa')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Produto> update(@PathVariable Integer id, @RequestBody Produto produto) {
        var result = produtoService.update( id, produto );
        return new ResponseEntity<>( result, HttpStatus.OK );

    }
    @PreAuthorize("hasRole('admin') or hasRole('Caixa')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        produtoService.delete( id );
        return new ResponseEntity<>( null, HttpStatus.OK );
    }

}