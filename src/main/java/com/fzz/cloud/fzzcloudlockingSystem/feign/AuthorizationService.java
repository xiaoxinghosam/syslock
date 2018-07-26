package com.fzz.cloud.fzzcloudlockingSystem.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(value="fzz-could-permissionsusers", fallback = AuthorizationServiceFallBack.class/*, url="http://192.168.0.116:8799/"*/)
@FeignClient(value="FZZ-CLOUD-FACILITATOR", fallback = AuthorizationServiceFallBack.class)
public interface AuthorizationService {
	
	@RequestMapping(value = "/Permissions/sharePermissions", method = RequestMethod.POST)
	String sharePermissionsController(@RequestParam("authorizationModel") String authorizationModel);
	
	@RequestMapping(value = "/facilitator/test", method = RequestMethod.POST)
	String test2(@RequestParam("id") String id);
	
}
