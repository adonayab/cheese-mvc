package org.launchcode.cheesemvs.models;

public class Cheese {

    private int id;
    private String cheeseName;
    private String cheeseDescription;
    private static int nextId = 1;


    public Cheese() {
        id = nextId;
        nextId++;
    }
    public Cheese(String cheeseName, String cheeseDescription) {
        this();
        this.cheeseName = cheeseName;
        this.cheeseDescription = cheeseDescription;
    }

    public int getId() {
        return id;
    }

    public String getCheeseName() {
        return cheeseName;
    }
    public void setCheeseName(String cheeseName){
        this.cheeseName = cheeseName;
    }

    public String getCheeseDescription() {
        return  cheeseDescription;
    }
    public void setCheeseDescription(String cheeseDescription) {
        this.cheeseDescription = cheeseDescription;
    }


}
