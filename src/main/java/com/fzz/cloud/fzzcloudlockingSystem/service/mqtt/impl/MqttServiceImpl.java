package com.fzz.cloud.fzzcloudlockingSystem.service.mqtt.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fzz.cloud.fzzcloudlockingSystem.entity.mqtt.ActiveMqEntity;
import com.fzz.cloud.fzzcloudlockingSystem.entity.mqtt.TopicMqttv3;
import com.fzz.cloud.fzzcloudlockingSystem.service.mqtt.MqttService;
import com.fzz.cloud.fzzcloudlockingSystem.util.Mqttv3Util;
@Service
public class MqttServiceImpl implements MqttService {
	@Autowired
	private Mqttv3Util mqttv3Util;
	/**
	 * 去链接+发布
	 */
	@Override
	public void topicMqttv3(TopicMqttv3 topicMqttv3) {
		// TODO Auto-generated method stub
		try {
			mqttv3Util.connect(topicMqttv3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 预留接口
	 * 
	 */
	@Override
	public void topicActiveMq(ActiveMqEntity activeMqEntity) {
		// TODO Auto-generated method stub
		
	}

}
