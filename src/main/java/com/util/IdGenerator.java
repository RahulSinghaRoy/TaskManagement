package com.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.IdMaster;
import com.repository.IdMasterRepository;

@Component
public class IdGenerator {

	@Autowired
	private IdMasterRepository idMasterRepository;
	
	public String generateId(String fieldName) {
		IdMaster idMaster = idMasterRepository.findByName(fieldName);
		int oldValue = idMaster.getValue();
		String prefix = idMaster.getPrefix();
		idMaster.setValue(oldValue + 1);
		idMasterRepository.save(idMaster);
		
		return prefix + String.format("%04d", oldValue);
	}
}
