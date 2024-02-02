package com.leonardo.service;

import java.util.List;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.leonardo.exception.RecordNotFoundException;
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

    public Course loadById(@NonNull @Positive Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course create(@NonNull @Valid Course course) {
        return courseRepository.save(course);
    }

    public Course update(@NonNull @Positive Long id, @NonNull @Valid Course course) {
        return courseRepository.findById(id)
                .map(entity -> {
                    entity.setName(course.getName());
                    entity.setCategory(course.getCategory());
                    return courseRepository.save(entity);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NonNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }

}
