package br.com.api.pessoa.repositorio;

import br.com.api.pessoa.modelo.PessoaModelo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepositorio extends CrudRepository<PessoaModelo, Long> {

    Iterable<PessoaModelo> findByCidade(String cidade);

    Iterable<PessoaModelo> findByCidadeOrCidade(String cidade1, String cidade2);

    Iterable<PessoaModelo> findByIdadeGreaterThanEqual(Integer idade);

    Iterable<PessoaModelo> findByIdadeBetween(Integer idade1, Integer idade2);

    Iterable<PessoaModelo> findByNomeLikeIgnoreCase(String nome);

    Iterable<PessoaModelo> findByOrderByNomeAsc();

    Long countByCidade(String cidade);

    @Query("SELECT SUM(p.idade) FROM PessoaModelo p")
    Long calcularSomaIdades();

    /*
     * A finalidade de um Repository (Repositório) no Spring Boot, especialmente quando estende
     * CrudRepository (ou JpaRepository), é atuar como uma camada de abstração para a persistência de dados.
     *
     * Em termos simples, ele é responsável por:
     * 1. **Interagir com o Banco de Dados:** Ele sabe como salvar, buscar, atualizar e deletar dados
     *    de uma entidade específica (neste caso, `PessoaModelo`) no banco de dados.
     * 2. **Esconder a Complexidade do JDBC/JPA:** Você não precisa escrever SQL ou código JPA diretamente.
     *    O Spring Data JPA (que o CrudRepository utiliza) gera automaticamente as queries para você.
     * 3. **Fornecer Operações CRUD Básicas:** Métodos como `save()`, `findById()`, `findAll()`, `delete()`
     *    já vêm prontos, facilitando muito o desenvolvimento.
     * 4. **Definir Consultas Personalizadas:** Você pode adicionar seus próprios métodos de busca
     *    (ex: `findByNome(String nome)`) e o Spring Data JPA os implementa automaticamente com base
     *    no nome do metodo.
     *
     * Em resumo, o Repository é a ponte entre sua aplicação e o banco de dados, simplificando
     * drasticamente as operações de persistência.
     */
}
