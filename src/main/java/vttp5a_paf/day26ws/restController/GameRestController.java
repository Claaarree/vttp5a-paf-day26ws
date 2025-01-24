package vttp5a_paf.day26ws.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.JsonObject;
import vttp5a_paf.day26ws.service.GameService;

@RestController
@RequestMapping("/api")
public class GameRestController {

    @Autowired
    private GameService gameService;
    
    @GetMapping(path = "/games", produces = "application/json")
    public ResponseEntity<String> getGames(@RequestParam (defaultValue = "10") int limit, 
    @RequestParam (defaultValue = "0") int offset) {
        JsonObject results = gameService.getGames(limit, offset);

        return ResponseEntity.ok().body(results.toString());
    }

    @GetMapping(path = "/games/rank", produces = "application/json")
    public ResponseEntity<String> getGamesByRank(@RequestParam (defaultValue = "10") int limit, 
    @RequestParam (defaultValue = "0") int offset) {
        JsonObject results = gameService.getGamesByRank(limit, offset);

        return ResponseEntity.ok().body(results.toString());
    }

    @GetMapping(path = "/game/{gameId}", produces = "application/json")
    public ResponseEntity<String> getGameDetails(@PathVariable int gameId) {
        JsonObject jObject = gameService.getGameDetails(gameId);

        return ResponseEntity.ok().body(jObject.toString());
    }
}
