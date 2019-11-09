package br.com.hepta.structure.entitytest.util;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class EntityUtils {
	
	public static <T extends GenericEntity<T,R>, R> Set<R> toDaoSet(Set<T> t) {
		Set<T> gropuOfTypeOrElse = Optional.ofNullable(t).orElse(new HashSet<>());
		return gropuOfTypeOrElse.stream().map(type -> type.toDto()).collect(Collectors.toSet());
	}
	
}
