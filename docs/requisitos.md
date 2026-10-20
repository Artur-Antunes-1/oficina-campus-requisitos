# Requisitos

Cada integrante fica responsável por quatro funcionalidades. A classificação
como baixa complexidade é a proposta do grupo e depende da avaliação do professor.
Todas permitem cadastro, consulta, alteração, arquivamento e restauração.

Textos são normalizados retirando espaços nas extremidades. Campos opcionais
podem ficar vazios; números opcionais podem ser nulos. Nomes e títulos não
precisam ser únicos. Uma edição deve informar a versão recebida na consulta;
versões antigas são recusadas com conflito, sem modificar a ficha.

As histórias abaixo usam as personas de [dominio.md](dominio.md). Os cenários
automatizados ficam em `apresentacao-backend/src/test/resources/features/<contexto>/`.

## Catálogo — Artur Antunes

### Oficinas (`oficinas`)

Como organizadora, quero manter fichas de oficinas para apresentar propostas de aprendizagem com informações claras.

| Campo | Regra |
|---|---|
| Título | Obrigatório. 1 a 100 caracteres. |
| Apresentação | Obrigatório. 1 a 2000 caracteres. |
| Modalidade | Obrigatório. Opções: Presencial, Online, Híbrida. |
| Duração em minutos | Obrigatório. Inteiro de 1 a 480. |

O estado inicial é **ATIVO**. Depois de arquivada, continua disponível para consulta. Para editar, restaure a ficha; os dados são mantidos.

### Manifestações de interesse (`interesses`)

Como organizadora, quero registrar interesses declarados para conhecer temas e formatos desejados, sem efetuar inscrições.

| Campo | Regra |
|---|---|
| Nome declarado | Obrigatório. 1 a 100 caracteres. |
| E-mail | Obrigatório. 1 a 254 caracteres. Formato de e-mail; não confirma existência nem exclusividade. |
| Tema desejado | Obrigatório. 1 a 150 caracteres. |
| Preferência de modalidade | Obrigatório. Opções: Presencial, Online, Híbrida, Sem preferência. |

O estado inicial é **ATIVO**. Depois de arquivada, continua disponível para consulta. Para editar, restaure a ficha; os dados são mantidos.

### Instrutores (`instrutores`)

Como organizadora, quero manter perfis informativos de instrutores para consultar suas especialidades e contatos.

| Campo | Regra |
|---|---|
| Nome | Obrigatório. 1 a 100 caracteres. |
| Apresentação | Obrigatório. 1 a 2000 caracteres. |
| Especialidade | Obrigatório. 1 a 150 caracteres. |
| Contato profissional | Opcional. 0 a 254 caracteres. |

O estado inicial é **ATIVO**. Depois de arquivada, continua disponível para consulta. Para editar, restaure a ficha; os dados são mantidos.

### Espaços (`espacos`)

Como organizadora, quero manter fichas de espaços para consultar localização e capacidade informativa.

| Campo | Regra |
|---|---|
| Nome | Obrigatório. 1 a 100 caracteres. |
| Localização | Obrigatório. 1 a 200 caracteres. |
| Descrição | Opcional. 0 a 2000 caracteres. |
| Capacidade informativa | Opcional. Inteiro de 1 a 1000. |

O estado inicial é **ATIVO**. Depois de arquivada, continua disponível para consulta. Para editar, restaure a ficha; os dados são mantidos.
