package uk.co.craftsmanspirit.checkoutkata.rules.definition;

import lombok.NonNull;
import lombok.Setter;
import uk.co.craftsmanspirit.checkoutkata.rules.ItemMatcher;
import uk.co.craftsmanspirit.checkoutkata.scan.Item;
import uk.co.craftsmanspirit.checkoutkata.scan.ItemCategory;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryMatcher implements ItemMatcher {
    @Setter
    private ItemCategory category;

    public CategoryMatcher(){
    }

    @Override
    public List<Item> match(@NonNull List<Item> itemList) {
        return itemList.stream()
                .filter(item -> item.getCategory() == category)
                .collect(Collectors.toList());
    }
}
