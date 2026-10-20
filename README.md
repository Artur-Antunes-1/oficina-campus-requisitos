# Oficina Campus

Projeto de Requisitos de Software e FCCPD.

Equipe: Artur Antunes, Ricardo, Luca, Victor e João Cláudio.
Partes integradas nesta etapa: Catálogo (4 funcionalidades).

## Executar

Com JDK 17+ e Maven 3.6.3+, na raiz:

```sh
mvn verify
java -jar apresentacao-backend/target/oficina-campus.jar
```

Abra http://127.0.0.1:8080. O banco H2 fica em `dados/`.
`prototipo.html` contém a demonstração com dados fictícios no navegador.

## Material

- [Domínio](docs/dominio.md)
- [Requisitos](docs/requisitos.md)
- [API](docs/api.md)
- [Modelo CML](oficina-campus.cml)
- [Desenvolvimento e apoio de IA](docs/interacoes/desenvolvimento.md)

A estrutura segue https://github.com/profsauloaraujo/sgb-2025-01.
O grupo trabalha na branch `main`. Atualize com `git pull --ff-only` antes de
iniciar uma alteração e teste a parte antes de enviar.
