package com.fzz.cloud.fzzcloudlockingSystem;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
//@Import(FdfsClientConfig.class)
//@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@MapperScan("com.fzz.cloud.fzzcloudlockingSystem.mapper.*")
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
@SpringBootApplication
public class FzzCloudLockingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FzzCloudLockingSystemApplication.class, args);
	}
}
