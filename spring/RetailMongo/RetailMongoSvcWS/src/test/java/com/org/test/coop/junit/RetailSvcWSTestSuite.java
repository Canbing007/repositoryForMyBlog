package com.org.test.coop.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.org.test.coop.master.junit.RetailMasterWSTest;
import com.org.test.coop.master.junit.RetailUtilWSTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	RetailMasterWSTest.class,
//	RetailUtilWSTest.class
})
public class RetailSvcWSTestSuite {
	
}
