package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesBonus;

public interface CovidBonusService {

	List<CovidCasesBonus> bonus() throws Exception;
	
	CovidCasesBonus addCovidBonus(String desc);
	
	int deleteCovidBonus(Long id) throws Exception;
	
	CovidCasesBonus putCovidBonus(CovidCasesBonus covidCasesBonus) throws Exception;
	
	CovidCasesBonus postCovidBonus(CovidCasesBonus covidCasesBonus) throws Exception;
	
	List<CovidCasesBonus> deleteCovidBonusDescription(String desc) throws Exception;
	
	List<String> deleteDuplicateRecord();
}
