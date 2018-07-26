package com.fzz.cloud.fzzcloudlockingSystem.entity.mqtt;

public class TopicMqttv3 {
	private String topic;//mq的主题
	private  String topicId;//主题的标识
	private String msg;//内容
	
	public TopicMqttv3(String topic, String topicId, String msg) {
		super();
		this.topic = topic;
		this.topicId = topicId;
		this.msg = msg;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getTopicId() {
		return topicId;
	}
	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
