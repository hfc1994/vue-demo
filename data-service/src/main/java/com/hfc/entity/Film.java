package com.hfc.entity;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 */
public class Film
{
    private int id = 0;
    private String oid = "";
    private String title = "";
    private String year = "";
    private String type = "";
    private double star = 0;
    private String directors = "";
    private String actors = "";
    private int commenter = 0;
    private String duration = "";
    private String url = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public int getCommenter() {
        return commenter;
    }

    public void setCommenter(int commenter) {
        this.commenter = commenter;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String getContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id).append(this.oid).append(this.title)
                .append(this.year).append(this.type).append(this.star)
                .append(this.directors).append(this.actors)
                .append(this.commenter).append(this.duration).append(this.url);
        return sb.toString();
    }

    @Override
    public int hashCode() {
      return this.getContent().hashCode();
    }

    @Override
    public String toString() {
        return this.getContent();
    }
}
