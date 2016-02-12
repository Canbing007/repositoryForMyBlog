package com.org.test.coop.master.junit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.coop.admin.service.MasterDataSetupServiceImpl;
import com.org.coop.bs.config.DozerConfig;
import com.org.coop.canonical.master.beans.MasterDataBean;
import com.org.coop.canonical.master.beans.ModuleMasterBean;
import com.org.test.coop.junit.JunitTestUtil;
import com.org.test.coop.society.data.transaction.config.TestDataAppConfig;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.org.test.coop")
@ContextHierarchy({
	  @ContextConfiguration(classes={TestDataAppConfig.class, DozerConfig.class})
})
//@SpringApplicationConfiguration(classes={TestDataAppConfig.class, DozerConfig.class})
public class ModuleTest {
	private static final Logger logger = Logger.getLogger(ModuleTest.class);
	private MasterDataBean createModuleBean;
	private String createModuleJson = null;
	private ObjectMapper om = null;
	
	@Autowired
	private MasterDataSetupServiceImpl masterDataSetupServiceImpl;
	
	@Before
	public void runBefore() {
		try {
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			om = new ObjectMapper();
			om.setSerializationInclusion(Include.NON_NULL);
			om.setDateFormat(df);
			createModuleJson = JunitTestUtil.getFileContent("inputJson/master/module/createModule.json");
			
			createModuleBean = om.readValue(createModuleJson, MasterDataBean.class);
		} catch (Exception e) {
			logger.error("Error while initializing: ", e);
		}
	}
	
	@Test
	public void createModule() {
		try {
			createModuleBean = masterDataSetupServiceImpl.saveModuleRulesAndPermissions(createModuleBean);
			
			String srcJson = om.writeValueAsString(createModuleBean);
			masterDataSetupServiceImpl.getModuleRulesAndPermissions(((ModuleMasterBean)createModuleBean.getModules().toArray()[0]).getModuleName());
			String destJson = om.writeValueAsString(createModuleBean);
			
			assertEquals(srcJson, destJson);
		} catch (Exception e) {
			logger.error("Error while creating module: ", e);
		}
	}
	
	@Test
	public void createDuplicateModule() {
		try {
			createModuleBean = masterDataSetupServiceImpl.saveModuleRulesAndPermissions(createModuleBean);
			createModuleBean = masterDataSetupServiceImpl.saveModuleRulesAndPermissions(createModuleBean);
			assertNotEquals(createModuleBean.getErrorMsg(), null);
		} catch (Exception e) {
			logger.error("Error while creating module: ", e);
		}
	}
}
