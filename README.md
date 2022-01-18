# Weather API GraphQl
This is an application built with spring boot and related technologies to access openweathermap.org's 
RESTful APIs. After running the application you can call the "/graphql" end point from any graphql client, 
which will enable you to call the api using graphql queries and have the full CRUD functionality. 
This also has a caching mechanism built-in for subsequent requests that are asking for same data.

## Here are some sample queries

* Query to get all the weather reports
  ```
  query getAllReports{
    getAllReports{
        name,
        description,
        temperature,
        prettyDate
    }
  }
  ```
* Query to get a report by it's city id
  ```
  query getReportByCityId{
    colombo:getReportByCityId(cityId:1248991){
        name,
        description,
        temperature
    }
    tokyo:getReportByCityId(cityId:1850147){
        name,
        description,
        temperature
    }
  }
  
  ```
* Query to create a new record by city id
  ```
  mutation createNewReportById(cityId:1850147){   
      createNewReportById(cityId:1850147){
          name,
          description
      }
  }
  
  ```
* Query to create list of new records by city ids
  ```
  mutation createNewReportsByIds{   
      createNewReportsByIds(cityIds:[524901,703448]){
          name,
          description
      }
  }
  
  ```
* Query to create a new record
  ```
  mutation createNewReport($input:CityWeatherReportInput){   
      createNewReport(input:$input){
          name,
          description
      }
  }
  
  ```
* Query to update a record  
  ```
  mutation updateReport($input:CityWeatherReportInput){   
    updateReport(input:$input){
        name,
        description
    }
  }

  ```

* sample variable: 
  ```
     {
        "input":{
            "cityId":703448,
            "description":"sunny day",
            "temperature":102,
            "name":"No name"
        }
     }

  ```
* Query to update a record by city id
  ```
  mutation updateReportById{   
    updateReportById(cityId:703448){
        name,
        description
    }
  }

  ```
* Query to delete a record by city id
  ```
  mutation deleteReportById{   
    deleteReportById(cityId:703448)
  }
  
  ```
 
## Technologies used

* Spring Boot
* Spring GraphQL
* MySQL
* caffeine for caching
  
