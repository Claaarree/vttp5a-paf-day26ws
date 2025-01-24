package vttp5a_paf.day26ws.repository;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static vttp5a_paf.day26ws.utils.Constants.*;

import java.util.List;

@Repository
public class GameRepository {
    
    @Autowired
    private MongoTemplate template;

    // db.games.find({})
    //     .sort({name: 1})
    //     .limit(5)
    //     .skip(1)
    public List<Document> getGames(int limit, int offset) {
        
        Query query = new Query()
            .with(Sort.by(Direction.ASC, F_NAME))
            .limit(limit)
            .skip(offset);

        List<Document> games = template.find(query, Document.class, C_GAMES);
        
        return games;
    }

    // db.games.find({})
    //     .count()
    public long getTotalGames(){
        return template.getCollection(C_GAMES).countDocuments();
    }
}
