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