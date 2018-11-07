package br.com.alpha.factory;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;

public class ConnectionFactory {
	
	private RedisClient redisClient; 
	
	public StatefulRedisConnection<String, String> getConnection () {
		this.redisClient = RedisClient.create("redis://localhost:6379/0");
	    return this.redisClient.connect();
	}
	
	public StatefulRedisConnection<String, String> getConnectionUri() {
		RedisURI redisUri = RedisURI.Builder.redis("localhost")
                .withSsl(true)
                .withPassword("authentication")
                .withDatabase(2)
                .build();
	    this.redisClient = RedisClient.create(redisUri);
	    return this.redisClient.connect();
	}
	
	public void closeConnection (StatefulRedisConnection<String, String> connection) {
		connection.close();
		this.redisClient.shutdown();
	}
	
}
