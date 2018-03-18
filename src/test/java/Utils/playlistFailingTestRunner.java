package Utils;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"stepDefs"},plugin = {"pretty"}, tags = {"~@playlist","~@ignore", "@failing"}, features = {"src/test/java/features"})
public class playlistFailingTestRunner {

}
