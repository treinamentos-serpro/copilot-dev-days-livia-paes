package com.socops.service;

import com.socops.data.IcebreakerPrompts;
import com.socops.model.BingoCell;
import com.socops.model.WinningStreak;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Valida a montagem do tabuleiro, a marcação das células e a detecção
 * de vitória fornecidas por {@link BoardAssembler}.
 */
class BoardAssemblerTests {

    @Test
    @DisplayName("Os prompts do Tech Life Bingo estão em português")
    void techLifePromptsAreInPortuguese() {
        assertEquals(24, IcebreakerPrompts.ALL_PROMPTS.size(),
                "O tabuleiro precisa ter 24 prompts mais o espaço livre");
        assertTrue(IcebreakerPrompts.ALL_PROMPTS.stream()
                        .anyMatch(prompt -> prompt.toLowerCase().contains("tecnologia")),
                "Os prompts devem refletir o tema Tech Life Bingo");
        assertTrue(IcebreakerPrompts.ALL_PROMPTS.stream()
                        .noneMatch(prompt -> prompt.toLowerCase().contains("has ")
                                || prompt.toLowerCase().contains("loves ")
                                || prompt.toLowerCase().contains("plays ")
                                || prompt.toLowerCase().contains("prefers ")),
                "Os prompts devem estar em português e não em inglês");
    }

    /* ── criação do tabuleiro ─────────────────────────────────────── */

    @Test
    @DisplayName("O tabuleiro montado contém exatamente vinte e cinco células")
    void assembledBoardHasTwentyFiveCells() {
        List<BingoCell> generatedBoard = BoardAssembler.assembleNewBoard();
        assertEquals(25, generatedBoard.size(),
                "Um tabuleiro de bingo padrão deve ter 25 células");
    }

    @Test
    @DisplayName("A célula central (índice 12) é sempre o espaço livre e já vem marcada")
    void centerSlotIsAlwaysFreeCell() {
        List<BingoCell> generatedBoard = BoardAssembler.assembleNewBoard();
        BingoCell centreTile = generatedBoard.get(12);

        assertTrue(centreTile.freeCell(),  "A célula central deve ser marcada como livre");
        assertTrue(centreTile.selected(),  "A célula livre deve começar já marcada");
    }

    @Test
    @DisplayName("Todas as células que não são livres começam sem seleção")
    void nonFreeCellsStartUnselected() {
        List<BingoCell> generatedBoard = BoardAssembler.assembleNewBoard();
        for (BingoCell tile : generatedBoard) {
            if (!tile.freeCell()) {
                assertFalse(tile.selected(),
                        "A célula id=" + tile.id() + " deve começar desmarcada");
            }
        }
    }

    /* ── alternância de seleção ───────────────────────────────────── */

    @Test
    @DisplayName("Ao marcar uma célula comum, a seleção alterna entre ligada e desligada")
    void flippingCellTogglesSelection() {
        List<BingoCell> board = BoardAssembler.assembleNewBoard();
        int targetId = 0;

        List<BingoCell> afterFirstFlip = BoardAssembler.flipCell(board, targetId);
        assertTrue(afterFirstFlip.get(targetId).selected(),
                "A primeira marcação deve ativar a célula");

        List<BingoCell> afterSecondFlip = BoardAssembler.flipCell(afterFirstFlip, targetId);
        assertFalse(afterSecondFlip.get(targetId).selected(),
                "A segunda marcação deve desmarcar a célula");
    }

    @Test
    @DisplayName("Tentar marcar a célula livre não tem efeito")
    void flippingFreeCellHasNoEffect() {
        List<BingoCell> board = BoardAssembler.assembleNewBoard();
        int freeCellId = 12;

        List<BingoCell> afterAttemptedFlip = BoardAssembler.flipCell(board, freeCellId);
        assertTrue(afterAttemptedFlip.get(freeCellId).selected(),
                "A célula livre deve permanecer marcada mesmo ao tentar alternar");
        assertTrue(afterAttemptedFlip.get(freeCellId).freeCell(),
                "A flag da célula livre não deve mudar");
    }

    /* ── detecção de vitória ──────────────────────────────────────── */

    @Test
    @DisplayName("Completar a primeira linha inteira é detectado como vitória da linha")
    void completeRowDetectedAsVictory() {
        List<BingoCell> board = BoardAssembler.assembleNewBoard();

        // Seleciona todas as células da linha 0 (índices 0 a 4)
        for (int col = 0; col < 5; col++) {
            board = BoardAssembler.flipCell(board, col);
        }

        Optional<WinningStreak> result = BoardAssembler.detectWinningStreak(board);
        assertTrue(result.isPresent(), "Uma linha completa deve disparar vitória");
        assertEquals("row", result.get().direction(),
                "A direção detectada deve ser 'row'");
        assertEquals(0, result.get().index(),
                "O índice da linha vencedora deve ser 0");
    }

    @Test
    @DisplayName("Um tabuleiro recém montado não possui sequência vencedora")
    void incompleteBoardHasNoVictory() {
        List<BingoCell> freshBoard = BoardAssembler.assembleNewBoard();
        Optional<WinningStreak> result = BoardAssembler.detectWinningStreak(freshBoard);
        assertTrue(result.isEmpty(),
                "Não deve haver sequência vencedora em um tabuleiro novo");
    }

    @Test
    @DisplayName("collectWinningCellIds retorna o conjunto correto de posições")
    void winningCellIdsMatchStreak() {
        List<Integer> expectedPositions = List.of(0, 1, 2, 3, 4);
        WinningStreak fakeStreak = new WinningStreak("row", 0, expectedPositions);

        Set<Integer> collectedIds = BoardAssembler.collectWinningCellIds(fakeStreak);
        assertEquals(Set.of(0, 1, 2, 3, 4), collectedIds,
                "Os IDs coletados devem corresponder às posições da sequência vencedora");
    }
}
