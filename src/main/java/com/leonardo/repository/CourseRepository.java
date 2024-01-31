package com.leonardo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

}
