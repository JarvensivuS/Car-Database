package com.samuli.project;

import java.awt.Desktop;
import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) throws Exception{
		
		//Open index.html on startup.  
		String URL = "src\\main\\resources\\templates\\index.html";
		File file = new File(URL);
		Desktop.getDesktop().browse(file.toURI());

		//Wait for Spring to initialize itself before interacting with the site.
		SpringApplication.run(MainApplication.class, args);
		
	}

}
