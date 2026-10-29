package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

public interface ChessProblemRepository extends org.springframework.data.repository.CrudRepository<ChessProblem, Integer> {

    
    @Query ("SELECT p FROM ChessProblem p WHERE p.difficultyLevel >= :difficultyLevel AND SIZE(p.board.pieces) < :numPieces")
List<ChessProblem> findByDifficultyAndNumPieces(Integer difficultyLevel, Integer numPieces);

    List<ChessProblem> findAll();

    Optional<ChessProblem> findById(Integer i);

    void delete(ChessProblem piece);
    
}
