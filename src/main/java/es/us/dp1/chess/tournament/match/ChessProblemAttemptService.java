package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.chess.tournament.exceptions.UnfeasibleAttemptException;
import es.us.dp1.chess.tournament.user.User;
import es.us.dp1.chess.tournament.user.UserService;

@Service
public class ChessProblemAttemptService {

    ChessProblemAttemptRepository repo;  
    UserService userService;  

    @Autowired
    public ChessProblemAttemptService(ChessProblemAttemptRepository repo) {
        this.repo = repo;        
    }

    @Transactional(readOnly = true)
    public Optional<ChessProblemAttempt> getMatchById(Integer id) {
        return repo.findById(id);
    }

    @Transactional(rollbackFor = UnfeasibleAttemptException.class)
    public ChessProblemAttempt save(ChessProblemAttempt m) {        
        User player  = m.getPlayer();
        List<ChessProblemAttempt> attemptsForPlayer = repo.findAll().stream().filter(
            a -> a.getPlayer().equals(player) && a.getFinish() == null
        ).toList();
        if(attemptsForPlayer.size() > 0) {
                  //throw new UnfeasibleAttemptException("There is another ongoing attempt");
        }
    
        if(m.getFinish() != null && m.getFinish().isBefore(m.getStart())) {
            // throw new UnfeasibleAttemptException("The start must be before the finish");
        }

        return repo.save(m);
    }

    @Transactional(readOnly = true)
    public List<ChessProblemAttempt> getAttempts() {
        return repo.findAll();
    }    

}
