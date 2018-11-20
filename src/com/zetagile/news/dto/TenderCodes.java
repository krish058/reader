package com.zetagile.news.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@Entity

@Table(name = "tender_codes")


public class TenderCodes {
	
	@Id
	@Column(name="code")
	private String code;
	
	@Column(name="price")
	private String price;
	
}
