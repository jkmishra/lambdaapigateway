package com.example.lambdaapigateway;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LambdaTestController {

	@GetMapping(path = { "{name}" }, produces = { "application/json" })
	public TestResp greeting(@PathVariable Optional<String> name) {

		return new TestResp("Hello " + name.get());
	}

	@GetMapping(path = { "/list/{count}" }, produces = { "application/json" })
	public List<TestResp> getCurrencyList(@PathVariable Optional<Integer> count) {
		List<TestResp> listObj = new ArrayList<>();
		for (int i = 0; i <= count.get(); i++) {
			listObj.add(new TestResp("Hello " + i));
		}
		return listObj;
	}

}
