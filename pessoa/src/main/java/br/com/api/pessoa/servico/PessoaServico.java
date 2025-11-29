package br.com.api.pessoa.servico;

import br.com.api.pessoa.repositorio.PessoaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaServico {

  // Atributo da classe
  private final PessoaRepositorio pr;
}

/**
 * Em um projeto Spring Boot, a service (ou serviço) tem a finalidade de centralizar as regras de negócio da aplicação.
 * Ela atua como uma camada intermediária entre os controllers (responsáveis por receber as requisições do cliente)
 * e os repositórios (que manipulam diretamente o banco de dados). É nessa camada que são implementadas as lógicas
 * mais complexas, validações, integrações com outros sistemas e processamento das informações antes de serem
 * persistidas ou retornadas. Dessa forma, a separação das responsabilidades melhora a organização do código,
 * facilita a manutenção, os testes e a reutilização das regras de negócio ao longo do sistema.
 */
