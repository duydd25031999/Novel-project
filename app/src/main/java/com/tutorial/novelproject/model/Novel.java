package com.tutorial.novelproject.model;

public class Novel {
    private int id;
    private int imageId;
    private String name;

    public Novel() {
    }

    public Novel(int imageId, String name) {
        this.imageId = imageId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
