package com.leonardo.dto.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.leonardo.dto.CourseDTO;
import com.leonardo.dto.LessonDTO;
import com.leonardo.enums.Category;
import com.leonardo.model.Course;
import com.leonardo.model.Lesson;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }

        List<LessonDTO> lessonsDto = course
                .getLessons()
                .stream()
                .map(lessonDto -> new LessonDTO(
                        lessonDto.getId(),
                        lessonDto.getName(),
                        lessonDto.getYoutubeUrl()))
                .collect(Collectors.toList());

        return new CourseDTO(
                course.getId(),
                course.getName(),
                course.getCategory().getValue(),
                lessonsDto);
    }

    public Course toEntity(CourseDTO courseDto) {
        if (courseDto == null) {
            return null;
        }

        Course course = new Course();

        if (courseDto.id() != null) {
            course.setId(courseDto.id());
        }

        course.setName(courseDto.name());
        course.setCategory(convertCategoryValue(courseDto.category()));

        List<Lesson> lessons = courseDto.lessons().stream().map(lessonDto -> {
            Lesson lesson = new Lesson();
            lesson.setId(lessonDto.id());
            lesson.setName(lessonDto.name());
            lesson.setYoutubeUrl(lessonDto.youtubeUrl());
            lesson.setCourse(course);
            return lesson;
        }).collect(Collectors.toList());

        course.setLessons(lessons);

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
