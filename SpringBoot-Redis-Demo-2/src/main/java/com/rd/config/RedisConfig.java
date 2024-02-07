package com.rd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig {

	@Bean
	public JedisConnectionFactory connectionFactory() {
		RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();

		configuration.setHostName("localhost");
		configuration.setPort(6379);
		return new JedisConnectionFactory(configuration);
	}

	// This is for the default configuration

	@Bean
	public RedisTemplate<String, Object> redisTemplate()
	{
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		
		template.setConnectionFactory(connectionFactory());
		template.setKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new StringRedisSerializer());
		template.setHashKeySerializer(new GenericToStringSerializer<Object>(Object.class));
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		template.setEnableTransactionSupport(true);
		template.afterPropertiesSet();
		return template;
		
	}

	// Below are for the publisher subscriber event.

//	@Bean
//	@Primary
//	public RedisTemplate<String, Object> redisTemplate() {
//		RedisTemplate<String, Object> template = new RedisTemplate<>();
//
//		template.setConnectionFactory(connectionFactory());
//		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
//		return template;
//
//	}
//
//	@Bean
//	public ChannelTopic topic() {
//		return new ChannelTopic("pubsub:impChannel");
//	}
//
//	@Bean
//	public MessageListenerAdapter messageListenerAdapter() {
//		return new MessageListenerAdapter(new Receiver());
//	}
//
//	@Bean
//	public RedisMessageListenerContainer redisMessageListenerContainer() {
//		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory());
//		container.addMessageListener(messageListenerAdapter(), topic());
//		return container;
//	}
	
	//pubsub config end here
}
