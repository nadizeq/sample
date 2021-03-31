package com.app.service.covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entity.CovidCasesBonusEntity;
import com.app.error.IDNotFoundException;
import com.app.mapper.CovidAreaBonusMapper;
import com.app.model.CovidCasesBonus;
import com.app.repository.covid.CovidCasesBonusRepository;

import fr.xebia.extras.selma.Selma;
import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

//TODO: Practical bonus final
//complete this as Dependencies Injection Service
@Slf4j
@Service
@Transactional
public class CovidBonusServiceImpl implements CovidBonusService {

	// hint
	// the method is similar to getCovidDesc() CovidServiceImpl file
		
	@Autowired 
	CovidCasesBonusRepository covidCasesBonusRepository;
	
	//retrieve table trx_covid_case_bonus function
	@Override
	public
	List<CovidCasesBonus> bonus() throws Exception {
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
				log.info("entity description={}", entity.getDescription());
			}
			log.info(" CovidCasesBonusEntities() return Size={}", covidCaseBonusEntities.size());
		}
		log.info("bonus() ends");
		return covidCasesBonusList;
		
	}
	
	// adding record into table trx_covid_case_bonus function
	@Override
	public CovidCasesBonus addCovidBonus(String desc) {
		
		//add codes below for adding it and modify it to suit with bonus table
		log.info("addCovidBonus started");

		CovidCasesBonus covidCasesBonus = null;
		CovidCasesBonusEntity covidCasesBonusEntity = new CovidCasesBonusEntity();

		covidCasesBonusEntity.setDescription(desc);

		CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);

		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();

		covidCasesBonus = mapper.asResource(savedEntity);

		return covidCasesBonus;
		
	}
	
	@Override
	public int deleteCovidBonus(Long id) throws Exception{
		log.info("deleteCovidBonus started");
		
		try {

			Optional<CovidCasesBonusEntity> entityOptional = covidCasesBonusRepository.findById(id);

			log.info("Entity found == " + entityOptional.isPresent());

			if (entityOptional.isPresent()) {
				CovidCasesBonusEntity covidCasesBonusEntity = entityOptional.get();
				covidCasesBonusRepository.delete(covidCasesBonusEntity);
				return 1;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("deleteCovid() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		return 0;
	}
	
	@Override
	public CovidCasesBonus putCovidBonus(CovidCasesBonus covidCasesBonus) throws Exception {
		log.info("putCovidBonus() started, covidCasesBonus={}", covidCasesBonus);
		try {
			CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();

			CovidCasesBonusEntity covidCasesBonusEntity= mapper.asEntity(covidCasesBonus);
			CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);
			covidCasesBonus = mapper.asResource(savedEntity);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("putCovidBonus() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info("putCovidBonys() ends, covidCasesBonysSaved={}", covidCasesBonus);
		return covidCasesBonus;
		
	}
	
	//post method
	public CovidCasesBonus postCovidBonus(CovidCasesBonus covidCasesBonus) throws Exception{
		log.info("postCovid() starts, covidCasesDesc={}",covidCasesBonus);
		CovidAreaBonusMapper mapper = Selma.builder(CovidAreaBonusMapper.class).build();
	    CovidCasesBonusEntity covidCasesBonusEntity= mapper.asEntity(covidCasesBonus);
	    CovidCasesBonusEntity savedEntity = covidCasesBonusRepository.save(covidCasesBonusEntity);
	    covidCasesBonus = mapper.asResource(savedEntity);
			
		log.info("postCovidBonus() end, covidCasesBonus={}",covidCasesBonus);
    	return covidCasesBonus;
		
	}
	
	//delete by description
	@Override
	public List<CovidCasesBonus> deleteCovidBonusDescription(String desc) throws Exception{
		
		log.info("deleteCovidSoap() started desc={}", desc);
		covidCasesBonusRepository.deleteBonusDescWithCondition(desc);
		
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
			log.info(" getCovidBonus() return Size={}", covidCaseBonusEntities.size());
		}

		return covidCasesBonusList;
				
	}
	
	@Override
	public List<String> deleteDuplicateRecord(){
		List<String> lists = new ArrayList<String>();
		lists=covidCasesBonusRepository.findDuplicateNdelete();
		log.info("lists value={}",lists);
		
		return lists;
		
	}
}
