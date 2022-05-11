package com.qa.tc.customAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.qa.tc.enums.TestCaseCategoryType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestCaseCategory {

	public String author() default "";

	public TestCaseCategoryType[] category();

}
