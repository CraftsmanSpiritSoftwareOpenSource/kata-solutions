package uk.co.craftsmanspirit.checkoutkata.scan;

import lombok.Getter;
import lombok.Setter;

public class Item {
    @Getter @Setter
    private ItemCategory category;
    @Getter
    private String id;
    @Getter
    private Double price;
}
