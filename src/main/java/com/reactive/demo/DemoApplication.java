package com.reactive.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
@EnableWebFlux
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}

@Configuration
class AppRoutes {

	@Bean
	RouterFunction<ServerResponse> customerRoutes() {

		return route( GET("/customer"),
				req -> ok().body(getAllCustomers(),Customer.class))
				.andRoute(GET("/"),req->ok().body(Mono.just(new Customer("c7",UUID.randomUUID(),"address7")),Customer.class));

	}

	private static Flux<Customer> getAllCustomers() {
		List<Customer> customerList = new ArrayList<>();
		Customer customer = new Customer("c1",UUID.randomUUID(),"address1");
		customerList.add(customer);

		customer = new Customer("c2",UUID.randomUUID(),"address1");
		customerList.add(customer);

		customer = new Customer("c3",UUID.randomUUID(),"address1");
		customerList.add(customer);

		customer = new Customer("c4",UUID.randomUUID(),"address1");
		customerList.add(customer);

		customer = new Customer("c5",UUID.randomUUID(),"address1");
		customerList.add(customer);

		return Flux.fromIterable(customerList).delayElements(Duration.ofSeconds(5));
	}

}

record Customer(String name,UUID id,String address) {

}
