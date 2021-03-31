package com.app.service.covid;

import java.util.List;

import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;

public interface CovidService {

	List<CovidCasesArea> getCovid();

	List<CovidCasesDesc> getCovidDesc();

	CovidCasesDesc addCovid(String desc);
	
	int deleteCovid(Long id) throws Exception;
	
	CovidCasesDesc postCovid(CovidCasesDesc covidCasesDesc) throws Exception;
	
	CovidCasesDesc putCovid(CovidCasesDesc covidCasesDesc) throws Exception;
	
	List<CovidCasesDesc> deleteCovidDesc(String desc) throws Exception;
	
	//List<CovidCasesDesc> deleteCovidDuplicateDesc(String desc) throws Exception;
}
