package com.app.service.covid;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.CovidCasesBonusEntity;
import com.app.error.IDNotFoundException;
import com.app.mapper.CovidAreaBonusMapper;
import com.app.model.CovidCasesBonus;
import com.app.repository.covid.CovidCasesBonusRepository;
import fr.xebia.extras.selma.Selma;

//TODO: Practical bonus final
//complete this as Dependencies Injection Service

@Service
@Transactional
public class CovidBonusServiceImpl implements CovidBonusService {

	// hint
	// the method is similar to getCovidDesc() CovidServiceImpl file
	
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CovidBonusServiceImpl.class);
	
	@Autowired 
	CovidCasesBonusRepository covidCasesBonusRepository;
	
	@Override
	public
	List<CovidCasesBonus> bonus() throws Exception {
		//List<CovidCasesBonus> CovidCasesBonus = null;
		log.info("bonus() started");
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
		List<CovidCasesBonusEntity> covidCaseBonusEntities = covidCasesBonusRepository.findAll();
		List<CovidCasesBonus> covidCasesBonusList = new ArrayList<CovidCasesBonus>();
		if (covidCaseBonusEntities == null) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesBonusEntity entity : covidCaseBonusEntities) {
				CovidCasesBonus model = mapper.asResource(entity);
				covidCasesBonusList.add(model);
				log.info("entity total desc={}", entity.getDescription());
			}
			log.info(" CovidCasesBonus() return Size={}", covidCaseBonusEntities.size());
		}
		log.info("bonus() ends");
		return covidCasesBonusList;
	}
}
