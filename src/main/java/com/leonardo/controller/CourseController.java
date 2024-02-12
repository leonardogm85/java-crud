package com.leonardo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.dto.CourseDTO;
import com.leonardo.dto.PageDTO;
import com.leonardo.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public PageDTO<CourseDTO> list(@RequestParam(defaultValue = "0") @PositiveOrZero int pageNumber,
            @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize) {
        return courseService.list(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public CourseDTO loadById(@PathVariable @NonNull @Positive Long id) {
        return courseService.loadById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CourseDTO create(@RequestBody @NonNull @Valid CourseDTO dto) {
        return courseService.create(dto);
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable @NonNull @Positive Long id, @RequestBody @NonNull @Valid CourseDTO dto) {
        return courseService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NonNull @Positive Long id) {
        courseService.delete(id);
    }

}
