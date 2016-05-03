package com.org.coop.bs.util;

public class RetailBusinessConstants {
	public static String ENTRY_TYPE_NEW 				= "NEW";
	public static String ENTRY_TYPE_PURCHASED 			= "PURCHASED";
	public static String ENTRY_TYPE_SALE	 			= "SALE";
	public static String ENTRY_TYPE_YR_CLOSE_OPENING 	= "YR_CLOSE_OPENING";
	public static String ENTRY_TYPE_OPENING				= "OPENING";
	public static String ENTRY_TYPE_STOCK_RETURN	 	= "STOCK_RETURN";
	public static String ENTRY_TYPE_SHORTAGE			= "SHORTAGE";
	public static String ENTRY_TYPE_TRANSFER			= "TRANSFER";
	public static String ENTRY_TYPE_TRANSFER_RETURN		= "TRANSFER_RETURN";
	public static String ENTRY_TYPE_MISC	 			= "MISC";
	
	
	public static String EXCEPTION_COMMON 					= "1000";
	public static String EXCEPTION_RETAIL 					= "1200";
	public static String EXCEPTION_RETAIL_PAYMENT 			= "1201";
	public static String EXCEPTION_RETAIL_PAYMENT_DELETE	= "1202";
	public static String EXCEPTION_RETAIL_MATERIAL_MGMT 	= "1210";
	public static String EXCEPTION_RETAIL_CUSTOMER_MGMT 	= "1220";
	public static String EXCEPTION_RETAIL_VENDOR_MGMT 		= "1230";
	public static String EXCEPTION_RETAIL_STOCK_ENTRY 		= "1240";
	public static String EXCEPTION_RETAIL_STOCK_SALE 		= "1250";
	public static String EXCEPTION_RETAIL_MASTER_DATA_SETUP	= "1260";
	public static String EXCEPTION_CUSTOMER_ACCOUNT			= "1270";
	
	public static String EXCEPTION_RETAIL_YEAR_CLOSED		= "1280";
	
	public static String MODE_OF_PAYMENT_CASH 			= "CASH";
	public static String MODE_OF_PAYMENT_CARD 			= "CARD";
	public static String MODE_OF_PAYMENT_CHEQUE 		= "CHEQUE";
	public static String MODE_OF_PAYMENT_CREDIT 		= "CREDIT";
	public static String MODE_OF_PAYMENT_CRDR 			= "CRDR";  // Creditor Debtor account. Advance can be issued from this account
	public static String MODE_OF_PAYMENT_ADVANCE 		= "ADVANCE";
	public static String MODE_OF_PAYMENT_LOAN 			= "LOAN";
	public static String MODE_OF_PAYMENT_LOAN_OTHER_BANK= "LOAN-OTHERBANK";
	
	public static String CREDIT = "Cr";
	public static String DEBIT = "Dr";
	
	public static String TYPE_OF_ACCOUNT_CRDR 			= "CRDR";
	public static String TYPE_OF_ACCOUNT_SAVINGS		= "SB"; 
	
	public static int LEDGER_CODE_VAT_COLLECTION		= 18803; 
	public static int LEDGER_CODE_VAT_PAID				= 18803; 
	
	public static int LEDGER_CODE_DISCOUNT_COLLECTION	= 44104; 
	public static int LEDGER_CODE_DISCOUNT_PAID			= 37908; 
	
	// When pagination is not required
	public static int ALL_RECORDS_DEFAULT_VALUE 		= 100000;
}
