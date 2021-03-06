package com.org.coop.retail.servicehelper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.coop.bs.mapper.MasterDataMappingImpl;
import com.org.coop.retail.data.entities.BranchMaster;
import com.org.coop.retail.data.entities.MaterialMaster;
import com.org.coop.retail.data.entities.RetailData;
import com.org.coop.retail.data.repositories.MaterialMasterRepository;
import com.org.coop.retail.data.repositories.RetailBranchMasterRepository;

@Service
public class RetailMasterDataSetupServiceHelperImpl {
	
	@Autowired
	private MasterDataMappingImpl masterDataMapping;
	
	@Autowired
	private MaterialMasterRepository materialMasterRepository;
	
	@Autowired
	private RetailBranchMasterRepository branchMasterRepository;
	
	@Transactional
	public RetailData saveMasterData(RetailData retailData) {
		if(retailData.getBranch().getMaterials() != null && retailData.getBranch().getMaterials().size() > 0) {
			int branchId = retailData.getBranch().getBranchId();
			BranchMaster bm = null;
			if(branchId != 0) {
				bm = branchMasterRepository.findByBranchId(branchId);
			}
			if(bm == null) {
				bm = new BranchMaster();
			}
			masterDataMapping.mapBean(retailData.getBranch(), bm);
			branchMasterRepository.save(bm);
			retailData.setBranch(bm);
		}
		return retailData;
	}
	
	@Transactional
	public RetailData getMasterData(RetailData retailData) {
		int branchId = retailData.getBranchId();
		int materialId = retailData.getMaterialId();

		BranchMaster branch = branchMasterRepository.findMaterialByMaterialId(branchId, materialId);

		retailData.setBranch(branch);

		return retailData;
	}
}
