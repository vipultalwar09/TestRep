Feature: My Store Application

	@first
	Scenario Outline: Login to the Application
	
		Given User opens the My Store Application into the browser
		When User after verifying logo and title clicks on the Login Button
		Then User is redirected to Login Page
		Then User enters "<user>" and "<password>"
		Then User clicks on the Sign In button
		Then User lands on the My Account Page
		
	Examples:
		|user |password |
		|patlibalondi@gmail.com | test@123 |
		
	@second	
	Scenario Outline: CheckOut from the Application
	
		Given User opens the My Store Application into the browser
		When User after verifying logo and title clicks on the Login Button
		Then User enters "<user>" and "<password>"
		Then User clicks on the Sign In button
		Then User is on the Accounts Page
		And User clicks on Dresses button
		And Chooses a dress to buy
		Then User clicks on Add To Cart Button
		Then Proceed for CheckOut
		
	Examples:
		|user |password |
		|patlibalondi@gmail.com | test@123 |
		
	@third
	Scenario Outline: Summary Page
	
	Given User opens the My Store Application into the browser
		When User after verifying logo and title clicks on the Login Button
		Then User enters "<user>" and "<password>"
		Then User clicks on the Sign In button
		Then User is on the Accounts Page
		And User clicks on Dresses button
		And Chooses a dress to buy
		Then User clicks on Add To Cart Button
		Then Proceed for CheckOut
		And Lands on the Summary Page
		And Moves forward to the payments page
		
	Examples:
		|user |password |
		|patlibalondi@gmail.com | test@123 |
	
	