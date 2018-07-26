package com.fzz.cloud.fzzcloudlockingSystem.util;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Component;

import com.fzz.cloud.fzzcloudlockingSystem.entity.mqtt.TopicMqttv3;
import com.github.pagehelper.util.StringUtil;

@Component
public class Mqttv3Util {
	/**
	 * 此工具类用来发送消息-- 直接的发布订阅。或者订阅后，根据id来确定到底给那些人。
	 * @param topicId//识别客户端的id
	 * @param topics//主题
	 * 
	 */
	public void connect(TopicMqttv3 topicMqttv3){
		try {
			ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin",
					"tcp://120.79.15.123:61616");			
			Connection connection = factory.createConnection();
			//connection.setClientID("mqtt-1055");
			connection.start();
			Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			Topic topic = session.createTopic(topicMqttv3.getTopic());
			MessageProducer producer = session.createProducer(topic);
			Message message = session.createTextMessage(topicMqttv3.getMsg());
			String id = null;
			if(!StringUtil.isEmpty(topicMqttv3.getTopicId())){
				id = topicMqttv3.getTopicId();
			}
			// 指定clientID
			message.setStringProperty("PTP_CLIENTID", id);
			producer.send(message);
			producer.close();
		    session.close();
		    connection.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
