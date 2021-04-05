package com.app.service.covid.api;

import java.util.List;

import com.app.error.GeneralException;
import com.app.model.CovidCasesArea;

public interface CovidMiningAPITotalCases {
	
	List<CovidCasesArea> getLast5RecordsMY();

	String getTotalfromDB();
	
	List<CovidCasesArea> getLast5RecordsMYWithSize(int size) throws GeneralException;
}
