package com.fzz.cloud.fzzcloudlockingSystem.util;

import java.util.UUID;

/**
* @author 作者 一叶之秋:
* @version 创建时间：2017年5月19日 下午4:18:38
* 类说明
*/
public class GetUUID {
	public static String getUUid(){
		UUID uuid = UUID.randomUUID();
		String newid = uuid.toString();
		return newid;
	}
}
