# API local

Base: `http://127.0.0.1:8080/api`. JSON em UTF-8. O servidor padrão é local, sem autenticação. A interface web é servida pelo mesmo processo.

| Método e caminho | Resultado |
|---|---|
| `GET /meta` | Definições dos módulos instalados e seus campos |
| `GET /registros/{modulo}` | Lista com `items`, `total`, `offset` e `limite` |
| `GET /registros/{modulo}/{id}` | Uma ficha com dados, estado, versão e datas |
| `POST /registros/{modulo}` | Cria uma ficha; retorna 201 e cabeçalho Location |
| `PUT /registros/{modulo}/{id}` | Substitui os dados da ficha, exigindo a versão atual |
| `POST /registros/{modulo}/{id}/arquivar` | Arquiva sem excluir os dados |
| `POST /registros/{modulo}/{id}/restaurar` | Restaura conforme a política do módulo |
| `GET /historico` | Até 100 alterações desta execução, em memória |

Conteúdos também aceitam as ações `publicar` e `ocultar`; `GET /public/conteudo` retorna somente os visíveis.

## Exemplo: criar oficina

```http
POST /api/registros/oficinas
Content-Type: application/json

{
  "dados": {
    "titulo": "Introdução à lógica de programação",
    "apresentacao": "Uma oficina prática de primeiros passos.",
    "modalidade": "Presencial",
    "duracao": 120
  }
}
```

Para editar, envie o mesmo objeto `dados` com todos os campos e acrescente `"versao": 0`, usando a versão recebida na última consulta. Para uma ação de estado, envie apenas `{"versao": 0}`. A versão aumenta em cada mudança; uma versão antiga retorna 409.

## Consulta e erros

Exemplo de filtro: `/registros/oficinas?q=logica&estado=ATIVO&offset=0&limite=20`. A pesquisa ignora maiúsculas/minúsculas, mas preserva a distinção entre caracteres acentuados. `limite` deve ser de 1 a 100; o cliente deve percorrer as páginas quando houver mais registros.

- 400: campos, formato ou paginação inválidos.
- 404: módulo, registro ou ação inexistente.
- 409: versão antiga ou transição incompatível com o estado atual.

Os erros de negócio retornam uma propriedade `mensagem`. Não existe exclusão definitiva pela API. As definições dos campos e as regras particulares estão em [requisitos.md](requisitos.md) e nos testes Cucumber.


As rotas são implementadas por controladores/formulários tipados por conceito. Metadados visuais em `/api/meta` não validam o domínio. Números devem ser inteiros JSON; textos numéricos e decimais não são convertidos silenciosamente. As regras residem nos agregados. Cada serviço de aplicação acessa uma porta específica do contexto, com implementação ORM transacional.
