@HSBC
Feature: To verify API automation with rest assured

#@Register
#Scenario: To verify rest service for register user endpoint - POST method
	#Given I want to prepare data for create account
	#When I submit the POST request for create single user
	#Then I should get 200 success status code along with response body
	
@RatesAPI
Scenario Outline: To validate status code for foreign currency exchange rates api
	Given Rates API for Latest Foreign Exchange rates "<baseURI>" with "<basePath>" 
	When The API is available
	Then To validate the response code <status_code>
	
	Examples:
		|	test_case	 | 		 baseURI			|basePath   	| status_code |
		|with latest api |http://api.ratesapi.io/ 	|api/latest		|	200		  |
		|with date api	 |http://api.ratesapi.io/   |api/2010-01-12	|	200		  |

@DataValidationforLatest
Scenario Outline: To validate response data for latest foreign exchange rates
	Given Rates API for Latest Foreign Exchange rates "http://api.ratesapi.io/" with "api/latest" 
	When The API is available
	Then To validate the response code 200
	And  To validate response data should be"<country-name>" and "<country-rates>"
Examples:
			|country-name| country-rates|
			|  	ind  	 |	 89.7945    |
			|	GBP		 |	 0.907		|		
			|	HKD		 |	 9.4321		|
			|	IDR		 |  17351.03	|


@DataValidationforSpecificDate
Scenario Outline: To validate response data for specific date foreign exchange rates
	Given Rates API for Latest Foreign Exchange rates "http://api.ratesapi.io/" with "api/2020-12-22" 
	When The API is available
	Then To validate the response code 200
	And  To validate response data should be"<country-name>" and "<country-rates>"
Examples:
			|country-name| country-rates|
			|  	ind  	 |	 90.1338    |
			|	GBP		 |	 0.9161		|		
			|	HKD		 |	 9.4378		|
			|	IDR		 |  17351.52	|
			
@DateValidation
Scenario: To validate status code for foreign currency exchange rates api
	Given Rates API for Latest Foreign Exchange rates "http://api.ratesapi.io/" with "api/2022-01-12" 
	When The API is available
	Then To validate the response code 200
	And  To validate date should be current date
						