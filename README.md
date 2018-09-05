# Spring4MvcCustomValidation

* Spring 4 + MVC + Java Configuration + Maven + Spring Custom Validation, Example
* Template example for Spring 4 MVC + JSP View with pure Java Configuration (no XML), using Maven build tool.
* Spring4 + MVC, Integration without using the web.xml and Spring_Servlet.xml file. 
* By using WebMvcConfigurerAdapter class and WebApplicationInitializer interface to replace above files.
* By Implementing Validator Interface, using @InitBinder and @Validated

> **###1. Technologies**
* Spring 4.0.6.RELEASE
* Maven 3.1
* JSTL 1.2

> **###2. To Run this project locally**
* $ git clone https://github.com/AkashChauhanSoftEngi/Spring4MvcValidation
* $ mvn tomcat7:run

> **###3.  Access** 
* http://localhost:8080/Spring4MvcValidation

> **###4. Important points to keep in mind**
* When you are using Custom Validation you need to create a validation class(in IOC)
  - which implement validator interface
  - and over validate(Object target, Errors errors) {} method
* For custom Validation, you need to use @Validated instead @Valid
  - This is, spring validation, not javax{@Valid}
```java
	@RequestMapping(value = "doSuccess", method = RequestMethod.POST)
	public String saveRegistration(@Validated Student student,
			BindingResult result, ModelMap model) {}
```
* If @Validated finds error it will store those erros in BindingResult result
* JSP pages use values from BindingResult and ger error message from the result itself and then show with the form
* Before even using such custom validation
  - we need to bind, created validator with WebDataBinder, using @InitBinder
```java
	WebDataBinder.setValidator(custom validator bean);
```
