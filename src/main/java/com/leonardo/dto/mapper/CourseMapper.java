package com.leonardo.dto.mapper;

import org.springframework.stereotype.Component;

import com.leonardo.dto.CourseDTO;
import com.leonardo.enums.Category;
import com.leonardo.model.Course;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }

        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
    }

    public Course toEntity(CourseDTO dto) {
        if (dto == null) {
            return null;
        }

        Course course = new Course();

        if (dto.id() != null) {
            course.setId(dto.id());
        }

        course.setName(dto.name());
        course.setCategory(convertCategoryValue(dto.category()));

        return course;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case "frontend" -> Category.FRONT_END;
            case "backend" -> Category.BACK_END;
            default -> throw new IllegalArgumentException("Invalid category: " + value);
        };
    }

}
