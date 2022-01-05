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
	
	private final static Logger logger = LoggerFactory.getLogger(MyApplication.class);
	private final static String DEFAULT_PORT = "3000";

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(MyApplication.class);

		/*ApplicationArguments cliOpts = new DefaultApplicationArguments(args);
		if (cliOpts.containsOption("port")){
			port = cliOpts.getOptionValues("port").get(0);
		}

		app.setDefaultProperties(
			Collections.singletonMap("server.port",port));
		;*/

		ApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List optVal = appArgs.getOptionValues("port");
		String portNumber = null;

		if (optVal == null || optVal.get(0)==null){
			portNumber = System.getenv("PORT");
			if(portNumber == null){
				portNumber = DEFAULT_PORT;
			}
		}else{
			portNumber = (String) optVal.get(0);
		}

		if(portNumber != null){
			app.setDefaultProperties(Collections.singletonMap("server.port",portNumber));
		}
		app.run(args);
	}

}
