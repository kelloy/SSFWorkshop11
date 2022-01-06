package tfip.Workshop11;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class MyApplication {
	//This is to check the logs
	private final static Logger logger = LoggerFactory.getLogger(MyApplication.class);
	/*This is the default port that we set. We use capital letters to represent items we set as default...
	we also can use the application properties to set the default port*/
	private final static String DEFAULT_PORT = "3000";

	public static void main(String[] args) {

		//We get the springboot app to run everything in myapplication
		SpringApplication app = new SpringApplication(MyApplication.class);

		//we get the application arguments
		ApplicationArguments appArgs = new DefaultApplicationArguments(args);
		//from the application arguments we get the Option values from the argument "port"
		List optVal = appArgs.getOptionValues("port");
		//we initialise the initial portnumber to be null
		String portNumber = null;

		//we first do determine if there are arguments, or even if there is a argument, is there a value for the port...
		if (optVal == null || optVal.get(0)==null){
			//if there are no arguments  or even if there is a argument but no port number.... we use the system port number....
			portNumber = System.getenv("PORT");
			//if the system port number is not there we set the default port to 3000...
			if(portNumber == null){
				portNumber = DEFAULT_PORT;
				}
		}else{
			//if there is a port number, we set the portNumber to be the argument....
			portNumber = (String) optVal.get(0);
		}


		//Once we have the portNumber we set the server port to be the portNumber
		if(portNumber != null){
			app.setDefaultProperties(Collections.singletonMap("server.port",portNumber));
		}
		//We run the application
		app.run(args);
	}

}
