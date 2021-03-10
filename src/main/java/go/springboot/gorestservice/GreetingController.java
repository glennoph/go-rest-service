package go.springboot.gorestservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@PropertySource("classpath:data.properties")
@RestController
public class GreetingController {

    private static final String template = "Hello, %s";
    private final AtomicLong counter = new AtomicLong();

    // data.properties data.name
    @Value("${data.name:defaultname}")
    private  String dataName ;

    // data.properties data.id
    @Value("${data.id:defaultid}")
    private  String dataId ;

    @GetMapping("/greeting")
    public Greeting greeting(
            @RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name ));
    }

    @GetMapping("/greetingid")
    public Greeting greetingId(
            @RequestParam(value = "name", defaultValue = "World") String name) {
        // return greeting with name, dataName, and dataId
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name + " dataName: " + dataName + " dataId: " + dataId));
    }


}
