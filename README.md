# Alten Vehicle Monitoring

## Scenario:
Imagine you are an Alten consultant and you got assigned to a project at one of our top partners.
They have a number of connected vehicles that belongs to a number of customers.
They have a need to be able to view the status of the connection among these vehicles on a monitoring display.

The vehicles send the status of the connection one time per minute.
The status can be compared with a ping (network trace); no request from the vehicle means no connection.

## Task:
Your task will be to create a data store that keeps these vehicles with their status and the customers who own them, as well as a GUI (preferably web-based) that displays the status.
How the GUI is designed is up to you, as well as how you chose to implement the features and use suitable technologies.

Obviously, for this task, there are no real vehicles available that can respond to your "ping" request.
This can either be solved by using static values or ​​by creating a separate machinery that returns random fake status.

## Requirements
1. Web GUI (Single Page Application Framework/Platform).
 - An overview of all vehicles should be visible on one page (full-screen display), together with their status.
 - It should be able to filter, to only show vehicles for a specific customer.
 - It should be able to filter, to only show vehicles that have a specific status.
2. Random simulation to vehicles status sending.
3. If database design will consume a lot of time, use data in-memory representation.
4. Unit Testing.
5. .NET Core, Java or any native language.
6. Complete analysis for the problem.
 - Full architectural sketch to solution.
 - Analysis behind the solution design, technologies,....
 - How the solution will make use of cloud.
 - Deployment steps.

## Optional Requirements
1. Write an integration test.
2. Write an automation test.
3. Use CI(Travis, Circle, TeamCity...) to verify your code (Static analysis,..) and tests.
4. Dockerize the whole solution.
5. Microservices architecture for driver, vehicle and FaaS APIs.
 - Use any Microservices Chassis Framework.
6. Explain if it is possible to be in Serverless architecture and how?

## Data:
Below you have all customers from the system; their addresses and the vehicles they own.


 Customer name: **Kalles Grustransporter AB**
 
 Customer address: **Cementvägen 8, 111 11 Södertälje**
 
 |  VIN (VehicleId)    |   Reg. nr.   | 
 | --- | --- |
 |  YS2R4X20005399401  |   ABC123       | 
 |  VLUR4X20009093588  |   DEF456       | 
 |  VLUR4X20009048066   |  GHI789       | 

 Customer name: **KJohans Bulk AB** 
 
 Customer address: **Balkvägen 12, 222 22 Stockholm**
 
 |  VIN (VehicleId)   |    Reg. nr.     | 
 | --- | --- |
 |  YS2R4X20005388011  |   JKL012       | 
 |  YS2R4X20005387949  |   MNO345       | 

 Customer name: **Haralds Värdetransporter AB**
 
 Customer address: **Budgetvägen 1, 333 33 Uppsala** 
 
 | VIN (VehicleId)   |    Reg. nr. | 
 | --- | --- |
 |  VLUR4X20009048066  |   PQR678       | 
 |  YS2R4X20005387055  |  STU901       | 


# Solution Architecture

## Architecture Selection

In our project the partner's business is highly dynamic. Also the business scaling is promising. So we recommed that 
the application will be cloud compliant. The selection of the archicture here will be a **Microservice Architecure** it
will be optimal for this business agility. The load on the application endpoints is not close. ex:ping service will be 
hitted more that the custoemr search so scalling this endpoint only will be cost saving compared to replicaing the whole application. 
Also the give the flexability to add new small and independant components to enrich the customer business.

![Alt text](images/architecture.png?raw=true "Micoservice architecture")

## Technologies

The related technologies to this *Reference Architecture* mentioned in the below diagram. 

![Alt text](images/architecture2.png?raw=true "Micoservice architecture technologies")


The mentioned technologies are used in the impelemantation of this project into the below modules.
- **eureka-service** : build over Eureka to keep registery with the service instances, Zuul to load balance (it is together discovery and gateway service )
- **customer-service** : Spring boot (web, Data) , h2-database, swagger . Customer store management.
- **vehicle-service** : Spring boot (web, Data) , h2-database, Swagger . Vehicle store management.
- **vehicle-signal-generator** : Spring boot (web) , util to demo the signal status.
- **UI-dashboard** : Will Use Angular but  Not implemented


## Deployment (Development env)

first of all you need to build the mentioned projects (each project has it's own pipeline):

1. eureka-service
   ```shell
   cd eureka-service
   mvn package
   ```
2. customer-service
   ```shell
   cd customer-service
   mvn package
   ```
3. vehicle-service
   ```shell
   cd vehicle-service
   mvn package
   ```
4. vehicle-signal-generator
   ```shell
   cd vehicle-signal-generator
   mvn package
   ```
   
 **now** got to the workspace root to run the whole environment locally using **docker-compose**
 ```shell
 docker-compose build
 docker-compose up
 ```
 or 
 
 ```shell
 docker-compose up --build
 ```
 
 Check up the components
 
  
 - **customer service** : check the swagger of the customer service  http://localhost:8301/customer/swagger-ui.html#
   ![Alt text](images/customer-service.png?raw=true "Customer's API swagger")
 - **vehicle service** : check the swagger of the customer service  http://localhost:8300/vehicle/swagger-ui.html#
   ![Alt text](images/vehicle-service.png?raw=true "Customer's API swagger")
 - **eureka-serice** : check the *Eureka* dashboard http://localhost:8302/
  ![Alt text](images/eureka-service.png?raw=true "Eureka dashboard ")
## Deployment (Production env)
 **Not implemented**


## CI/CD
 
**Not implemented**

  

 
 
 
 










