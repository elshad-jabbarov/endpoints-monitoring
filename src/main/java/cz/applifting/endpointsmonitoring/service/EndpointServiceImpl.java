package cz.applifting.endpointsmonitoring.service;

import cz.applifting.endpointsmonitoring.entity.MonitoredEndpoint;
import cz.applifting.endpointsmonitoring.exceptions.NotFoundException;
import cz.applifting.endpointsmonitoring.model.MonitoredEndpointInfo;
import cz.applifting.endpointsmonitoring.repo.MonitoredEndpointRepository;
import cz.applifting.endpointsmonitoring.repo.MonitoringResultRepository;
import cz.applifting.endpointsmonitoring.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * @author Elshad Jabbarov
 * 4/17/2022
 */
@Service
@RequiredArgsConstructor
public class EndpointServiceImpl implements EndpointService {
	private final MonitoredEndpointRepository endpointRepository;
	private final UserRepository userRepository;
	private final MonitoringResultRepository monitoringResultRepository;

	@Override
	public MonitoredEndpointInfo findEndpointById(long id, UUID accessToken) {
		validateUser(accessToken);
		var endpoint = endpointRepository.findByIdAndUser_AccessToken(id, accessToken);
		return endpoint.orElseThrow(() -> new NotFoundException("endpoint not found"));
	}

	@Override
	public List<MonitoredEndpointInfo> findAllEndpoints(UUID accessToken) {
		validateUser(accessToken);
		return endpointRepository.findAllByUser_AccessToken(accessToken);
	}

	@Override
	public MonitoredEndpoint createEndpoint(MonitoredEndpoint endpoint, UUID accessToken) {
		validateUser(accessToken);
		var user = userRepository.findByAccessToken(accessToken).get();
		endpoint.setUser(user);
		return endpointRepository.save(endpoint);
	}

	@Override
	public MonitoredEndpoint updateEndpoint(long id, MonitoredEndpoint endpoint, UUID accessToken) {
		validateUser(accessToken);
		endpointRepository.findById(id)
			.ifPresentOrElse(monitoredEndpoint -> {
				monitoredEndpoint.setUrl(endpoint.getUrl());
				monitoredEndpoint.setName(endpoint.getName());
				endpointRepository.save(monitoredEndpoint);
			}, () -> new NotFoundException("endpoint not found"));
		return endpoint;
	}

	@Override
	public void deleteEndpoint(long id, UUID accessToken) {
		validateUser(accessToken);
        endpointRepository.deleteById(id);
	}

	@Override
	public List<MonitoredEndpointInfo.MonitoringResultInfo> findResultsForEndpoint(Long endpointId, UUID accessToken, Pageable pageable) {
		validateUser(accessToken);

		return monitoringResultRepository.findAllByMonitoredEndpointIdAndMonitoredEndpoint_User_AccessToken(endpointId,accessToken,pageable);
	}

	private void validateUser(UUID accessToken) {
		var byAccessToken = userRepository.findByAccessToken(accessToken);
		if (byAccessToken.isEmpty()) throw new NotFoundException("requested user with access token  not found");
	}
}
