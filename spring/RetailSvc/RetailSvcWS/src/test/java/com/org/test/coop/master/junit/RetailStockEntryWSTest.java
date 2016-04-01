package com.org.test.coop.master.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.coop.bs.config.DozerConfig;
import com.org.coop.bs.util.AdminSvcCommonUtil;
import com.org.coop.canonical.beans.RetailStockEntryBean;
import com.org.coop.canonical.beans.RetailVatRegNoBean;
import com.org.coop.canonical.beans.UIModel;
import com.org.coop.retail.bs.config.RetailDozerConfig;
import com.org.coop.retail.servicehelper.RetailBranchSetupServiceHelperImpl;
import com.org.coop.society.data.admin.repositories.BranchMasterRepository;
import com.org.coop.society.data.transaction.config.DataAppConfig;
import com.org.test.coop.junit.JunitTestUtil;
import com.org.test.coop.society.data.transaction.config.TestDataAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages = "com.org.test.coop")
@EnableAutoConfiguration(exclude = { DataAppConfig.class, DozerConfig.class})
@ContextHierarchy({
	  @ContextConfiguration(classes={TestDataAppConfig.class, RetailDozerConfig.class})
})
@WebAppConfiguration
public class RetailStockEntryWSTest {
	private static final Logger logger = Logger.getLogger(RetailStockEntryWSTest.class);
	
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	
	private String addStockEntryJson = null;
	private String addAnotherStockEntryJson = null;
	
	private ObjectMapper om = null;
	
	@Autowired
	private RetailBranchSetupServiceHelperImpl branchSetupServiceImpl;
	
	@Autowired
	private BranchMasterRepository branchMasterRepository;
	
	@Autowired
	private AdminSvcCommonUtil adminSvcCommonUtil;
	
	@Before
	public void runBefore() {
		try {
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			om = new ObjectMapper();
			om.setSerializationInclusion(Include.NON_NULL);
			om.setDateFormat(df);
			addStockEntryJson = JunitTestUtil.getFileContent("inputJson/retail/branch/stockin/addStockEntry.json");
			addAnotherStockEntryJson = JunitTestUtil.getFileContent("inputJson/retail/branch/stockin/addAnotherStockEntry.json");
		} catch (Exception e) {
			logger.error("Error while initializing: ", e);
			Assert.fail("Error while initializing: ");
		}
	}
	@Test
	public void testRetailBranch() {
		addStockEntry();
		addAnotherStockEntry();
		getAllStocksForABranch();
	}

	
	private void addStockEntry() {
		try {
			MvcResult result = this.mockMvc.perform(post("/rest/saveStockEntries")
				 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
				 .content(addStockEntryJson)
				).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
			
			UIModel uiModel = getUIModel(result, "outputJson/retail/branch/stockin/addStockEntry.json");
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String dateInString = "28-03-2016";
			Date date = formatter.parse(dateInString);

			
			assertNull(uiModel.getErrorMsg());
			assertEquals(3, uiModel.getBranchBean().getStockEntries().size());
			for(RetailStockEntryBean stockBean : uiModel.getBranchBean().getStockEntries()) {
				switch (stockBean.getStockId()) {
					case 1: 
						assertEquals(1, stockBean.getStockId());
						assertEquals(2, stockBean.getBranchId());
						assertEquals(1, stockBean.getMaterialId());
						assertEquals(1, stockBean.getVendorId());
						assertEquals(date, stockBean.getEntryDate());
						assertEquals("AZ1001", stockBean.getBatch());
						assertEquals(new BigDecimal("100"), stockBean.getQty());
						assertEquals(new BigDecimal("100.50"), stockBean.getPurchasePrice());
						assertEquals("86/123", stockBean.getChallanNo());
						assertEquals("86-123", stockBean.getBillNo());
						assertEquals("N", stockBean.getOpeningEntryInd());
						
						break;
					case 2: 
						assertEquals(2, stockBean.getStockId());
						assertEquals(2, stockBean.getBranchId());
						assertEquals(1, stockBean.getMaterialId());
						assertEquals(2, stockBean.getVendorId());
						assertEquals(date, stockBean.getEntryDate());
						assertEquals("AB1001", stockBean.getBatch());
						assertEquals(new BigDecimal("100"), stockBean.getQty());
						assertEquals(new BigDecimal("100.00"), stockBean.getPurchasePrice());
						assertEquals("86/123", stockBean.getChallanNo());
						assertEquals("86-123", stockBean.getBillNo());
						assertEquals("N", stockBean.getOpeningEntryInd());
						break;
						
					case 3: 
						assertEquals(3, stockBean.getStockId());
						assertEquals(2, stockBean.getBranchId());
						assertEquals(2, stockBean.getMaterialId());
						assertEquals(1, stockBean.getVendorId());
						assertEquals(date, stockBean.getEntryDate());
						assertEquals("AA1001", stockBean.getBatch());
						assertEquals(new BigDecimal("100"), stockBean.getQty());
						assertEquals(new BigDecimal("10.00"), stockBean.getPurchasePrice());
						assertEquals("86/123", stockBean.getChallanNo());
						assertEquals("86-124", stockBean.getBillNo());
						assertEquals("N", stockBean.getOpeningEntryInd());
						break;
				}
				
			}
		} catch(Exception e) {
			logger.error("Error while adding stock entry", e);
			Assert.fail("Error while adding stock entry");
		}
	}
	
	private void addAnotherStockEntry() {
		try {
			MvcResult result = this.mockMvc.perform(post("/rest/saveStockEntries")
				 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
				 .content(addAnotherStockEntryJson)
				).andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andReturn();
			
			UIModel uiModel = getUIModel(result, "outputJson/retail/branch/stockin/addAnotherStockEntry.json");
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			String dateInString = "30-03-2016";
			Date date = formatter.parse(dateInString);

			
			assertNull(uiModel.getErrorMsg());
			assertEquals(3, uiModel.getBranchBean().getStockEntries().size());
			for(RetailStockEntryBean stockBean : uiModel.getBranchBean().getStockEntries()) {
				switch (stockBean.getStockId()) {
					case 4: 
						assertEquals(4, stockBean.getStockId());
						assertEquals(2, stockBean.getBranchId());
						assertEquals(1, stockBean.getMaterialId());
						assertEquals(1, stockBean.getVendorId());
						assertEquals(date, stockBean.getEntryDate());
						assertEquals("AZ1002", stockBean.getBatch());
						assertEquals(new BigDecimal("10"), stockBean.getQty());
						assertEquals(new BigDecimal("100.60"), stockBean.getPurchasePrice());
						assertEquals("86/125", stockBean.getChallanNo());
						assertEquals("86-125", stockBean.getBillNo());
						assertEquals("N", stockBean.getOpeningEntryInd());
						
						break;
					case 5: 
						assertEquals(5, stockBean.getStockId());
						assertEquals(2, stockBean.getBranchId());
						assertEquals(1, stockBean.getMaterialId());
						assertEquals(2, stockBean.getVendorId());
						assertEquals(date, stockBean.getEntryDate());
						assertEquals("AB1002", stockBean.getBatch());
						assertEquals(new BigDecimal("20"), stockBean.getQty());
						assertEquals(new BigDecimal("100.10"), stockBean.getPurchasePrice());
						assertEquals("86/125", stockBean.getChallanNo());
						assertEquals("86-125", stockBean.getBillNo());
						assertEquals("N", stockBean.getOpeningEntryInd());
						break;
						
					case 6: 
						assertEquals(6, stockBean.getStockId());
						assertEquals(2, stockBean.getBranchId());
						assertEquals(2, stockBean.getMaterialId());
						assertEquals(1, stockBean.getVendorId());
						assertEquals(date, stockBean.getEntryDate());
						assertEquals("AA1002", stockBean.getBatch());
						assertEquals(new BigDecimal("30"), stockBean.getQty());
						assertEquals(new BigDecimal("10.10"), stockBean.getPurchasePrice());
						assertEquals("86/126", stockBean.getChallanNo());
						assertEquals("86-125", stockBean.getBillNo());
						assertEquals("N", stockBean.getOpeningEntryInd());
						break;
				}
				
			}
		} catch(Exception e) {
			logger.error("Error while adding stock entry", e);
			Assert.fail("Error while adding stock entry");
		}
	}
	
	private void getAllStocksForABranch() {
		try {
			MvcResult result = this.mockMvc.perform(get("/rest/getStockEntries?branchId=2")
					 .contentType("application/json").header("Authorization", "Basic " + Base64.getEncoder().encodeToString("ashish:ashish".getBytes()))
					).andExpect(status().isOk())
					.andExpect(content().contentType("application/json"))
					.andReturn();
				
			UIModel uiModel = getUIModel(result, "outputJson/retail/branch/stockin/getAllStocksForABranch.json");
			assertNull(uiModel.getErrorMsg());
		} catch(Exception e) {
			logger.error("Error while getting stocks", e);
			Assert.fail("Error while getting stocks");
		}
	}
	
	private UIModel getUIModel(MvcResult result)
			throws UnsupportedEncodingException, IOException,
			JsonParseException, JsonMappingException {
		String json = result.getResponse().getContentAsString();
		UIModel createBranchBean = om.readValue(json, UIModel.class);
		return createBranchBean;
	}
	
	private UIModel getUIModel(MvcResult result, String path)
			throws UnsupportedEncodingException, IOException,
			JsonParseException, JsonMappingException {
		UIModel createBranchBean = getUIModel(result);
		JunitTestUtil.writeJSONToFile(createBranchBean, path);
		return createBranchBean;
	}
	
}
