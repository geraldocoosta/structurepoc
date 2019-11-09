package br.com.hepta.structure.entitytest.dto.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class DTOUtils {
	
	public static <T extends GenericDTO<T,R>, R> Set<R> toEntitySet(Set<T> t) {
		Set<T> gropuOfTypeOrElse = Optional.ofNullable(t).orElse(new HashSet<>());
		return gropuOfTypeOrElse.stream().map(type -> type.toEntity()).collect(Collectors.toSet());
	}

	public static <T extends GenericDTO<T,R>, R> List<R> toEntityList(List<T> t) {
		List<T> gropuOfTypeOrElse = Optional.ofNullable(t).orElse(new ArrayList<>());
		return gropuOfTypeOrElse.stream().map(type -> type.toEntity()).collect(Collectors.toList());
	}
	

}
