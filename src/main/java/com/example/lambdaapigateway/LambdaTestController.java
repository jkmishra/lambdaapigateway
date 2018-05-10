package com.example.lambdaapigateway;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jugul.mishra May 10, 2018 3:05:02 PM
 */

@RestController
public class LambdaTestController {
	public LambdaTestController() {

	}
// getting name as paassed
	@GetMapping(path = { "{name}" }, produces = { "application/json" })
	public TestResp greeting(@PathVariable final Optional<String> name) {

		return new TestResp("Hello " + name.get());
	}
// printing list of testresp
	@GetMapping(path = { "/list/{count}" }, produces = { "application/json" })
	public List<TestResp> getCurrencyList(@PathVariable final Optional<Integer> count) {
		final List<TestResp> listObj = new ArrayList<>();
		for (int i = 0; i <= count.get(); i++) {
			getTestRespObj(listObj, i);
		}
		return listObj;
	}
	private void getTestRespObj(final List<TestResp> listObj, int i) {
		listObj.add(new TestResp("Hello " + i));
	}

}
