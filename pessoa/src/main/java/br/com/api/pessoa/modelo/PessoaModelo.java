package br.com.api.pessoa.modelo;

/*
Implementar a camada de modelo, ela é importante porque através dos modelos que o spring boot vai criar objetos para enviarmos e receber, através das rotas.
*/

import lombok.Getter;
import lombok.Setter;

// Em vez de fazer o uso de getter e setter dos atributos, a dependência Lombok permite fazer a anotação (@Getter e @Setter) na classe. Com isso deixando nosso código mais limpo.
@Getter
@Setter
public class PessoaModelo {

    //Atributos
    private Long codigo;
    private String nome;
    private int idade;
    private String cidade;

//    // Get e Set
//    public Long getCodigo() {
//        return codigo;
//    }
//
//    public void setCodigo(Long codigo) {
//        this.codigo = codigo;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public int getIdade() {
//        return idade;
//    }
//
//    public void setIdade(int idade) {
//        this.idade = idade;
//    }
//
//    public String getCidade() {
//        return cidade;
//    }
//
//    public void setCidade(String cidade) {
//        this.cidade = cidade;
//    }
}
