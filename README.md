# currency-conversion-api
This API will convert the currency rate of a given currency to its destination currency


Developing
To develop this application, you will need Spring-Boot Hibernate and Java 11.

You can run this project locally using the Spring Boot Maven Plugin. When running locally, the application will make use of a local database. Other developer-specific functionality is provided by the Spring Boot Developer Tools.

Running the application
To run the application, execute the following Maven goal:

On Windows, make sure to point to the correct file based on the version number:

mvn clean package && java -jar target\currency-conversion-api-1.0.0-SNAPSHOT.war

On Mac or Linux:

mvn spring-boot:run
This will start the application on your local machine on port 8080 using an in-memory database..

#Application Contact-Service Endpoints
POST - http://localhost:9090/currency-conversion/convert

#Request Payload
POST: { "srcCurrency": "INR", "destCurrency": "GBP", "amount": 100}

#Response Payload
{ "currency": "GBP", "convertAmount": "85.0"}

#Database configs:
spring.datasource.url=jdbc:mysql://localhost:3306/mydb