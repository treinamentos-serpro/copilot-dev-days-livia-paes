package com.socops.model;

import com.socops.data.IcebreakerPrompts;

/**
 * Uma célula do tabuleiro de bingo 5×5.
 *
 * @param id        posição zero-based (0-24)
 * @param prompt    texto exibido na célula
 * @param selected  indica se o jogador marcou a célula
 * @param freeCell  true apenas para a célula central de espaço livre
 */
public record BingoCell(int id, String prompt, boolean selected, boolean freeCell) {

    /** Cria uma célula comum ainda não marcada. */
    public static BingoCell ofPrompt(int id, String prompt) {
        return new BingoCell(id, prompt, false, false);
    }

    /** Cria a célula central de espaço livre, já marcada desde o início. */
    public static BingoCell ofFreeCell(int id) {
        return new BingoCell(id, IcebreakerPrompts.FREE_CELL_LABEL, true, true);
    }
}
