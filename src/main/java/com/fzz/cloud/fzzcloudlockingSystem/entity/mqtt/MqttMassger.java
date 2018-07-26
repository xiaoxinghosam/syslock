package com.fzz.cloud.fzzcloudlockingSystem.entity.mqtt;
/**
 * 用于发送 mq消息的格式
 * @author Administrator
 *
 */
public class MqttMassger {
	private int code;
	private String mag;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMag() {
		return mag;
	}
	public void setMag(String mag) {
		this.mag = mag;
	}
	
}
