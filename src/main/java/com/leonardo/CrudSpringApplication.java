package com.leonardo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.leonardo.enums.Category;
import com.leonardo.model.Course;
import com.leonardo.model.Lesson;
import com.leonardo.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudSpringApplication.class, args);
    }

    @Bean
    @Profile("dev")
    CommandLineRunner initDatabase(CourseRepository courseRepository) {
        return args -> {
            courseRepository.deleteAll();

            for (int i = 0; i < 20; i++) {
                Course course = new Course();
                course.setName("Angular" + i);
                course.setCategory(Category.FRONT_END);

                Lesson lesson1 = new Lesson();
                lesson1.setName("Getting Started");
                lesson1.setYoutubeUrl("qwertyuiop");
                lesson1.setCourse(course);
                course.getLessons().add(lesson1);

                Lesson lesson2 = new Lesson();
                lesson2.setName("The Basics");
                lesson2.setYoutubeUrl("asdfghjkl");
                lesson2.setCourse(course);
                course.getLessons().add(lesson2);

                courseRepository.save(course);
            }
        };
    }

}
