package com.leonardo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import com.leonardo.model.Course;
import com.leonardo.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Optional<Course> loadById(@NonNull @Positive Long id) {
        return courseRepository.findById(id);
    }

    public Course create(@NonNull @Valid Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> update(@NonNull @Positive Long id, @NonNull @Valid Course course) {
        return courseRepository.findById(id)
                .map(entity -> {
                    entity.setName(course.getName());
                    entity.setCategory(course.getCategory());
                    return courseRepository.save(entity);
                });
    }

    public boolean delete(@NonNull @Positive Long id) {
        return courseRepository.findById(id)
                .map((@NonNull Course entity) -> {
                    courseRepository.delete(entity);
                    return true;
                })
                .orElse(false);
    }

}
