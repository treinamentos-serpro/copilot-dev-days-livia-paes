# Soc Ops

## Checklist obrigatório

Antes de concluir qualquer mudança, execute:

1. `git diff --check` (lint)
2. `cd socops && ./mvnw clean package` (build)
3. `cd socops && ./mvnw test` (testes)

Use sempre o Maven Wrapper de `socops`, não um Maven global. Não faça commit automaticamente.

## Arquitetura e convenções

- Spring Boot 3, Java 21, stateless e sem persistência.
- `BingoRestController` serve Thymeleaf e `/api/bingo/fresh-board`.
- Preserve `BoardAssembler` puro e os modelos imutáveis em `model/` como records.
- `IcebreakerPrompts` contém 24 prompts; o índice 12 é o espaço livre.
- Preserve `lobbyView` e `activeView` em `game.html`.
- Frontend usa Thymeleaf, JavaScript vanilla e CSS utilities próprio; evite novas dependências.
- Adicione testes JUnit 5 próximos à unidade alterada, com `@DisplayName` descritivo.
- Antes de editar frontend, consulte [CSS utilities](.github/instructions/css-utilities.instructions.md) e [frontend design](.github/instructions/frontend-design.instructions.md).

Consulte o [README](README.md) e o [guia do workshop](workshop/GUIDE.md) para detalhes. Não faça commits automáticos.
