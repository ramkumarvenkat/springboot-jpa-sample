package com.example.resume.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.resume.model.Resume;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ResumeControllerTest {
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	private JacksonTester<Resume> json;
	
	@Before
	public void setup() {
		ObjectMapper objectMapper = new ObjectMapper(); 
        JacksonTester.initFields(this, objectMapper);
	}
	
	@Test
    public void testUploadAndGetResume_happycase() throws Exception {
		Resume input = json.read(this.getClass().getResourceAsStream("/ResumeInput.json")).getObject();
        restTemplate.postForEntity("/api/uploadResume", input, Resume.class).getBody();
        Resume result = restTemplate.getForEntity("/api/getResume/1", Resume.class).getBody();
        
        assertThat(this.json.write(result)).isEqualToJson(this.getClass().getResourceAsStream("/ResumeOutput.json"));
    }
	
	@Test
    public void testUploadResume_nullrequest() throws Exception {
        Resume r = restTemplate.postForEntity("/api/uploadResume", null, Resume.class).getBody();
        assertEquals(0, r.getId());
        assertNull(r.getName());
        assertNull(r.getCurrentJob());
    }
	
	@Test
    public void testUploadResume_invalidid() throws Exception {
		Resume r = restTemplate.getForEntity("/api/getResume/0", Resume.class).getBody();
		assertEquals(0, r.getId());
        assertNull(r.getName());
        assertNull(r.getCurrentJob());
    }
}
