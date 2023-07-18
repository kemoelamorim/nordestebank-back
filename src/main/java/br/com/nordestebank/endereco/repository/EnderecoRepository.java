package br.com.nordestebank.endereco.repository;

import br.com.nordestebank.endereco.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco, Long>{
    @Query("SELECT e FROM Endereco e WHERE e.conta.id = :id")
    List<Endereco> findEnderecosByContaId(Long id);
    @Query("DELETE FROM Endereco e WHERE e.conta.id = :id")
    void deletePorIdConta(Long id);
}
