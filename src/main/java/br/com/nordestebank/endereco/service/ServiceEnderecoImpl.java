package br.com.nordestebank.endereco.service;

import br.com.nordestebank.endereco.model.EnderecoViaCepDTO;
import br.com.nordestebank.endereco.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.nordestebank.endereco.model.Endereco;
import br.com.nordestebank.endereco.model.EnderecoDTO;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceEnderecoImpl implements ServiceViaCep, ServiceEndereco {
    private static final String URL_BASE = "https://viacep.com.br/ws/";
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Override
    public EnderecoViaCepDTO getEnderecoViaCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = URL_BASE + cep + "/json";
        EnderecoViaCepDTO endereco = restTemplate.getForObject(url, EnderecoViaCepDTO.class);
        return endereco;
    }

    @Override
    public void deleteEndereco(Long id) {
        if(id != null){
            Optional<Endereco> enderecoOptional = this.enderecoRepository.findById(id);
            if(enderecoOptional.isPresent()){
                this.enderecoRepository.delete(enderecoOptional.get());
            }else{
                throw new RuntimeException("Endereco não encontrado.");
            }
        }
    }

    @Override
    public Endereco editEndereco(EnderecoDTO enderecoDTO) {
        if (enderecoDTO != null) {
            Optional<Endereco> enderecoOptional = this.enderecoRepository.findById(enderecoDTO.getId());
            if (enderecoOptional.isPresent()) {
                Endereco endereco = new Endereco(enderecoDTO);
                return this.enderecoRepository.save(endereco);
            } else {
                throw new RuntimeException("Endereco não encontrado.");
            }
        } else {
            throw new RuntimeException("Problema ao editar endereco.");
        }
    }

    @Override
  public List<Endereco> enderecosContaById(Long id) {
      List<Endereco> enderecosByContaId = this.enderecoRepository.findEnderecosByContaId(id);
      return enderecosByContaId;
  }

  @Override
    public Endereco saveEndereco(EnderecoDTO enderecoDTO) {
      if(enderecoDTO != null){
          return this.enderecoRepository.save(new Endereco(enderecoDTO));
      }
      throw new RuntimeException("Problema ao salvar endereco.");
    }
}
