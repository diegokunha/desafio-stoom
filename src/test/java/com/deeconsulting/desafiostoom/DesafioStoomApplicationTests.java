package com.deeconsulting.desafiostoom;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.deeconsulting.desafiostoom.data.vo.URL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;

@SpringBootTest
class DesafioStoomApplicationTests{
	
	@Test
	void contextLoads() throws ApiException, InterruptedException, IOException {
		
		String number = "95";
		String streetName = "Rua São Pedro";
		String city = "Nova Iguaçu";
		String state = "RJ";
		
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey("AIzaSyCj0cY2yEvVfYhAaTz3-P2MW-YRKmhz5Uw")
			    .build();
			GeocodingResult[] results =  GeocodingApi.geocode(context,
					  URL.decode(number) + "+" 
					+ URL.decode(streetName) + ",+" 
					+ URL.decode(city) + ",+" 
					+ URL.decode(state)).await();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(results[0].geometry.location.lat));
	}
	
}
