package com.project.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.model.Student;
import com.project.validator.StudentValidator;

@Controller
@RequestMapping("/")
public class HelloWorldController {

	static final Logger logger = Logger.getLogger(HelloWorldController.class);
	
	@Autowired
    StudentValidator validator;
	
	/*
	 * This method will serve as default GET handler.
	 */

	@InitBinder
    public void performValidation(WebDataBinder registry)
    {
        StringTrimmerEditor editor = new StringTrimmerEditor(true);
        registry.setValidator(validator);
        registry.registerCustomEditor(String.class,editor);
    }
	
	@RequestMapping(method = RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		logger.info("inside newregistration");
		logger.debug("inside debug");
		Student student = new Student();
		model.addAttribute("student", student);
		return "enroll";
	}

	
	/*
	 * This method will be called on form submission, handling POST request It
	 * also validates the user input
	 * Using Custom validation
	 */
	@RequestMapping(value = "doSuccess", method = RequestMethod.POST)
	public String saveRegistration(@Validated Student student,
			BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "enroll";
		}

		model.addAttribute("success", "Dear " + student.getFirstName()
				+ " , your Registration completed successfully");
		return "success";
	}

	/*
	 * Method used to populate the Section list in view. Note that here you can
	 * call external systems to provide real data.
	 */
	@ModelAttribute("sections")
	public List<String> initializeSections() {

		List<String> sections = new ArrayList<String>();
		sections.add("Graduate");
		sections.add("Post Graduate");
		sections.add("Research");
		return sections;
	}

	/*
	 * Method used to populate the country list in view. Note that here you can
	 * call external systems to provide real data.
	 */
	@ModelAttribute("countries")
	public List<String> initializeCountries() {

		List<String> countries = new ArrayList<String>();
		countries.add("USA");
		countries.add("CANADA");
		countries.add("FRANCE");
		countries.add("GERMANY");
		countries.add("ITALY");
		countries.add("OTHER");
		return countries;
	}

	/*
	 * Method used to populate the subjects list in view. Note that here you can
	 * call external systems to provide real data.
	 */
	@ModelAttribute("subjects")
	public List<String> initializeSubjects() {

		List<String> subjects = new ArrayList<String>();
		subjects.add("Physics");
		subjects.add("Chemistry");
		subjects.add("Life Science");
		subjects.add("Political Science");
		subjects.add("Computer Science");
		subjects.add("Mathmatics");
		return subjects;
	}

}
