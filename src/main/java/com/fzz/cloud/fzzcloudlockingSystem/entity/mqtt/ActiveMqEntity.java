package com.fzz.cloud.fzzcloudlockingSystem.entity.mqtt;

public class ActiveMqEntity {
	private String topic;//MQ的主题
	private String msg;//发送给Mq的内容
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
