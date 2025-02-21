package com.InventoryManager.Model;

public class Product {
    private String id;
    private String name;
    private String serialNumber;
    private String assigmentName;
    private String location;
    private String status;
    private String comments;
    public Product(String id, String name, String serialNumber, String assigmentName, String location, String status, String comments) {
        this.id = id;
        this.name = name;
        this.serialNumber = serialNumber;
        this.assigmentName = assigmentName;
        this.location = location;
        this.status = status;
        this.comments = comments;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSerialNumber() { return serialNumber; }
    public void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber; }

    public String getAssignmentName() { return assigmentName; }
    public void setAssignmentName(String assignmentName) { this.assigmentName = assignmentName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    //Add class diagram
    public String toString() {
        return "Product{id='" + id + "', name='" + name + "', serialNumber='" + serialNumber + "', assigmentName='" + assigmentName + "', location='" + location + "', status='" + status + "', comments='" + comments + "'}";
    }
}
