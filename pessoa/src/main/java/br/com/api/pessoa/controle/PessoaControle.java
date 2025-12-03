package br.com.api.pessoa.controle;

/*
Implementar a camada de controle, que é responsável por gerenciar rotas.
Uma rota é um caminho e esse caminho vai executar uma ação (get, post, put, patch e delete).
*/

import br.com.api.pessoa.modelo.PessoaModelo;
import br.com.api.pessoa.repositorio.PessoaRepositorio;
import br.com.api.pessoa.servico.PessoaServico;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController // Aqui estamos especificando que a classe PessoaControle é um controlador REST.
@RequiredArgsConstructor
public class PessoaControle {

    // Atributo da classe PessoaControle
    // @Autowired // Injeção de dependência
    private final PessoaServico ps; // Com o final estamos aplicando o princípio da imutabilidade

    // @Autowired // Injeção de dependência
    //public PessoaControle(PessoaRepositorio pr) {
    //    this.pr = pr;
    //}

    // Rota responsável pela listagem de pessoas
    @GetMapping("/")
    public ResponseEntity<Iterable<PessoaModelo>> listarPessoas() { // Iterable é uma interface de coleções
       // return new ResponseEntity<>(this.pr.findAll(), HttpStatus.OK); // findAll equivale a select * from pessoas
       return this.ps.listarPessoas();
    }

    // Rota responsável pelo cadastro de pessoas
    @PostMapping("/")
    public ResponseEntity<PessoaModelo> cadastrarPessoa(@Valid @RequestBody PessoaModelo pm) {
        // return new ResponseEntity<>(this.pr.save(pm), HttpStatus.CREATED); // save equivale a into e update
        return this.ps.cadastrarPessoa(pm);
    }

    // Rota responsável pela alteração total dos dados de uma pessoa
    @PutMapping("/{codigo}")
    public ResponseEntity<PessoaModelo> alterarPessoaTotal(@Valid @PathVariable Long codigo, @RequestBody PessoaModelo pm) {
        // Obter o registro contido na tabela
        // Optional<PessoaModelo> obj = this.pr.findById(codigo); // select * from pessoas where código = 1

        // Condicional
       // if (obj.isPresent()) {
       //     pm.setCodigo(codigo);
       //     return new ResponseEntity<>(this.pr.save(pm), HttpStatus.OK);
       // }

        // Caso o ID não exista
        // return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return this.ps.alterarPessoaTotal(codigo, pm);
    }

    // Rota responsável pela alteração parcial dos dados
    @PatchMapping("/{codigo}")
    public ResponseEntity<PessoaModelo> alterarPessoaParcial(@PathVariable Long codigo, @RequestBody PessoaModelo pm) {

        // Forma simplificada não precisando converter Optional para PessoaModelo
        // PessoaModelo pm2 = this.pr.findById(codigo).get();

        // Obter o registro contido na tabela
        // Optional<PessoaModelo> obj = this.pr.findById(codigo); // select * from pessoas where código = 1

        // Condicional
        // if (obj.isPresent()) {
        //   pm.setCodigo(codigo);

            // Converter Optional para PessoaModelo
           // PessoaModelo pm2 = obj.get();

            // Verificação (preciso saber quais as informacoes especificadas no @RequestBody)
           // if (pm.getNome() != null) { // Se pm.getNome for diferente de null
           //     pm2.setNome(pm.getNome()); // então pm2.setNome recebe pm.getNome
           // }

           // if (pm.getIdade() != null) {
           //     pm2.setIdade(pm.getIdade());
           // }

           // if (pm.getCidade() != null) {
           //     pm2.setCidade(pm.getCidade());
           // }

            // Retorno
           // return new ResponseEntity<>(this.pr.save(pm2), HttpStatus.OK);
       // }

        // Caso o ID não exista
        // return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return this.ps.alterarPessoaParcial(codigo, pm);
    }

    // Rota responsável pela remoção dos dados
    @DeleteMapping("/{codigo}")
    public ResponseEntity<Void> removerPessoa(@PathVariable Long codigo) { // alterado de void para a classe Void
        // Verificar a existência do id
       // boolean existeCodigo = this.pr.existsById(codigo);

        // Condicional
       // if (existeCodigo) { // Se o existeCodigo for verdadeiro, execute o deleteById e retorne o status
       //     this.pr.deleteById(codigo);
       //     return new ResponseEntity<>(HttpStatus.OK);
   // }
        // Caso o ID não exista
       // return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return this.ps.removerPessoa(codigo);
    }

    // Rota responsável pelos testes

    @GetMapping("/teste")
    public Iterable<PessoaModelo> teste(){
        return this.ps.teste("Rio de Janeiro", "São Paulo");
    }

}

    /*
    No Spring, o Optional é usado para indicar que uma busca pode não retornar resultado.
    Por exemplo, métodos como findById() retornam Optional<T> justamente para evitar trabalhar com null e para forçar o desenvolvedor a tratar a ausência do registro.
    Ele foi introduzido no Java 8 e ajuda a reduzir NullPointerException.
    Uma dica, se você utilizar o VSCode ou o IntelliJ, pode passar o cursor sobre o termo Optional, ele exibe uma breve explicação sobre a classe.
    */

    /*
     * @PatchMapping("/{codigo}"): Esta anotação mapeia requisições HTTP PATCH para este metodo.
     * O "{codigo}" na URL indica que um valor será passado como parte do caminho da URL,
     * representando o código da pessoa a ser alterada parcialmente.
     *
     * public PessoaModelo alterarPessoaParcial(@PathVariable long codigo, @RequestBody PessoaModelo pm):
     * - @PathVariable long codigo: Extrai o valor do "codigo" da URL e o injeta como um parâmetro 'codigo' do tipo long.
     * - @RequestBody PessoaModelo pm: Indica que o corpo da requisição HTTP (que deve ser um JSON)
     *   será desserializado para um objeto PessoaModelo e injetado como o parâmetro 'pm'.
     *   Este 'pm' conterá apenas os campos que o cliente deseja atualizar.
     *
     * Optional<PessoaModelo> obj = this.pr.findById(codigo);:
     *   Busca no banco de dados a pessoa com o 'codigo' fornecido. O retorno é um Optional,
     *   que é uma forma de lidar com a possibilidade de o registro não ser encontrado, evitando NullPointerExceptions.
     *
     * PessoaModelo pm2 = obj.get();:
     *   Obtém o objeto PessoaModelo real do Optional. É importante garantir que o Optional não esteja vazio antes de chamar 'get()'.
     *   (Em um cenário real, você adicionaria uma verificação 'obj.isPresent()' e trataria o caso de não encontrado).
     *
     * if (pm.getNome() != null) { pm2.setNome(pm.getNome()); }:
     *   Verifica se o campo 'nome' foi fornecido no corpo da requisição (pm). Se sim, atualiza o 'nome' do objeto existente (pm2).
     *   Isso é feito para cada campo (nome, idade, cidade), garantindo que apenas os campos presentes na requisição PATCH sejam alterados.
     *
     * return this.pr.save(pm2);: Salva as alterações no banco de dados. Como 'pm2' já tem um 'codigo' existente,
     * o Spring Data JPA realizará uma operação de atualização (UPDATE) apenas nos campos que foram modificados.
     */

    /*
     * @PutMapping("/{codigo}"): Esta anotação mapeia requisições HTTP PUT para este metodo.
     * O "{codigo}" na URL indica que um valor será passado como parte do caminho da URL,
     * representando o código da pessoa a ser alterada.
     *
     * public PessoaModelo alterarPessoaTotal(@PathVariable long codigo, @RequestBody PessoaModelo pm):
     * - @PathVariable long codigo: Extrai o valor do "codigo" da URL e o injeta como um parâmetro 'codigo' do tipo long.
     * - @RequestBody PessoaModelo pm: Indica que o corpo da requisição HTTP (que deve ser um JSON ou XML)
     *   será desserializado para um objeto PessoaModelo e injetado como o parâmetro 'pm'.
     *
     * pm.setCodigo(codigo);: Garante que o objeto PessoaModelo recebido no corpo da requisição
     * tenha o mesmo código que foi passado na URL. Isso é importante para que o metodo 'save'
     * do repositório saiba qual registro atualizar.
     *
     * return this.pr.save(pm);: Salva as alterações no banco de dados. Se um objeto PessoaModelo
     * com um 'codigo' existente for passado para o metodo 'save', o Spring Data JPA
     * (através do JpaRepository) realizará uma operação de atualização (UPDATE).
     * Se o 'codigo' não existir, ele tentará inserir um novo registro (INSERT),
     * embora para um PUT, o esperado seja uma atualização.
     */

//    Metodo
//    @GetMapping("/mensagem") // Mapeia requisições GET para a raiz ("/") do contexto da aplicação.
//    public String mensagem() {
//        return "Aprendendo spring boot v3.5.7"; // Dependência de Live reload para atualizar a página, sem ter que reiniciar o servidor.
//    }
//
//    @GetMapping("/apresentacao/{nome}")
//    public String apresentacao(@PathVariable String nome) {
//        return "Olá " + nome;
//    }
