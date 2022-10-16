package com.fbs.authentication.response;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Service
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
	private String message;
}
