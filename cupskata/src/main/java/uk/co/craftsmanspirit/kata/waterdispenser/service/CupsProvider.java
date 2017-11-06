package uk.co.craftsmanspirit.kata.waterdispenser.service;

import uk.co.craftsmanspirit.kata.waterdispenser.dto.Cup;

import java.util.List;

@FunctionalInterface
public interface CupsProvider {
    List<Cup> getCups(int requestedLitres);
}
