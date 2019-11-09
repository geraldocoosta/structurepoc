package br.com.hepta.structure.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import br.com.hepta.structure.entitytest.Entity;
import br.com.hepta.structure.entitytest.dto.EntityDTO;
import br.com.hepta.structure.entitytest.dto.utils.DTOUtils;

public class EntityService {
	
	public void returnEntity() {
		HashSet<EntityDTO> hashSetEntityTest = new HashSet<EntityDTO>();
		List<Entity> roubado = DTOUtils.toEntityList(new ArrayList<>(hashSetEntityTest));
		System.out.println(roubado);
		EntityDTO dto = roubado.get(0).toDto();
		Entity entity = dto.toEntity();
		
//		Set<EntityDTO> daoSet = EntityUtils.toDaoSet(roubado);
//		System.out.println(daoSet);
	}
	
	
	
	
}
