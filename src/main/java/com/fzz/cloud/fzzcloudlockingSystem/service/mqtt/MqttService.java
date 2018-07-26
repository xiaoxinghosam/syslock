package com.fzz.cloud.fzzcloudlockingSystem.service.mqtt;

import com.fzz.cloud.fzzcloudlockingSystem.entity.mqtt.ActiveMqEntity;
import com.fzz.cloud.fzzcloudlockingSystem.entity.mqtt.TopicMqttv3;

public interface MqttService {
	//做移动端，发布订阅结合点对点推送使用协议  mqtt
	public void topicMqttv3(TopicMqttv3 topicMqttv3);
	//使用mq做发布订阅--实行对订阅过的朋友，没人都发送消息
	public void topicActiveMq(ActiveMqEntity activeMqEntity);
}
