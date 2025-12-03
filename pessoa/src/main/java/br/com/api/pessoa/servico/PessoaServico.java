package br.com.api.pessoa.servico;

import br.com.api.pessoa.modelo.PessoaModelo;
import br.com.api.pessoa.repositorio.PessoaRepositorio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaServico {

  // Atributo da classe
  private final PessoaRepositorio pr;

    // Método responsável pela listagem de pessoas
    // @GetMapping("/")
    public ResponseEntity<Iterable<PessoaModelo>> listarPessoas() { // Iterable é uma interface de coleções
        return new ResponseEntity<>(this.pr.findAll(), HttpStatus.OK); // findAll equivale a select * from pessoas
    }

    // Método responsável pelo cadastro de pessoas
    // @PostMapping("/")
    public ResponseEntity<PessoaModelo> cadastrarPessoa(PessoaModelo pm) { // alterado de @Valid @RequestBody para apenas PessoaModelo pm
        return new ResponseEntity<>(this.pr.save(pm), HttpStatus.CREATED); // save equivale a into e update
    }

    // Método responsável pela alteração total dos dados de uma pessoa
    // @PutMapping("/{codigo}")
    public ResponseEntity<PessoaModelo> alterarPessoaTotal(Long codigo, PessoaModelo pm) { // @Valid @PathVariable @RequestBody
        // Obter o registro contido na tabela
        Optional<PessoaModelo> obj = this.pr.findById(codigo); // select * from pessoas where código = 1

        // Condicional
        if (obj.isPresent()) {
            pm.setCodigo(codigo);
            return new ResponseEntity<>(this.pr.save(pm), HttpStatus.OK);
        }

        // Caso o ID não exista
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Método responsável pela alteração parcial dos dados
    // @PatchMapping("/{codigo}")
    public ResponseEntity<PessoaModelo> alterarPessoaParcial(Long codigo, PessoaModelo pm) { // @PathVariable @RequestBody

        // Forma simplificada não precisando converter Optional para PessoaModelo
        // PessoaModelo pm2 = this.pr.findById(codigo).get();

        // Obter o registro contido na tabela
        Optional<PessoaModelo> obj = this.pr.findById(codigo); // select * from pessoas where código = 1

        // Condicional
        if (obj.isPresent()) {
            pm.setCodigo(codigo);

            // Converter Optional para PessoaModelo
            PessoaModelo pm2 = obj.get();

            // Verificação (preciso saber quais as informacoes especificadas no @RequestBody)
            if (pm.getNome() != null) { // Se pm.getNome for diferente de null
                pm2.setNome(pm.getNome()); // então pm2.setNome recebe pm.getNome
            }

            if (pm.getIdade() != null) {
                pm2.setIdade(pm.getIdade());
            }

            if (pm.getCidade() != null) {
                pm2.setCidade(pm.getCidade());
            }

            // Retorno
            return new ResponseEntity<>(this.pr.save(pm2), HttpStatus.OK);
        }

        // Caso o ID não exista
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Método responsável pela remoção dos dados
    // @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removerPessoa(Long codigo) { // alterado de void para a classe Void // @PathVariable
        // Verificar a existência do id
        boolean existeCodigo = this.pr.existsById(codigo);

        // Condicional
        if (existeCodigo) { // Se o existeCodigo for verdadeiro, execute o deleteById e retorne o status
            this.pr.deleteById(codigo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        // Caso o ID não exista
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Metodo para testar as funcionalidades implementadas no repositórios
    public Iterable<PessoaModelo> teste(String cidade){
        return this.pr.findByCidade(cidade);
    }
}

/**
 * Em um projeto Spring Boot, a service (ou serviço) tem a finalidade de centralizar as regras de negócio da aplicação.
 * Ela atua como uma camada intermediária entre os controllers (responsáveis por receber as requisições do cliente)
 * e os repositórios (que manipulam diretamente o banco de dados). É nessa camada que são implementadas as lógicas
 * mais complexas, validações, integrações com outros sistemas e processamento das informações antes de serem
 * persistidas ou retornadas. Dessa forma, a separação das responsabilidades melhora a organização do código,
 * facilita a manutenção, os testes e a reutilização das regras de negócio ao longo do sistema.
 */
