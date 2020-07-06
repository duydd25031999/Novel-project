package com.tutorial.novelproject.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NovelDetail {
    private String title;
    private String imageUrl;
    private ArrayList<String> genres;
    private String author;
    private String artist;
    private ArrayList<Volume> volumes;

    public NovelDetail() {
        genres = new ArrayList<String>();
        volumes = new ArrayList<Volume>();
    }

    public NovelDetail(String title, String imageUrl, ArrayList<String> genres, String author, String artist, ArrayList<Volume> volumes) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.genres = genres;
        this.author = author;
        this.artist = artist;
        this.volumes = volumes;
    }

    public static NovelDetail createFromJson(JSONObject json) throws JSONException {
        NovelDetail novelDetail = new NovelDetail();
        novelDetail.title = json.getString("title");
        novelDetail.imageUrl = json.getString("cover");
        novelDetail.getGenresFromJson(json.getJSONArray("genres"));
        novelDetail.author = json.getString("author");
        novelDetail.artist = json.getString("artist");
        novelDetail.getVolumesFromJson(json.getJSONArray("volumes"));

        return novelDetail;
    }

    private void getGenresFromJson(JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            genres.add(jsonArray.getString(i));
        }
    }

    private void getVolumesFromJson(JSONArray jsonArray) throws JSONException {
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Volume volume = Volume.createFromJson(jsonObject);
            volumes.add(volume);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public ArrayList<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(ArrayList<Volume> volumes) {
        this.volumes = volumes;
    }
}
