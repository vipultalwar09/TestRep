package TestRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions (
		
		features = "C:\\Users\\Vipul\\eclipse-workspace\\BDD\\src\\main\\java\\Feature\\App.feature",
		glue = {"StepDefinition"},
		plugin = {"pretty", "html:test-output"},
		dryRun = false,
		monochrome = true,
		strict = true,
		tags = {"@third"}
		
		)

	
public class Runner {

}
