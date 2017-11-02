# springBootAndXamarin

This is a sample template for Xamarin and spring boot where they can interact with each other

### How do I get set up?

* **Clone The project**: 

	git clone https://github.com/kasra21/springBootAndXamarin.git

* **Details**:

In the `/dbScript` there are any mysql database script to run on a new or out of date database (unfinished)

In the `/SpringBootTemplate` there is the spring boot piece

In the `/XamarinTemplate` There i the Xamarin and mobile native code

* **Build/Deploy**:

For the spring boot go to `/SpringBootTemplate` and execute:

	 mvn clean package
	 
This will generate your binary file which you may run:

	java -jar target/spring-boot-demo-1.0.jar
	
Try to avoid directly running it with `mvn spring-boot:run` which may cause problems for shutting down the service.
Then assuming that the database is already set up you may access the app from:

	http://localhost:8080/
	
Or by making rest requests or by using postman or equivalent software
	
The `XamarinTemplate` can be built on visual studio (Only tested on Android so far)
However keep in mind that it is trying to access a given IP address for the moment, that machine needs to be running with the `SpringBootTemplate` piece and also with the database