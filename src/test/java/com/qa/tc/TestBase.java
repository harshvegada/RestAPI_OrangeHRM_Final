package com.qa.tc;

import com.qa.tc.pages.admin.Employment_Status_Page;
import com.qa.tc.pages.job_title.Job_Title_Page;
import com.qa.tc.utill.GenerateData;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import static com.qa.tc.constant.FilePaths.ALLURE_ENV_PATH;
import static com.qa.tc.constant.FilePaths.ALLURE_JSON_FILES;


public class TestBase {

    /* Pages Instances */
    Employment_Status_Page employment_Status_Page = Employment_Status_Page.getInstance();
    Job_Title_Page job_Title_Page = Job_Title_Page.getInstance();


    /*Faker Reference To Create Data*/
    GenerateData data = new GenerateData();


    @BeforeSuite
	public void setupAllureEnvironment() {
		if (new File(ALLURE_JSON_FILES).exists()) {
			File fileDirs = new File(ALLURE_JSON_FILES);
			File[] files = fileDirs.listFiles();
			if (files.length != 0) {
				for (File singleFile : files) {
					singleFile.delete();
				}
			}
		}
		new File(ALLURE_JSON_FILES).mkdir();
		Properties prop = new Properties();
//		prop.setProperty("User Name", System.getProperty("user.name").toUpperCase());
		prop.setProperty("User Name", "Maulik");
		prop.setProperty("Execution Env", "PROD");
		prop.setProperty("OS", System.getProperty("os.name"));
		prop.setProperty("Java Version", System.getProperty("java.version"));
		try {
			FileOutputStream otuput = new FileOutputStream(new File(ALLURE_ENV_PATH));
			prop.store(otuput, "Comments");
			otuput.flush();
			otuput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
