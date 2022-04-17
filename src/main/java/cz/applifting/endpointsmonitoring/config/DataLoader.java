package cz.applifting.endpointsmonitoring.config;

import cz.applifting.endpointsmonitoring.entity.MonitoredEndpoint;
import cz.applifting.endpointsmonitoring.entity.User;
import cz.applifting.endpointsmonitoring.repo.MonitoredEndpointRepository;
import cz.applifting.endpointsmonitoring.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Elshad Jabbarov
 * 4/17/2022
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
	private final UserRepository userRepository;
	private final MonitoredEndpointRepository monitoredEndpointRepository;

	@Override
	public void run(String... args) throws Exception {

		var users = userRepository.saveAll(List.of(new User("user", "user@gmail.com"),
			new User("jon", "jon@gmail.com"),
			new User("tom", "tom@gmail.com"),
			new User("tom2", "tom2@gmail.com")
		));
	   users.forEach(user -> {
		   var monitoredEndpoint = new MonitoredEndpoint();
		   monitoredEndpoint.setName("baeldung");
		   monitoredEndpoint.setUrl("https://eoe8vj6kvm12tsx.m.pipedream.net");
		   monitoredEndpoint.setUser(user);
		   monitoredEndpointRepository.save(monitoredEndpoint);
	   });
	}
}
