package com.example.functional_programming;

import com.example.functional_programming.start.with.StartsWithFunc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FunctionalProgrammingApplication {

	public static void main(String[] args) {
		SpringApplication.run(FunctionalProgrammingApplication.class, args);

		StartsWithFunc startsWithFunc = new StartsWithFunc();
	}

}
