package com.example.resume.persistence;

import com.example.resume.model.Resume;

public interface ResumeService {

	Resume create(Resume model);
	Resume read(Long id);
}
