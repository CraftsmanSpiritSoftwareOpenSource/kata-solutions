package uk.co.craftsmanspirit.kata.waterdispenser.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cup {
    private int capacity;
    private int amount;
    private int cost;
}
