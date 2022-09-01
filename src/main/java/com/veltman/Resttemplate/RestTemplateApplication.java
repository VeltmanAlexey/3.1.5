package com.veltman.Resttemplate;

import com.veltman.Resttemplate.Config.Config;
import com.veltman.Resttemplate.Model.User;
import com.veltman.Resttemplate.RestAPI.Communication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class RestTemplateApplication {
	public static void main(String[] args) {


		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

		Communication communication = context.getBean("communication", Communication.class);

		communication.saveUser(new User(3L,"James", "Brown", (byte) 19));
		communication.updateUser(new User(3L,"Thomas", "Shelby", (byte) 19));
		communication.deleteUser(3L);
	}
}
