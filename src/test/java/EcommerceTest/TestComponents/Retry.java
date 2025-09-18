package EcommerceTest.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int Count = 0;
	int MaxTry= 1;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		if(Count < MaxTry) {
			Count++;
			return true;
		}
		return false;
	}
	
}
