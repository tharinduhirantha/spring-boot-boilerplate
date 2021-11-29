package com.firststep.rest;

import com.firststep.dto.CourseDTO;
import com.firststep.model.Course;
import com.firststep.model.Instructor;
import com.firststep.model.Student;
import com.firststep.service.CourseService;
import com.firststep.service.UserService;
import com.firststep.utill.CommonUtill;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class RestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	UserService userService;

	@Autowired
	CourseService courseService;

	JSONFormatter formatter = new JSONFormatter();

	/*
@Test
	public void test() throws IOException {

		JSONObject requestParams = new JSONObject();
		requestParams.put("email", "emailddddddd"); // Cast
		requestParams.put("firstName", "Singh");
		requestParams.put("lastName", "Singha");
		requestParams.put("password", "password");
		requestParams.put("qualification", "qua");
		requestParams.put("id", 0);

		String jsonStr = formatter.convertJsonToStr(requestParams);
		System.out.println(jsonStr);

		given()
				.contentType(ContentType.JSON)
				.accept(ContentType.TEXT)
				.body(jsonStr).when().post("http://localhost:8080/api/user/instructor/signup").
				then().statusCode(201).and().log().all();
	} */

	@Test
	public void saveAndLogInstructor() throws Exception {
		Instructor instructor = new Instructor();
		instructor.setEmail("tom@gmail.com");
		instructor.setFirstName("Tom");
		instructor.setLastName("Hank");
		instructor.setQualification("PHD");
		instructor.setPassword("password");

		userService.saveOrUpdate(instructor);
		int auth = userService.login("tom@gmail.com","password","INSTRUCTOR");
		assertEquals(1,auth);

		auth = userService.login("tom@gmail.com","invalid","INSTRUCTOR");
		assertEquals(0,auth);
	}

	@Test
	public void saveAndLogStudent() throws Exception {
		Student student = new Student();

		student.setEmail("will@gmail.com");
		student.setFirstName("Will");
		student.setLastName("Smith");
		student.setJoinedYear(2021L);
		student.setPassword("password");

		userService.saveOrUpdate(student);
		int auth = userService.login("will@gmail.com","password","S");
		assertEquals(1,auth);

		auth = userService.login("will@gmail.com","invalid","S");
		assertEquals(0,auth);
	}
	@Test
	public void getCousebyInstructorId() throws Exception {
		ArrayList<CourseDTO> courses = courseService.getCousebyInstructorId(2l);
		assertEquals(1,courses.size());
	}
	@Test
	public void saveCourse() throws Exception {
		CourseDTO course = new CourseDTO();
		course.setCourseName("Maths");
		course.setCourseDescription("Learn maths");
		course.setInstructorId(1L);
		courseService.saveOrUpdate(course);
	}

	@Test
	public void startCourse() throws Exception {
		CourseDTO course = new CourseDTO();
		course.setCourseName("Maths");
		course.setCourseDescription("Learn maths");
		course.setInstructorId(1L);
		courseService.startCourse(1L);
		Course model = courseService.getCourse(1L);
		assertEquals(CommonUtill.CouseStatus.START,model.getStatus());
	}


}