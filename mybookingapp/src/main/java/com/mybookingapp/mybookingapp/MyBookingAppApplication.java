package com.mybookingapp.mybookingapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class MyBookingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyBookingAppApplication.class, args);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Geben Sie Text ein: ");
		String command = scanner.nextLine().toLowerCase();
		System.out.println(command);
	}

}
