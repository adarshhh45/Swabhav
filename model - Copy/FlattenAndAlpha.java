package com.aurionpro.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class FlattenAndAlpha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<String>> nested = Arrays.asList(Arrays.asList("Adarsh", "", "Samad",null,  "Dipesh"),
				Arrays.asList("cricket", "football", "",null));
		List<String> result = nested.stream().flatMap(Collection::stream).filter(s -> s != null && !s.isEmpty())
				.sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());
		
		System.out.println(result);
	}

}
