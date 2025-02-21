package com.InventoryManager.Model;

import java.io.File;

public class Purchase {
    private String id;
    private File invoice;
    private boolean hasAppleCare;
    private String appleCareInvoice;
    private String comments;
    private String location;
    private Double priceMx;
    private Double priceUsa;
    private Boolean deliveryStatus;
    private String  provider;

    public Purchase(String id, File invoice, boolean hasAppleCare, String appleCareInvoice, String comments, String location, double priceMx, double priceUsa, boolean deliveryStatus, String provider) {
        this.id = id;
        this.invoice = invoice;
        this.hasAppleCare = hasAppleCare;
        this.appleCareInvoice = appleCareInvoice;
        this.comments = comments;
        this.location = location;
        this.priceMx = priceMx;
        this.priceUsa = priceUsa;
        this.deliveryStatus = deliveryStatus;
        this.provider = provider;
    }


    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public File getInvoice() { return invoice; }
    public void setInvoice(File invoice) { this.invoice = invoice; }

    public boolean isHasAppleCare() { return hasAppleCare; }
    public void setHasAppleCare(boolean hasAppleCare) { this.hasAppleCare = hasAppleCare; }

    public String getAppleCareInvoice() { return appleCareInvoice; }
    public void setAppleCareInvoice(String appleCareInvoice) { this.appleCareInvoice = appleCareInvoice; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public double getPriceMx() { return priceMx; }
    public void setPriceMx(double priceMx) { this.priceMx = priceMx; }

    public double getPriceUsa() { return priceUsa; }
    public void setPriceUsa(double priceUsa) { this.priceUsa = priceUsa; }

    public boolean isDeliveryStatus() { return deliveryStatus; }
    public void setDeliveryStatus(boolean deliveryStatus) { this.deliveryStatus = deliveryStatus; }

    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }

    public String toString() {
        return "Purchase{id='" + id + "', invoice=" + invoice + ", hasAppleCare=" + hasAppleCare + ", appleCareInvoice='" + appleCareInvoice + "', comments='" + comments + "', location='" + location + "', priceMx=" + priceMx + ", priceUsa=" + priceUsa + ", deliveryStatus=" + deliveryStatus + ", provider='" + provider + "'}";
    }

}
