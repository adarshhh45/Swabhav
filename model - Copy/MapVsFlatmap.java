package com.aurionpro.model;

import java.util.*;
import java.util.stream.Collectors;

public class MapVsFlatmap {
	public static void main(String[] args) {
		List<String> sentences = Arrays.asList("xyz is a cricketer", "ram and rahul are friends");

		// map starts here
		List<List<String>> mapped = sentences.stream().map(s -> Arrays.asList(s.split("\\s+")))
				.collect(Collectors.toList());
		System.out.println(mapped);

		// flatmap starts here
		List<String> flatMapped = sentences.stream().flatMap(s -> Arrays.stream(s.split("\\s+"))).distinct()
				.sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());
		System.out.println(flatMapped);
	}
}