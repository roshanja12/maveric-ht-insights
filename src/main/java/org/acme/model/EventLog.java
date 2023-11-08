package org.acme.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.acme.enums.Type;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "event_logs")
public class EventLog {

	@Id
	@GeneratedValue
	public  Long id;
	@Column(nullable = false)
	public Type type;
	@Column(nullable = false)
	public String message;
	@CreationTimestamp
	public Instant timestamps;
	@Column(name="created_at",nullable = false)
	@CreationTimestamp
	public Instant createdAt;

}
