package com.socops.data;

import java.util.List;

/**
 * Catálogo central de todos os prompts do bingo.
 * Há exatamente 24 entradas, uma a menos que a grade de 25 células,
 * porque a célula central é sempre o espaço livre.
 */
public final class IcebreakerPrompts {

    public static final String FREE_CELL_LABEL = "ESPAÇO LIVRE";

    public static final List<String> ALL_PROMPTS = List.of(
            "usa tecnologia todos os dias",
            "já ajudou alguém com um problema técnico",
            "tem um app que não vive sem",
            "aprende melhor com vídeos e tutoriais",
            "já montou ou configurou um computador",
            "tem um gadget favorito da infância",
            "já ensinou alguém a usar uma ferramenta digital",
            "quer aprender uma nova tecnologia este ano",
            "já teve que improvisar sem internet ou energia",
            "automatiza tarefas repetitivas da rotina",
            "já usou IA para facilitar trabalho ou estudo",
            "conhece atalhos úteis de teclado",
            "já experimentou um software e acabou usando por meses",
            "prefere ferramentas simples e práticas",
            "tem um projeto pessoal ligado à tecnologia",
            "gosta de testar novas ferramentas digitais",
            "já explicou tecnologia para alguém iniciante",
            "tem uma stack favorita para trabalho ou estudo",
            "já criou algo digital que ficou orgulhoso",
            "quer dominar uma tecnologia em breve",
            "usa mais produtividade ou mais criatividade no dia a dia",
            "já teve que resolver um problema sem tutorial",
            "gosta de acompanhar inovações de tech",
            "já participou de comunidade, grupo ou evento tech"
    );

    private IcebreakerPrompts() {
        /* catalogue only — no instances */
    }
}
