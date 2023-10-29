package com.sushmanth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sushmanth.model.Address;

@FeignClient(name="ADDRESS-MS")
public interface AddressFeignUtil {

	@GetMapping(value = "/address/getById/{id}")
	public Address getAddressById(@PathVariable int id);
	
}
