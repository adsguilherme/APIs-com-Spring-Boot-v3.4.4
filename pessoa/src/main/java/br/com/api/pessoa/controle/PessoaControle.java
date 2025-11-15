package br.com.api.pessoa.controle;

/*
Implementar a camada de controle, que é responsável por gerenciar rotas.
Uma rota é um caminho e esse caminho vai executar uma ação (get, post, put, delete).
*/

import br.com.api.pessoa.modelo.PessoaModelo;
import br.com.api.pessoa.repositorio.PessoaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // Aqui estamos especificando que a classe PessoaControle é um controlador REST.
@RequiredArgsConstructor
public class PessoaControle {

    // Atributo da classe PessoaControle
    // @Autowired // Injeção de dependência
    private final PessoaRepositorio pr; // Com o final estamos aplicando o princípio da imutabilidade

    // @Autowired // Injeção de dependência
    //public PessoaControle(PessoaRepositorio pr) {
    //    this.pr = pr;
    //}

    // Rota responsável pela listagem de pessoas
    @GetMapping("/")
    public Iterable<PessoaModelo> listarPessoas() { // Iterable é uma interface de coleções
        return this.pr.findAll(); // findAll equivale a select * from pessoas
    }

    // Rota responsável pelo cadastro de pessoas
    @PostMapping("/")
    public PessoaModelo cadastrarPessoa(@RequestBody PessoaModelo pm) {
        return this.pr.save(pm); // save equivale a into e update
    }

    // Rota responsável pela alteração dos dados de uma pessoa
    @PutMapping("/{codigo}")
    public PessoaModelo alterarPessoaTotal(@PathVariable long codigo, @RequestBody PessoaModelo pm) {
        pm.setCodigo(codigo);
        return this.pr.save(pm);
    }

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

    // Metodo
//    @GetMapping("/mensagem") // Mapeia requisições GET para a raiz ("/") do contexto da aplicação.
//    public String mensagem() {
//        return "Aprendendo spring boot v3.5.7"; // Dependência de Live reload para atualizar a página, sem ter que reiniciar o servidor.
//    }
//
//    @GetMapping("/apresentacao/{nome}")
//    public String apresentacao(@PathVariable String nome) {
//        return "Olá " + nome;
//    }
}