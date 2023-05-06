package edu.birzeit.studystar.Model;

public class Item {

    private  int id;
    private String name;
    private int image;
    private  String category;

    public Item(int id, String name, int image, String category) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
