package uk.co.craftsmanspirit.checkoutkata.rules;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import uk.co.craftsmanspirit.checkoutkata.rules.definition.CategoryMatcher;
import uk.co.craftsmanspirit.checkoutkata.scan.Item;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CategoryMatcher.class, name = "CategoryMatcher")
})
public interface ItemMatcher {
    List<Item> match(List<Item> itemList);
}
