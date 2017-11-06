package uk.co.craftsmanspirit.kata.waterdispenser.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import uk.co.craftsmanspirit.kata.waterdispenser.dto.Cup;
import uk.co.craftsmanspirit.kata.waterdispenser.dto.WaterResponse;
import uk.co.craftsmanspirit.kata.waterdispenser.request.RequestValidator;
import uk.co.craftsmanspirit.kata.waterdispenser.service.BasicCupsProvider;
import uk.co.craftsmanspirit.kata.waterdispenser.service.CostOptimisingCupsProviderDecorator;
import uk.co.craftsmanspirit.kata.waterdispenser.service.CupsProvider;

import java.util.List;

public class WaterDispenser {
    private static Logger LOG = Logger.getLogger(WaterDispenser.class);
    private static ObjectMapper MAPPER = new ObjectMapper();

    private RequestValidator requestValidator;
    private CupsProvider cupsProvider;

    public WaterDispenser() {
        this.requestValidator = new RequestValidator();
        this.cupsProvider = new CostOptimisingCupsProviderDecorator(new BasicCupsProvider(requestValidator));
    }

    public String handleWaterRequest(int requestedLitres) {
        try {
            requestValidator.validate(requestedLitres);
            List<Cup> cupsCollection = cupsProvider.getCups(requestedLitres);
            return MAPPER.writeValueAsString(new WaterResponse(cupsCollection));
        } catch (Exception e) {
            LOG.error(e);
            return e.getLocalizedMessage();
        }
    }
}
