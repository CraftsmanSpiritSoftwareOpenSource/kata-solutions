package uk.co.craftsmanspirit.kata.waterdispenser.dto;

import lombok.Getter;

import java.util.List;

public class WaterResponse {
    @Getter
    private int totalCost;
    @Getter
    private List<Cup> cupsCollection;

    public WaterResponse(){
    }

    public WaterResponse(List<Cup> cupsCollection){
        totalCost = cupsCollection.stream().mapToInt(o->o.getCost()).sum();
        this.cupsCollection = cupsCollection;
    }
}
