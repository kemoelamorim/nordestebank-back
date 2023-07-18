package br.com.nordestebank.conta.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nordestebank.conta.model.Conta;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{
    @Transactional
    @Modifying
    @Query("DELETE FROM Endereco e WHERE e.conta.id = :idConta")
    void deleteEnderecosByContaId(Long idConta);

}
