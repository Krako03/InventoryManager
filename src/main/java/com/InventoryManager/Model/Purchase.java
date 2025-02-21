package com.InventoryManager.Model;

import java.io.File;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
}
