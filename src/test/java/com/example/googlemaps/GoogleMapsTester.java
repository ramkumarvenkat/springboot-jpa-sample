package com.example.googlemaps;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.json.JacksonTester;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

public class GoogleMapsTester {

	static class Location {
		Double lat;
		Double lng;

		public Double getLat() {
			return lat;
		}

		public void setLat(Double lat) {
			this.lat = lat;
		}

		public Double getLng() {
			return lng;
		}

		public void setLng(Double lng) {
			this.lng = lng;
		}

	}

	static class MapsTestInputOutput {
		String address;
		Location location;

		public void setAddress(String address) {
			this.address = address;
		}

		public void setLocation(Location location) {
			this.location = location;
		}

	}

	private JacksonTester<List<MapsTestInputOutput>> json;
	private JacksonTester<Location> locationJson;

	private String API_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=%s";

	@Before
	public void setup() {
		ObjectMapper objectMapper = new ObjectMapper();
		JacksonTester.initFields(this, objectMapper);
	}

	@Test
	public void testLocations() throws IOException {

		List<MapsTestInputOutput> testInputOutputList = json.read(
				this.getClass().getResourceAsStream("/GoogleMapsTest.json"))
				.getObject();

		
		for (MapsTestInputOutput o : testInputOutputList) {
			HttpUriRequest httpRequest = new HttpGet(String.format(API_URL,
					URLEncoder.encode(o.address, "UTF8")));
			HttpResponse httpResponse = HttpClientBuilder.create().build()
					.execute(httpRequest);
			String response = EntityUtils.toString(httpResponse.getEntity(),
					"UTF-8");

			Location location = JsonPath.parse(response).read(
					"$.results[0].geometry.location", Location.class);
			assertThat(locationJson.write(location).getJson()).isEqualTo(locationJson.write(o.location).getJson());
		}
	}

}
