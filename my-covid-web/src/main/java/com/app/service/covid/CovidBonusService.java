package com.app.service.covid;

import java.util.List;

import com.app.error.GeneralException;
import com.app.model.CovidCasesBonus;

public interface CovidBonusService {

	List<CovidCasesBonus> bonus();
	
	CovidCasesBonus addCovidBonus(String desc);
	
	int deleteCovidBonus(Long id) throws GeneralException;
	
	CovidCasesBonus putCovidBonus(CovidCasesBonus covidCasesBonus) throws GeneralException;
	
	CovidCasesBonus postCovidBonus(CovidCasesBonus covidCasesBonus);
	
	List<CovidCasesBonus> deleteCovidBonusDescription(String desc);
	
	List<String> duplicateRecord();
}
