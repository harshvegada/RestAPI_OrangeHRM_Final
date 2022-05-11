package com.qa.tc.testngListeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ReRunTestCase implements IRetryAnalyzer {

	private int maxRetry = 3;
	private int retry = 0;

	@Override
	public boolean retry(ITestResult result) {
		if (maxRetry > retry) {
			retry++;
			return true;
		}
		return false;
	}

}
