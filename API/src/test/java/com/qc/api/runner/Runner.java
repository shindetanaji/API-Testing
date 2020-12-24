package com.qc.api.runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@CucumberOptions(features = "src/test/resources/", glue = "com/qc/api/steps", 
monochrome = true, tags = { "@HSBC" })
@RunWith(Cucumber.class)

public class Runner {

}
