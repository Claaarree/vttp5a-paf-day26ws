package vttp5a_paf.day26ws.service;

import java.io.StringReader;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
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

    public JsonObject getGameDetails(int gameId) {
        Optional<Document> opt = gameRepository.getGameDetails(gameId);
        JsonObject jObject = null;

        if (opt.isPresent()) {
            Document doc = opt.get();
            String thumbnail = doc.getString("image");
            ObjectId oid = doc.getObjectId("_id");
            doc.remove("_id");
            doc.remove("image");
            doc.append("thumbnail", thumbnail)
                .append("timestamp", Timestamp.valueOf(LocalDateTime.now()).toString())
                .append("game_id", oid.toHexString());
            jObject = Json.createReader(new StringReader(doc.toJson()))
                    .readObject();

            // below can order of the variables... above cannot...
            // JsonObject game = Json.createObjectBuilder()
            //         .add("game_id", doc.getObjectId("_id").toHexString())
            //         .add("name", doc.getString("name"))
            //         .add("year", doc.getInteger("year"))
            //         .add("ranking", doc.getInteger("year"))
            //         .add("url", doc.getString("url"))
            //         .add("thumbnail", doc.getString("doc"))
            //         .build();
        } else {
            jObject = Json.createObjectBuilder()
                    .add("error", "The provided game ID doesn't exist!")
                    .build();
        }

        return jObject;
    }
    
}
