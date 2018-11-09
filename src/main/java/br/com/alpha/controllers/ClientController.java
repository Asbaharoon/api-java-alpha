package br.com.alpha.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.alpha.bean.Client;
import br.com.alpha.factory.ConnectionFactory;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

@RestController
public class ClientController {
	
	
	@GetMapping("/client/{clientId}")
	public Client getClient(@PathVariable int clientId) {
		
		ConnectionFactory connectionFactory = new ConnectionFactory();
		StatefulRedisConnection<String, String> connection = connectionFactory.getConnection();
		//StatefulRedisConnection<String, String> connection = connectionFactory.getConnectionUri();
		
		RedisCommands<String, String> syncCommands = connection.sync();

	    System.out.println("Connected to Redis");
	    
	    //syncCommands.set(String.valueOf(clientId), "localhost:300"+clientId);
	    String url = syncCommands.get(String.valueOf(clientId));
	    
	    System.out.println(url);
	    
	    connectionFactory.closeConnection(connection);
		
		return new Client(clientId, url);
	}
	
}
