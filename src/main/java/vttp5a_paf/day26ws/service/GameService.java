package vttp5a_paf.day26ws.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp5a_paf.day26ws.model.Game;
import vttp5a_paf.day26ws.repository.GameRepository;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public JsonObject getGames(int limit, int offset) {
        List<Document> games = gameRepository.getGames(limit, offset);

        JsonArrayBuilder gamesArrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < games.size(); i++){
            JsonObject jObject = Game.toGameJson(games.get(i));
            gamesArrayBuilder.add(jObject);
        }

        JsonObject result = Json.createObjectBuilder()
                .add("games", gamesArrayBuilder.build())
                .add("offset", offset)
                .add("limit", limit)
                .add("total", gameRepository.getTotalGames())
                .add("timestamp", Timestamp.valueOf(LocalDateTime.now()).toString())
                .build();

        return result;
    }

    public JsonObject getGamesByRank(int limit, int offset) {
        List<Document> games = gameRepository.getGamesByRank(limit, offset);

        JsonArrayBuilder gamesArrayBuilder = Json.createArrayBuilder();
        for (int i = 0; i < games.size(); i++){
            JsonObject jObject = Game.toGameJson(games.get(i));
            gamesArrayBuilder.add(jObject);
        }

        JsonObject result = Json.createObjectBuilder()
                .add("games", gamesArrayBuilder.build())
                .add("offset", offset)
                .add("limit", limit)
                .add("total", gameRepository.getTotalGames())
                .add("timestamp", Timestamp.valueOf(LocalDateTime.now()).toString())
                .build();

        return result;
    }
    
}
