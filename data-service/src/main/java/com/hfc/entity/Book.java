package com.hfc.entity;

/**
 * Created by hfc on 2018/8/18.
 *
 * @author hfc.
 */
public class Book
{
    private int id = 0;
    private int oid = 0;
    private String title = "";
    private double scores = 0;
    private String prices = "";
    private String publishing = "";
    private String url = "";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getScores() {
        return scores;
    }

    public void setScores(double scores) {
        this.scores = scores;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }

    public String getPublishing() {
        return publishing;
    }

    public void setPublishing(String publishing) {
        this.publishing = publishing;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String getContent() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id).append(this.oid)
                .append(this.title).append(this.scores)
                .append(this.prices).append(this.publishing).append(this.url);
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
