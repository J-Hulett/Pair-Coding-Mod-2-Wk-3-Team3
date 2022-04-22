package com.techelevator.services;

import org.springframework.stereotype.Component;

import com.techelevator.model.CatFact;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;


@Component
public class RestCatFactService implements CatFactService {

	private static final String API_URL = "https://cat-data.netlify.app/api/facts/random";
	private final RestTemplate restTemplate = new RestTemplate();


	@Override
	public CatFact getFact() {
		// TODO Auto-generated method stub
		CatFact catFact = null;
		try {
			catFact = restTemplate.getForObject(API_URL, CatFact.class);
		} catch (RestClientResponseException | ResourceAccessException e){
			e.printStackTrace();
		} catch (NullPointerException e){
			e.printStackTrace();
		}
		return catFact;
	}


	//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity <CatFact> entity = new HttpEntity<>(newCatFact, headers);
}
