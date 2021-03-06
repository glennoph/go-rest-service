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

## swagger2
### swagger2 config
* maven dependencies

```xml
<openapi.version>1.5.6</openapi.version>

<dependency>
   <groupId>org.springdoc</groupId>
   <artifactId>springdoc-openapi-ui</artifactId>
   <version>${openapi.version}</version>
</dependency>
```

* application.properties

```properties
springdoc.swagger-ui.path=/swagger-ui-custom.html
springdoc.swagger-ui.operationsSorter=method
```

### swagger ui
http://localhost:8080/swagger-ui-custom.html

## Access Properties Values
* add file to resources: [data.properties](src/main/resources/data.properties)
* update the class that will access the properties [GreetingController.java](src/main/java/go/springboot/gorestservice/GreetingController.java)
```properties
@PropertySource("classpath:data.properties")
...
// data.properties data.name
@Value("${data.name:defaultname}")
private  String dataName ;

// data.properties data.id
@Value("${data.id:defaultid}")
private  String dataId ;
```
* the variables dataName and dataId will get the properties values or default values.

