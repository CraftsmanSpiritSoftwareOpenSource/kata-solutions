package uk.co.craftsmanspirit.kata.waterdispenser.service;

import uk.co.craftsmanspirit.kata.waterdispenser.dto.Cup;
import uk.co.craftsmanspirit.kata.waterdispenser.request.RequestValidator;

import java.util.*;

public class BasicCupsProvider implements CupsProvider {

    private RequestValidator requestValidator;

    public BasicCupsProvider(RequestValidator requestValidator){
        this.requestValidator = requestValidator;
    }

    private Cup getNextCup(int requestedLitres, Deque<Cup> availableCups){
        Cup nextCup = availableCups.pop();
        if (nextCup.getCapacity() > requestedLitres){
            nextCup.setAmount(requestedLitres);
        } else {
            nextCup.setAmount(nextCup.getCapacity());
        }
        return nextCup;
    }

    private Deque<Cup> createAvailableCupsOrderedByCost(){
        Deque<Cup> availableCups = new ArrayDeque<>();
        availableCups.push(new Cup(5,0,8));
        availableCups.push(new Cup(4,0,4));
        availableCups.push(new Cup(3,0,2));
        availableCups.push(new Cup(2,0,1));
        return availableCups;
    }

    @Override
    public List<Cup> getCups(int requestedLitres) {
        requestValidator.validate(requestedLitres);

        List<Cup> requiredCups = new ArrayList<>();
        Deque<Cup> availableCups = createAvailableCupsOrderedByCost();
        int litresToDispense = requestedLitres;
        while (litresToDispense > 0){
            Cup nextCup = getNextCup(litresToDispense,availableCups);
            requiredCups.add(nextCup);
            litresToDispense -= nextCup.getAmount();
        }
        return requiredCups;
    }
}
