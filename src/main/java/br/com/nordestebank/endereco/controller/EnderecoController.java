package br.com.nordestebank.endereco.controller;

import br.com.nordestebank.conta.model.Conta;
import br.com.nordestebank.conta.model.ContaDTO;
import br.com.nordestebank.conta.service.ContaServiceImpl;
import br.com.nordestebank.endereco.model.Endereco;
import br.com.nordestebank.endereco.model.EnderecoDTO;
import br.com.nordestebank.endereco.model.EnderecoViaCepDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import br.com.nordestebank.endereco.service.ServiceEnderecoImpl;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/enderecos")
public class EnderecoController {
  @Autowired
  public ServiceEnderecoImpl serviceEnderecoImpl;
  @Autowired
  public ContaServiceImpl contaService;

  @ApiOperation(value = "Busca o endereco pela api viacep")
  @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Retorna o endereço do cliente"),
        @ApiResponse(code = 204, message = "Sem conteúdo"),
        @ApiResponse(code = 500, message = "Foi gerada uma exceção")
  })
  @GetMapping("/viacep/{cep}")
  public ResponseEntity<?> endereco(@PathVariable("cep") String id){
      EnderecoViaCepDTO enderecoViaCepDTO = this.serviceEnderecoImpl.getEnderecoViaCep(id);
      return new ResponseEntity<>(new EnderecoViaCepDTO(enderecoViaCepDTO), HttpStatus.OK);
  }
    @ApiOperation(value = "Busca endereços pela id da conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os endereços do titular"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção")
    })
    @GetMapping("/findAll/conta/{id}")
    public ResponseEntity<?> enderecoContaById(@PathVariable("id") Long id){
        List<EnderecoDTO> enderecos = this.serviceEnderecoImpl.enderecosContaById(id);
        if(enderecos != null){
          return !enderecos.isEmpty() ? ResponseEntity.ok(enderecos) : ResponseEntity.noContent().build();
        }
        return ResponseEntity.badRequest().build();
    }
    @ApiOperation(value = "Salva um endereco para uma conta passando o ID da conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Salva um endereco"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PostMapping("/save/conta/{id}")
    public ResponseEntity<?> save(@PathVariable("id") Long id, @RequestBody EnderecoDTO enderecoDTO){
        log.info("save - Endereco");
        if (enderecoDTO != null) {
            Conta conta = contaService.findById(id);
            enderecoDTO.setConta(conta);
            Endereco endereco = serviceEnderecoImpl.saveEndereco(enderecoDTO);
            return new ResponseEntity<>(new EnderecoDTO(endereco), HttpStatus.CREATED);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @ApiOperation(value = "Edita um endereco")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Edita um endereco e retorna"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping("/update")
    public ResponseEntity<?> edit(@RequestBody EnderecoDTO enderecoDTO){
        log.info("update - Endereco");

        if (enderecoDTO != null) {
            Endereco endereco = serviceEnderecoImpl.editEndereco(enderecoDTO);
            return new ResponseEntity<>(new EnderecoDTO(endereco), HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @ApiOperation(value = "Exclui um endereco")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exclui um endereco"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        log.info("delete - Endereco");
        if (id != null) {
            this.serviceEnderecoImpl.deleteEndereco(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @ApiOperation(value = "Retorna um endereco")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma endereco"),
            @ApiResponse(code = 204, message = "Sem conteúdo"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        log.info("findById - Endereco");
         if (id != null) {
            EnderecoDTO endereco = this.serviceEnderecoImpl.findById(id);
            return new ResponseEntity<>(new Endereco(endereco), HttpStatus.OK);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
