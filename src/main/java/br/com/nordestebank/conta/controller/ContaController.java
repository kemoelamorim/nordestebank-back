package br.com.nordestebank.conta.controller;

import br.com.nordestebank.conta.model.Conta;
import br.com.nordestebank.conta.model.ContaDTO;
import br.com.nordestebank.conta.service.ContaServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.annotations.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/contas")
@CrossOrigin("*")
public class ContaController {

    @Autowired
    public ContaServiceImpl contaService;

    @ApiOperation(value = "Retorna uma lista de contas, sem paginação")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de contas"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        log.info("findAll - Conta");
        List<ContaDTO>contas = contaService.findAll();
        if(!contas.isEmpty()){
            return !contas.isEmpty() ? ResponseEntity.ok(contas) : ResponseEntity.noContent().build();
        }
        return  ResponseEntity.badRequest().build();
    }



    @ApiOperation(value = "Retorna uma lista de contas, com paginação")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma lista de contas"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "1", required = false) Integer size,
            @RequestParam(name = "id", required = false) Long id,
            @RequestParam(name = "search", required = false) String search
        ){
        log.info("Paginacao - Conta");
        Pageable pageRequest = PageRequest.of(page, size);
        Page<ContaDTO> contas = contaService.findAll(pageRequest);
        if(!contas.getContent().isEmpty()){
            return !contas.isEmpty() ? ResponseEntity.ok(contas) : ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
    @ApiOperation(value = "Retorna todas as contas filtradas por nome")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma busca por nome"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/search")
    public ResponseEntity<?> buscaPorNome(
        @RequestParam(name = "nome", required = false) String nome
    ){  
        log.info("Buscando cantas por nome {}", nome);
        List<ContaDTO> contas = contaService.findByNome(nome);
        return !contas.isEmpty() ? ResponseEntity.ok(contas) : ResponseEntity.noContent().build();
    }


    @ApiOperation(value = "Retorna uma conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma conta"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        log.info("findById - Conta");
        Conta conta = this.contaService.findById(id);
        return new ResponseEntity<>(new ContaDTO(conta), HttpStatus.OK);

    }

    @ApiOperation(value = "Salva uma noma conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Salva uma nova conta e retorna conta"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ContaDTO contaDTO) {
        log.info("save - Conta");
        if (contaDTO != null) {
            Conta conta = contaService.saveConta(contaDTO);
            return new ResponseEntity<>(new ContaDTO(conta), HttpStatus.CREATED);
        } else {

            return ResponseEntity.badRequest().build();
        }

    }

    @ApiOperation(value = "Edita uma conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Editando uma conta"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ContaDTO contaDTO) {
        log.info("update - Conta");
        if (contaDTO != null) {
            Conta conta = contaService.editConta(contaDTO);
            return new ResponseEntity<>(new ContaDTO(conta), HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }


    }

    @ApiOperation(value = "Exclui uma conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exclui uma conta"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        log.info("delete - Conta");
        if (id != null) {
            this.contaService.deleteConta(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
