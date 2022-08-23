package com.example.storehouse.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UpdateRawMatetialRequest {


	@NotBlank(message = "Material amount cannot be empty")
	@Size(min = 2, message = "Invalid amount")
	private String amount;
}
