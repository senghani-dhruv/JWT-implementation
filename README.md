# JWT-implementation

Step 1 :- Create the Jwt service in spring boot
	
	-->create following data member
		private static String secretKey = "YUhSMGNITTZMeTkzWldKaGRHVXRhRzkzZEM1amIyMGlhWE1pTkdjdE9TNWpiMjA9"; // Correct Base64 encoded key 
	 
	 	private static long expireTime = 360000 * 6;

	-->create method for generate token using userdetails
	-->create method extractUsername using token
	-->create method extractAllClaims using token
	-->Create one more method which check Token is valid or not using token and UserDetails

Step 2 :- Create user entity class which implement UserDetails interface

Step 3 :- Create the user repository

Step 4 :- Create JWTConfiguration Class

Step 5 :- Create JWTAuthenticationEntryPoint which implements OncePerRequestFilter class

Step 6 :- Create SecurityConfiguration class

Step 7 :- Now create the controller and service for user
