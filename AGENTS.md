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

## Guia de design

- A interface usa a direção **Cyberpunk Neon**: fundo escuro com textura de grade, ciano e magenta como acentos, e amarelo ácido reservado para estados de vitória.
- Preserve os tokens e estilos temáticos definidos em `socops/src/main/resources/static/css/app.css`; evite introduzir dependências, fontes externas ou uma nova biblioteca de UI.
- Mantenha o lobby, o tabuleiro 5×5 e o modal de vitória visualmente consistentes, com estados claros para célula disponível, selecionada, espaço livre e linha vencedora.
- Preserve foco visível via teclado, rótulos ARIA, contraste legível e suporte a telas estreitas. Respeite `prefers-reduced-motion` ao adicionar animações.
- Alterações no frontend devem manter os IDs `lobbyView`, `activeView`, `gridContainer`, `bingoBanner` e `victoryOverlay`, os handlers globais existentes e a chave de persistência `socops-bingo-snapshot`.
