package com.maltem.demo.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "title", "director", "releaseDate", "type" })
public class Movie {
    private  String title;

    private  String type;

    private  String releaseDate;

    private  String director;

    public Movie(String title, String director, String releaseDate ,String type) {
        this.title = title;
        this.type = type;
        this.releaseDate = releaseDate;
        this.director = director;
    }

    public String getTitle() {
        return this.title;
    }

    public String getType() {
        return this.type;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public String getDirector() {
        return this.director;
    }

    public void  setTitle(String title) {
        this.title=title;
    }

    public void setType(String type) {
        this.type=type;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate=releaseDate;
    }

    public void setDirector(String director) { this.director=director; }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();
        sb.append("\n{title: ").append(title);
        sb.append(",\ndirector: ").append(director);
        sb.append(",\nreleaseDate: ").append(releaseDate);
        sb.append(",\ntype: ").append(type);
        sb.append(",\n}, \n");

        return sb.toString();
    }

}
