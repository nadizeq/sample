package com.app.service.covid;

import java.util.List;

import com.app.error.GeneralException;
import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;

public interface CovidService {

	List<CovidCasesArea> getCovid();

	List<CovidCasesDesc> getCovidDesc();

	CovidCasesDesc addCovid(String desc);
	
	int deleteCovid(Long id) throws GeneralException;
	
	CovidCasesDesc postCovid(CovidCasesDesc covidCasesDesc) throws GeneralException;
	
	CovidCasesDesc putCovid(CovidCasesDesc covidCasesDesc) throws GeneralException;
	
	List<CovidCasesDesc> deleteCovidDesc(String desc);
	
}
