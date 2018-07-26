package com.fzz.cloud.fzzcloudlockingSystem.feign;

import org.springframework.stereotype.Component;

@Component
public class AuthorizationServiceFallBack implements AuthorizationService {

	@Override
	public String sharePermissionsController(String authorizationModel) {
		return "授权失败" + authorizationModel;
	}

	@Override
	public String test2(String id) {
		return "授权失败：" + id;
	}
}
