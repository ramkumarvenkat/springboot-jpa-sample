package com.example.resume.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.resume.model.Resume;
import com.example.resume.persistence.ResumeService;

@RestController
@RequestMapping(value = "/api")
public class ResumeController {

	@Autowired
	private ResumeService service;
	
	@RequestMapping(value = "/uploadResume", method = RequestMethod.POST)
	public Resume uploadResume(@Valid @RequestBody Resume resume) {
		Assert.notNull(resume, "resume must not be null");
		return service.create(resume);
	}

	@RequestMapping(value = "/getResume/{id}", method = RequestMethod.GET)
	public Resume getResume(@PathVariable Long id) {
		Assert.isTrue(id != 0, "id must not be 0");
		return service.read(id);
	}
}
