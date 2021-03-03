# go-rest-service
spring boot rest service 

## background
see https://spring.io/guides/gs/rest-service/

### run the application manually
1. start application: GoRestServiceApplication with command
   
   `mvn spring-boot:run`
   
1. From a browser go to url: `http://localhost:8080/greeting`
1. Results in browser: 
```
{"id":1,"content":"Hello, World"}
```

### test with JUnit
see [GreetingControllerTest.java](src/test/java/go/springboot/gorestservice/GreetingControllerTest.java)

```java
    @Test
    void greeting() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/greeting")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":1,\"content\":\"Hello, World\"}")) // ok
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                //.andExpect(jsonPath("$", hasSize(0))) // fails
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("content", startsWith("Hello")))
                .andExpect(jsonPath("content", is("Hello, World")))
        ;
    }
```