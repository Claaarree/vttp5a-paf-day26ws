package vttp5a_paf.day26ws.model;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Game {
    private int gid;
    private String name;
    private int year;
    private int ranking;
    private int average;
    private int users_rated;
    private String url;
    private String thumbnail;

    public int getGid() {
        return gid;
    }
    public void setGid(int gid) {
        this.gid = gid;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getRanking() {
        return ranking;
    }
    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    public int getAverage() {
        return average;
    }
    public void setAverage(int average) {
        this.average = average;
    }
    public int getUsers_rated() {
        return users_rated;
    }
    public void setUsers_rated(int users_rated) {
        this.users_rated = users_rated;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getThumbnail() {
        return thumbnail;
    }
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public static JsonObject toGameJson(Document doc) {
        JsonObject jObject = Json.createObjectBuilder()
                .add("game_id", doc.getInteger("gid"))
                .add("name", doc.getString("name"))
                .add("ranking", doc.getInteger("ranking"))
                .build();

        return jObject;               
    }
}
