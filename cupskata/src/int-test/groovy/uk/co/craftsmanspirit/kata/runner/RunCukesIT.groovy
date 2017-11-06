package uk.co.craftsmanspirit.kata.runner

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/int-test/resources/features",
        glue = "src/int-test/groovy/uk/co/craftsmanspirit/kata/steps",
        format = "pretty")
class RunCukesIT {
}
