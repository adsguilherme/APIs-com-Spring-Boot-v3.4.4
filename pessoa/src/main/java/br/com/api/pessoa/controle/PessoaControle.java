package br.com.api.pessoa.controle;

/*
Implementar a camada de controle, que é responsável por gerenciar rotas.
Uma rota é um caminho e esse caminho vai executar uma ação (get, post, put, delete).
*/

import br.com.api.pessoa.repositorio.PessoaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController // Aqui estamos especificando que a classe PessoaControle é um controlador REST.
@RequiredArgsConstructor
public class PessoaControle {

    // Atributos
    // @Autowired // Injeção de dependência
    private final PessoaRepositorio pr; // Com o final estamos aplicando o princípio da imutabilidade

    // @Autowired // Injeção de dependência
    //public PessoaControle(PessoaRepositorio pr) {
    //    this.pr = pr;
    //}

    // Metodo
    @GetMapping("/mensagem") // Mapeia requisições GET para a raiz ("/") do contexto da aplicação.
    public String mensagem() {
        return "Aprendendo spring boot v3.5.7"; // Dependência de Live reload para atualizar a página, sem ter que reiniciar o servidor.
    }

    @GetMapping("/apresentacao/{nome}")
    public String apresentacao(@PathVariable String nome) {
        return "Olá " + nome;
    }
}