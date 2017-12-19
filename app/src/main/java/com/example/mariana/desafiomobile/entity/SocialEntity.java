package com.example.mariana.desafiomobile.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SocialEntity implements Serializable{
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("site")
    @Expose
    private String site;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage () {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getSite() {
        return site;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSite(String site) {
        this.site = site;
    }
}