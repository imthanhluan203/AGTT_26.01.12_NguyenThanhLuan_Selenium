package Definition;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "Resource/Features",
        glue = "Definition",
        plugin = {"pretty"}
)
public class TestRunner {
}
