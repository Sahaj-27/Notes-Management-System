package org.example.App.controller;

import java.util.stream.Collectors;

import org.example.App.service.TagService;
import org.example.App.utils.ResponseUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/master")
public class MasterController {

	private final ResponseUtils responseUtils;

	private final TagService tagService;

	@GetMapping("/health-check/ping")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Endpoint to check if service is up or not")
	public ResponseEntity<?> serviceHealthCheckerHandler() {
		return responseUtils.pingResponse();
	}


	@GetMapping("/context-type-for-tags")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Returns List of values of Context Type For Retreiving List of tags")
	public ResponseEntity<?> contextTypeRetreivalHandler() {
		return responseUtils.contextTypeListResponse();
	}

	@GetMapping("/tags")
	@ResponseStatus(value = HttpStatus.OK)
	@Operation(summary = "Returns List of tags in the system)")
	public ResponseEntity<?> tagsRetreivalHandler() {
		return ResponseEntity
				.ok(tagService.retreiveAll().parallelStream().map(tag -> tag.getName()).collect(Collectors.toList()));
	}

}
