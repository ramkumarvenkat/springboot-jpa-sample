package com.example.resume.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.resume.model.Resume;

@Service
public class MemoryResumeService implements ResumeService {

	@Autowired
	private ResumeRepository repository;
	
	public Resume create(Resume model) {
		return repository.save(model);
	}

	public Resume read(Long id) {
		return repository.findOne(id);
	}

	
}
