package co.socobox.ws.example;

import co.socobox.ws.codegen.HelloRequest;
import co.socobox.ws.codegen.HelloResponse;
import co.socobox.ws.codegen.SayHello;
import co.socobox.ws.codegen.SayHelloResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import javax.xml.bind.JAXBElement;

@SpringBootApplication
public class ExampleApplication {



	public static void main(String[] args) {
		SpringApplication.run(ExampleApplication.class, args).close();
	}


	@Bean
	CommandLineRunner lookup(HelloClient helloClient) {
		return args -> {
			String name = "Hans";

			// if we did get a runtime argunment use that one, otherwise use the default
			if(args.length>0){
				name = args[0];
			}

			var responseMessage =  helloClient.sayHello(name);

			System.out.println("Got Response As below ========= : ");
			System.out.println("Name : "+responseMessage);
		};
	}





}
