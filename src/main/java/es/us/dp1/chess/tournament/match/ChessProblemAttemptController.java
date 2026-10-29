package es.us.dp1.chess.tournament.match;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import es.us.dp1.chess.tournament.user.UserService;
import java.util.Optional;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import es.us.dp1.chess.tournament.exceptions.ResourceNotFoundException;


@RestController 
@RequestMapping("/api/v1/attempts")
public class ChessProblemAttemptController {

    UserService userService;
    ChessProblemAttemptService matchService;

    @Autowired 
    public ChessProblemAttemptController(ChessProblemAttemptService service, UserService userService){
        this.matchService=service;
        this.userService=userService;    
    }

    @GetMapping 
    public ResponseEntity<List<ChessProblemAttempt>> getAll() {
        return new ResponseEntity<>(matchService.getAttempts(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ChessProblemAttempt> getById(@PathVariable Integer id) {
        ChessProblemAttempt match = matchService.getMatchById(id).orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " + id));
        return new ResponseEntity<>(match, HttpStatus.OK);
    }
}