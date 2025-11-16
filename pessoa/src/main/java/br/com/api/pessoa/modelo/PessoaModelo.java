package br.com.api.pessoa.modelo;

/*
Implementar a camada de modelo, ela é importante porque através dos modelos que o spring boot vai criar objetos para enviarmos e receber, através das rotas.
*/

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
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
    private Long codigo; // Alterando para Long. Pois long é um tipo primitivo e não suporte verificar se um tipo de dado é null

    @NotBlank(message = "O nome é obrigatório.") // Implementação das annotation de validação
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
    private String nome;

    @NotNull(message = "A idade é obrigatória.")
    @Min(value = 0, message = "A idade deve ser maior ou igual a 0.")
    @Max(value = 120, message = "A idade máxima permitida é 120.")
    private Integer idade; // Alterando para Integer. Pois int é um tipo primitivo e não suporte verificar se um tipo de dado é null

    @NotBlank(message = "A cidade é obrigatório.")
    @Size(min = 3, max = 30, message = "O nome da cidade deve ter entre 3 e 30 caracteres.")
    private String cidade;

}
