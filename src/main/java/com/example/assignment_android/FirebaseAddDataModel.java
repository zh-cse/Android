package com.example.assignment_android;

public class FirebaseAddDataModel {
    private String id;
    private String name;
    private String desination;
    private String team;
    String image;

    public FirebaseAddDataModel(){

    }

    public FirebaseAddDataModel(String id, String name, String desination, String team,String image) {
        this.id = id;
        this.name = name;
        this.desination = desination;
        this.team = team;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesination() {
        return desination;
    }

    public void setDesination(String desination) {
        this.desination = desination;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
}
