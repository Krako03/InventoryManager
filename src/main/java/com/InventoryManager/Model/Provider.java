package com.InventoryManager.Model;

public class Provider {
    private int id;
    private String name;
    private String contactInfo;
    private String comments;

    public Provider(int id, String name, String contactInfo, String comments) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.comments = comments;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    //add class diagram
    public String toString() {
        return "Provider{id=" + id + ", name='" + name + "', contactInfo='" + contactInfo + "', comments='" + comments + "'}";
    }
}
