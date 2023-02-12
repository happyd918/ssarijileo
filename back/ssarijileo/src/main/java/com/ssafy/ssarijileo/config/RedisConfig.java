// package com.ssafy.ssarijileo.config;
//
// import java.time.Duration;
// import java.util.List;
//
// import org.springframework.beans.factory.annotation.Value;
// // import org.springframework.data.redis.support.collections.RedisProperties;
// // import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.connection.RedisClusterConfiguration;
// import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
// import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
// import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
// import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//
// import io.lettuce.core.ClientOptions;
// import io.lettuce.core.SocketOptions;
// import lombok.RequiredArgsConstructor;
//
// @EnableRedisRepositories
// @Configuration
// @RequiredArgsConstructor
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
// 	public RedisTemplate<String, Object> redisTemplate() {
// 		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
// 		redisTemplate.setConnectionFactory(redisConnectionFactory());
// 		redisTemplate.setKeySerializer(new StringRedisSerializer());
// 		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
// 		return redisTemplate;
// 	}
// }package com.ssafy.ssarijileo.config;
//
// import java.time.Duration;
// import java.util.List;
//
// import org.springframework.beans.factory.annotation.Value;
// // import org.springframework.data.redis.support.collections.RedisProperties;
// // import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.connection.RedisClusterConfiguration;
// import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
// import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
// import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
// import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//
// import io.lettuce.core.ClientOptions;
// import io.lettuce.core.SocketOptions;
// import lombok.RequiredArgsConstructor;
//
// @EnableRedisRepositories
// @Configuration
// @RequiredArgsConstructor
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
// 	public RedisTemplate<String, Object> redisTemplate() {
// 		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
// 		redisTemplate.setConnectionFactory(redisConnectionFactory());
// 		redisTemplate.setKeySerializer(new StringRedisSerializer());
// 		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
// 		return redisTemplate;
// 	}
// }