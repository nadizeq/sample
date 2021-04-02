package com.app.service.covid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.error.IDNotFoundException;
import com.app.mapper.CovidAreaDescMapper;
import com.app.mapper.CovidCasesAreaMapper;
import com.app.repository.covid.CovidCasesDescRepository;
import com.app.repository.covid.CovidCasesRepository;
import com.app.entity.CovidCasesDescEntity;
import com.app.entity.CovidCasesAreaEntity;
import com.app.model.CovidCasesArea;
import com.app.model.CovidCasesDesc;

import fr.xebia.extras.selma.Selma;

@Service
@Transactional
public class CovidServiceImpl implements CovidService {

	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(CovidServiceImpl.class);

	@Autowired
	CovidCasesRepository covidCasesRepository;
	
	@Autowired
	CovidCasesDescRepository covidCasesDescRepository;

	@Override
	public List<CovidCasesArea> getCovid() {
		log.info("getCovid started");
		CovidCasesAreaMapper mapper = Selma.builder(CovidCasesAreaMapper.class).build();
		List<CovidCasesAreaEntity> covidCaseEntities = covidCasesRepository.findAll();
		List<CovidCasesArea> covidCasesAreaList = new ArrayList<>();
		if (covidCaseEntities.isEmpty() == true) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesAreaEntity covidCasesEntity : covidCaseEntities) {
				CovidCasesArea covidCasesArea = mapper.asResource(covidCasesEntity);
				covidCasesAreaList.add(covidCasesArea);
				log.info("covidCasesEntity total Cases={}", covidCasesEntity.getCases());
			}
			log.info(" getCovid() return Size={}", covidCaseEntities.size());
		}

		return covidCasesAreaList;

	}

	@Override
	public List<CovidCasesDesc> getCovidDesc() {
		log.info("getCovidDesc started");
		CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();
		List<CovidCasesDescEntity> covidCaseDescEntities = covidCasesDescRepository.findAll();
		List<CovidCasesDesc> covidCasesDescList = new ArrayList<CovidCasesDesc>();
		if (covidCaseDescEntities == null) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesDescEntity entity : covidCaseDescEntities) {
				CovidCasesDesc model = mapper.asResource(entity);
				covidCasesDescList.add(model);
				log.info("entity total desc={}", entity.getDescription());
			}
			log.info(" getCovidDesc() return Size={}", covidCaseDescEntities.size());
		}

		return covidCasesDescList;

	}
	
	// TODO: Related to Practical 4 (Add)
	@Override
	public CovidCasesDesc addCovid(String desc) {
		log.info("addCovid started");

		CovidCasesDesc covidCasesDesc = null;
		CovidCasesDescEntity covidCasesDescEntity = new CovidCasesDescEntity();

		covidCasesDescEntity.setDescription(desc);

		CovidCasesDescEntity savedEntity = covidCasesDescRepository.save(covidCasesDescEntity);

		CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();

		covidCasesDesc = mapper.asResource(savedEntity);

		return covidCasesDesc;

	}

	// TODO: Related to Practical 4 (Delete)
	@Override
	public int deleteCovid(Long id) throws Exception {
		log.info("deleteCovid started");
		
		try {

			Optional<CovidCasesDescEntity> entityOptional = covidCasesDescRepository.findById(id);

			log.info("Entity found == " + entityOptional.isPresent());

			if (entityOptional.isPresent()) {
				CovidCasesDescEntity covidAreaDescEntity = entityOptional.get();
				covidCasesDescRepository.delete(covidAreaDescEntity);
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
	public CovidCasesDesc putCovid(CovidCasesDesc covidCasesDesc) throws Exception {
		log.info("putCovid() started, covidCasesDesc={}", covidCasesDesc);
		try {
			CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();

			CovidCasesDescEntity covidCasesDescEntity= mapper.asEntity(covidCasesDesc);
			CovidCasesDescEntity savedEntity = covidCasesDescRepository.save(covidCasesDescEntity);
			covidCasesDesc = mapper.asResource(savedEntity);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("deleteCovid() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		log.info("putCovid() ends, covidCasesDescSaved={}", covidCasesDesc);
		return covidCasesDesc;
		
	}
	@Override
	public CovidCasesDesc postCovid(CovidCasesDesc covidCasesDesc) throws Exception{
		log.info("postCovid() starts, covidCasesDesc={}",covidCasesDesc);
		try {
			CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();
	    	CovidCasesDescEntity covidCasesDescEntity= mapper.asEntity(covidCasesDesc);
	    	CovidCasesDescEntity savedEntity = covidCasesDescRepository.save(covidCasesDescEntity);
	    	covidCasesDesc = mapper.asResource(savedEntity);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("deleteCovid() exception " + e.getMessage());
			throw new Exception(e.getMessage());
		}
		log.info("postCovid() end, covidCasesDesc={}",covidCasesDesc);
    	return covidCasesDesc;
		
	}
	
	@Override
	public List<CovidCasesDesc> deleteCovidDesc(String desc) throws Exception{
		
		log.info("deleteCovidSoap() started desc={}", desc);
		covidCasesDescRepository.deleteDescWithCondition(desc);
		
		CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();
		List<CovidCasesDescEntity> covidCaseDescEntities = covidCasesDescRepository.findAll();
		List<CovidCasesDesc> covidCasesDescList = new ArrayList<CovidCasesDesc>();
		if (covidCaseDescEntities == null) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesDescEntity entity : covidCaseDescEntities) {
				CovidCasesDesc model = mapper.asResource(entity);
				covidCasesDescList.add(model);
				log.info("entity total desc={}", entity.getDescription());
			}
			log.info(" getCovidDesc() return Size={}", covidCaseDescEntities.size());
		}

		return covidCasesDescList;		
	}
	/*
	@Override
	public List<CovidCasesDesc> deleteCovidDuplicateDesc(String desc) throws Exception{
		
		log.info("deleteCovidSoap() started desc={}", desc);
		covidCasesDescRepository.findDuplicate(desc);
		
		CovidAreaDescMapper mapper = Selma.builder(CovidAreaDescMapper.class).build();
		List<CovidCasesDescEntity> covidCaseDescEntities = covidCasesDescRepository.findAll();
		List<CovidCasesDesc> covidCasesDescList = new ArrayList<CovidCasesDesc>();
		if (covidCaseDescEntities == null) {
			throw new IDNotFoundException(0L);
		} else {

			for (CovidCasesDescEntity entity : covidCaseDescEntities) {
				CovidCasesDesc model = mapper.asResource(entity);
				covidCasesDescList.add(model);
				log.info("entity total desc={}", entity.getDescription());
			}
			log.info(" getCovidDesc() return Size={}", covidCaseDescEntities.size());
		}

		return covidCasesDescList;
	}
	*/
}
