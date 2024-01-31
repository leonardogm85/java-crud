package com.leonardo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.model.Course;
import com.leonardo.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;

@Validated
@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseRepository courseRepository;

    // @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Course> list() {
        return courseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> loadById(@PathVariable @NonNull @Positive Long id) {
        return courseRepository.findById(id)
                .map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.notFound().build());
    }

    // @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Course create(@RequestBody @NonNull @Valid Course course) {
        // return ResponseEntity
        // .status(HttpStatus.CREATED)
        // .body(courseRepository.save(course));
        return courseRepository.save(course);
    }

    // @RequestMapping(method = RequestMethod.PUT)
    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable @NonNull @Positive Long id,
            @RequestBody @NonNull @Valid Course course) {
        return courseRepository.findById(id)
                .map(entity -> {
                    entity.setName(course.getName());
                    entity.setCategory(course.getCategory());
                    Course updated = courseRepository.save(entity);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // @RequestMapping(method = RequestMethod.DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NonNull @Positive Long id) {
        return courseRepository.findById(id)
                .map((@NonNull Course entity) -> {
                    courseRepository.delete(entity);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
