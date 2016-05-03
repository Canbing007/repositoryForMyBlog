package com.org.coop.retail.bs.mapper.converter;

import org.dozer.CustomConverter;
import org.dozer.DozerConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.coop.canonical.beans.CustomerNotificationBean;
import com.org.coop.retail.entities.BranchMaster;
import com.org.coop.retail.entities.Customer;
import com.org.coop.retail.entities.CustomerNotification;
import com.org.coop.retail.repositories.CustomerRepository;
import com.org.coop.retail.repositories.RetailBranchMasterRepository;

@Component("customerNotificationCC")
public class CustomerNotificationCC extends DozerConverter<CustomerNotificationBean, CustomerNotification> implements MapperAware, CustomConverter {
	@Autowired
	private RetailBranchMasterRepository branchMasterRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerNotificationCC() {
		super(CustomerNotificationBean.class, CustomerNotification.class);
	}
	
	public CustomerNotificationCC(Class prototypeA, Class prototypeB) {
		super(prototypeA, prototypeB);
	}
	
	public void setMapper(Mapper arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CustomerNotificationBean convertFrom(CustomerNotification src, CustomerNotificationBean dest) {
		return null;
	}

	@Override
	public CustomerNotification convertTo(CustomerNotificationBean src, CustomerNotification dest) {
		if(src != null) {
			BranchMaster branch = branchMasterRepository.findOne(src.getBranchId());
			Customer customer = customerRepository.findOne(src.getCustomerId());
			dest.setBranchMaster(branch);
			dest.setCustomer(customer);
		}
		return dest;
	}
}