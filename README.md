## weather-api

This is an application built with spring boot and related technologies to access openweathermap.org's RESTful APIs.

After running the application you can call the "/api/home" end point from the browser, which will load list of cities around the world and and their weather information. You can also enter city code and see the results of a particular city.

There is also another endpoint "api/{ids}" where you can supply list of city codes (api/524901,703448,2643743), from the browser address bar which will then result in list of city weather information that you can view in the browser.

This also has a caching mechanism bilt-in for subsequent requests that are asking for same data.

### Technologies used

- Spring Boot
- Spring MVC
- Thymeleaf for the UI
- Caffeine for caching
