package com.Albkali.FirebaseRecyclerView.Model;



public class GroceryItem {


    private String name;
    private String addedByUser;
    private String id;
    private boolean completed;


    public GroceryItem() {

    }
    public GroceryItem(String name, String addedByUser, boolean completed) {
        this.name = name;
        this.addedByUser = addedByUser;
        this.completed = completed;
        this.id = "";
    }
    public String getId(){
        return id;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddedByUser() {
        return addedByUser;
    }
    public void setAddedByUser(String addedByUser) {
        this.addedByUser = addedByUser;

    }
    public boolean getCompleted() {

        return completed; }
    public void setCompleted(boolean completed) {
        this.completed = completed;

    }
}
