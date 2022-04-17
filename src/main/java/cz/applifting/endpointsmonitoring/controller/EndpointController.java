package cz.applifting.endpointsmonitoring.controller;

import cz.applifting.endpointsmonitoring.entity.MonitoredEndpoint;
import cz.applifting.endpointsmonitoring.model.MonitoredEndpointInfo;
import cz.applifting.endpointsmonitoring.service.EndpointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @author Elshad Jabbarov
 * 4/17/2022
 */
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api")
public class EndpointController {
	private final EndpointService service;

	@GetMapping("/endpoints")
	public ResponseEntity<List<MonitoredEndpointInfo>> findAllEndpoints(@RequestHeader(value = "accessToken") UUID accessToken) {
		log.info("requested findAll with accessToken " + accessToken);
		return ResponseEntity.ok().body(service.findAllEndpoints(accessToken));
	}

	@GetMapping("/endpoints/{endpointId}/results")
	public ResponseEntity<List<MonitoredEndpointInfo.MonitoringResultInfo>>
	getResultsForEndpoint(@RequestHeader(value = "accessToken") UUID accessToken, @PathVariable Long endpointId, Pageable pageable) {
		return ResponseEntity.ok().body(service.findResultsForEndpoint(endpointId, accessToken, pageable));

	}

	@GetMapping("/endpoints/{id}")
	public ResponseEntity<MonitoredEndpointInfo> findEndpointById(@PathVariable Long id,
																  @RequestHeader(value = "accessToken") UUID accessToken) {
		log.info("requested findById with accessToken " + accessToken);
		return ResponseEntity.ok().body(service.findEndpointById(id, accessToken));
	}

	@PostMapping("/endpoints")
	public ResponseEntity<MonitoredEndpoint> createEndpoint(@RequestHeader(value = "accessToken") UUID accessToken,
															@Valid @RequestBody MonitoredEndpoint endpoint) {
		log.info("requested  create with accessToken " + accessToken);
		return ResponseEntity.ok().body(service.createEndpoint(endpoint, accessToken));
	}

	@PutMapping("/endpoints/{id}")
	public ResponseEntity<MonitoredEndpoint> updateEndpoint(@RequestHeader(value = "accessToken") UUID accessToken,
															@PathVariable Long id,
															@Valid @RequestBody MonitoredEndpoint endpoint) {
		return ResponseEntity.ok().body(service.updateEndpoint(id, endpoint, accessToken));
	}

	@DeleteMapping("/endpoints/{id}")
	public ResponseEntity<String> deleteEndpoint(@RequestHeader(value = "accessToken") UUID accessToken, @PathVariable Long id) {
		service.deleteEndpoint(id, accessToken);
		return new ResponseEntity<>("deleted", HttpStatus.NO_CONTENT);
	}
}
