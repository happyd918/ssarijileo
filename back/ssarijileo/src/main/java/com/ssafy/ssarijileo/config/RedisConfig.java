// package com.ssafy.ssarijileo.config;
//
// import java.util.List;
//
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.connection.RedisClusterConfiguration;
// import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
// import org.springframework.data.redis.core.StringRedisTemplate;
//
// @Configuration
// public class RedisConfig {
//
// 	@Value("${spring.redis.cluster.nodes}")
// 	private List<String> clusterNodes;
//
// 	@Bean
// 	public RedisConnectionFactory redisConnectionFactory() {
// 		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(clusterNodes);
// 		return new LettuceConnectionFactory(redisClusterConfiguration);
// 	}
//
// 	@Bean
// 	public StringRedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
// 		return new StringRedisTemplate(redisConnectionFactory);
// 	}
// }
