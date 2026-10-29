package es.us.dp1.chess.tournament.match;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import es.us.dp1.chess.tournament.model.BaseEntity;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Max;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity 
@Getter
@Setter
public class ChessProblemSolution extends BaseEntity {
    @NotNull 
    @NotBlank 
    @Size(min = 3, max = 50)
    String name;

    @NotNull 
    LocalDateTime createdAt;

    @NotNull 
    boolean minimal;

    @Size(max = 400)
    String notes;
    
    @Min(1)
    @Max(15)
    Integer expectedMoveCount;
    
    @NotNull
    @ManyToOne
    ChessProblem problem;

    @NotNull 
    @OneToOne
    ChessBoard solutionBoard;
}
