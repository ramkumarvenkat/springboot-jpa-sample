package com.example.resume.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.resume.model.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}