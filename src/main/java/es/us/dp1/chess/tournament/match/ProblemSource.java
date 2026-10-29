package es.us.dp1.chess.tournament.match;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Email;

import lombok.Getter;
import lombok.Setter;
import es.us.dp1.chess.tournament.model.BaseEntity;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;


@Entity 
@Getter
@Setter
public class ProblemSource extends BaseEntity {
    @NotNull
    @Size(min = 3, max = 50)
    String name;

    @NotNull
    @Size(min = 3, max = 100)
    String author;

    @Size(max=1000)
    String description;
    
    @Email
    String contactEmail;

    @ManyToMany
    @JoinTable(name = "problem_source_problems",
        joinColumns = @JoinColumn (name ="problem_source_id"),
        inverseJoinColumns = @JoinColumn(name = "problem_id")
    )
    List<ChessProblem> problems;
}
