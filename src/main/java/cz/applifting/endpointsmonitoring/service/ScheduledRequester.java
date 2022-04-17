package cz.applifting.endpointsmonitoring.service;

import cz.applifting.endpointsmonitoring.entity.MonitoredEndpoint;
import cz.applifting.endpointsmonitoring.entity.MonitoringResult;
import cz.applifting.endpointsmonitoring.repo.MonitoredEndpointRepository;
import cz.applifting.endpointsmonitoring.repo.MonitoringResultRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * @author Elshad Jabbarov
 * 4/17/2022
 */
@Service
@RequiredArgsConstructor
@Log4j2
public class ScheduledRequester {
	private final MonitoredEndpointRepository endpointRepository;
	private final RestTemplate restTemplate = new RestTemplate();
	private final MonitoringResultRepository monitoringResultRepository;

	@Scheduled(fixedRate = 10000)
	public void monitor() {
		log.info("Scheduled stared ");
		endpointRepository.findAll().forEach(monitoredEndpoint -> {
			var url = monitoredEndpoint.getUrl();
			ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
			updateEndPointResult(response, monitoredEndpoint);
		});
	}

	@Transactional
	public void updateEndPointResult(ResponseEntity<String> response, MonitoredEndpoint endpoint) {
		var body = response.getBody();
		var statusCode = response.getStatusCode().value();
		var monitoringResult = new MonitoringResult();
		monitoringResult.setDateOfCheck(LocalDateTime.now());
		monitoringResult.setPayload(body);
		monitoringResult.setStatusCode(statusCode);
		monitoringResult.setMonitoredEndpoint(endpoint);
		monitoringResultRepository.save(monitoringResult);

		endpoint.setLastCheck(LocalDateTime.now());
		endpoint.setMonitoredInterval(endpoint.getMonitoredInterval() + 1);
		endpointRepository.save(endpoint);
	}
}
