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
	private MasterDataBean updateModuleBean;
	private String createModuleJson = null;
	private String updateModuleJson = null;
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
			
			
			updateModuleJson = JunitTestUtil.getFileContent("inputJson/master/module/updateModule.json");
			updateModuleBean = om.readValue(updateModuleJson, MasterDataBean.class);
		} catch (Exception e) {
			logger.error("Error while initializing: ", e);
		}
	}
	@Test
	public void testModule() {
		createModule();
		createDuplicateModule();
		updateModule();
		getValidModule();
		getNonExistanceModule();
	}

	private void createModule() {
		try {
			MasterDataBean moduleBean = masterDataSetupServiceImpl.saveModuleRulesAndPermissions(createModuleBean);
			
			String srcJson = om.writeValueAsString(moduleBean);
			moduleBean = masterDataSetupServiceImpl.getModuleRulesAndPermissions(((ModuleMasterBean)createModuleBean.getModules().toArray()[0]).getModuleName());
			String destJson = om.writeValueAsString(moduleBean);
			
			assertEquals(srcJson, destJson);
			
		} catch (Exception e) {
			logger.error("Error while creating module/duplicate module: ", e);
		}
	}
	
	private void updateModule() {
		try {
			MasterDataBean moduleBean = masterDataSetupServiceImpl.saveModuleRulesAndPermissions(updateModuleBean);

			String srcJson = om.writeValueAsString(moduleBean);
			moduleBean = masterDataSetupServiceImpl.getModuleRulesAndPermissions(((ModuleMasterBean)createModuleBean.getModules().toArray()[0]).getModuleName());
			String destJson = om.writeValueAsString(moduleBean);
			
			assertEquals(srcJson, destJson);
		} catch (Exception e) {
			logger.error("Error while creating module: ", e);
		}
	}
	
	private void createDuplicateModule() {
		try {
			// Create duplicate module 
			MasterDataBean moduleBean = masterDataSetupServiceImpl.saveModuleRulesAndPermissions(createModuleBean);
			assertNotEquals(moduleBean.getErrorMsg(), null);
		} catch (Exception e) {
			logger.error("Error while creating module: ", e);
		}
	}
	
	private void getValidModule() {
		try {
			MasterDataBean moduleBean = masterDataSetupServiceImpl.getModuleRulesAndPermissions("RD");
			assertEquals(moduleBean.getErrorMsg(), null);
		} catch (Exception e) {
			logger.error("Error while creating module: ", e);
		}
	}
	
	private void getNonExistanceModule() {
		try {
			MasterDataBean moduleBean = masterDataSetupServiceImpl.getModuleRulesAndPermissions("RD1");
			assertNotEquals(moduleBean.getErrorMsg(), null);
		} catch (Exception e) {
			logger.error("Error while creating module: ", e);
		}
	}
}
