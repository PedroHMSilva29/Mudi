package br.com.pehenmo.mvc.mudi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class MudiApplication {

	public static void main(String[] args) { SpringApplication.run(MudiApplication.class, args); }
}
