package uk.co.craftsmanspirit.kata.waterdispenser.service;

import lombok.NonNull;
import uk.co.craftsmanspirit.kata.waterdispenser.dto.Cup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CostOptimisingCupsProviderDecorator implements CupsProvider {

    private CupsProvider provider;

    public CostOptimisingCupsProviderDecorator(@NonNull CupsProvider provider) {
        this.provider = provider;
    }

    private List<Cup> optimise(List<Cup> cupsCollection) {
        List<Cup> sortedCups = sortCupByCapacity(cupsCollection);
        Cup biggestCup = sortedCups.get(0);

        List<Cup> optimisedCups = putSmallerCupsIntoBiggestCupIfItHasCapacity(sortedCups, biggestCup);

        return sortCupByCapacity(optimisedCups);
    }

    private List<Cup> putSmallerCupsIntoBiggestCupIfItHasCapacity(List<Cup> sortedCups, Cup biggestCup) {
        List<Cup> optimisedCups = new ArrayList<>();
        for (Cup nextCup: getListOfSmallerCups(sortedCups)){
            if (biggestCupHasCapacityToContainNextCup(biggestCup, nextCup)){
                putNextCupIntoBiggestCup(biggestCup, nextCup);
            } else {
                optimisedCups.add(nextCup);
            }
        }
        optimisedCups.add(biggestCup);
        return optimisedCups;
    }

    private void putNextCupIntoBiggestCup(Cup biggestCup, Cup nextCup) {
        biggestCup.setAmount(biggestCup.getAmount() + nextCup.getAmount());
    }

    private boolean biggestCupHasCapacityToContainNextCup(Cup biggestCup, Cup nextCup) {
        return (biggestCup.getAmount() + nextCup.getAmount()) <= biggestCup.getCapacity();
    }

    private List<Cup> sortCupByCapacity(List<Cup> cupsCollection) {
        return cupsCollection.stream()
                    .sorted(Comparator.comparing(Cup::getCapacity).reversed())
                    .collect(Collectors.toList());
    }

    private List<Cup> getListOfSmallerCups(List<Cup> sortedCups){
        return sortedCups.subList(1,sortedCups.size());
    }

    @Override
    public List<Cup> getCups(int requestedLitres) {
        return optimise(provider.getCups(requestedLitres));
    }
}
