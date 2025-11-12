package br.com.api.pessoa.modelo;

/*
Implementar a camada de modelo, ela é importante porque através dos modelos que o spring boot vai criar objetos para enviarmos e receber, através das rotas.
*/

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

// Em vez de fazer o uso de getter e setter dos atributos, a dependência Lombok permite fazer a anotação (@Getter e @Setter) na classe. Com isso deixando nosso código mais limpo.
@Getter
@Setter
@Entity // Essa entidade faz com que o Spring Boot saiba que o modelo vai ser utilizado para gerar uma tabela. Toda tabela que precise ser gerada automaticamente, precisa dessa annotation.
@Table(name = "pessoas") // Aqui podemos renomear o nome da tabela. Pois o @Entity cria uma tabela com o mesmo nome da classe, e ficaria PessoaModelo.
public class PessoaModelo {

    //Atributos
    @Id // Chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento da tabela
    private Long codigo;
    private String nome;
    private int idade;
    private String cidade;

}
