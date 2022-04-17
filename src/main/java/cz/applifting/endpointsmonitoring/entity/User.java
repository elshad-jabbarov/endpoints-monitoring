package cz.applifting.endpointsmonitoring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.validation.constraints.Email;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;
/**
 * @author Elshad Jabbarov
 * 4/17/2022
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;


	@Column(nullable = false)
	private String name;

	@Email
	@Column(nullable = false)
	private String email;

	@Type(type="org.hibernate.type.UUIDCharType")
	private UUID accessToken;

	@JsonIgnore
	@OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
	private Set<MonitoredEndpoint> monitoredEndpoints = new LinkedHashSet<>();

	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}

	@PrePersist
	public void onPrePersist() {
		accessToken = UUID.randomUUID();
	}
}