package com.example.useractivity.entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@Table(name = "user_activity")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "total_request")
	private long totalRequest;
	@Column(name = "request_date")
	private String requestDate;
	@Column(name = "request_limit")
	private long requestLimit;

	@Column(name = "user_token")
	private String token;
	
	@Column(name = "request_time_duration")
	private String requestTimeDuration;

}
