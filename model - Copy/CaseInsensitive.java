package com.aurionpro.model;

import java.util.List;
import java.util.*;

public class CaseInsensitive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> words = Arrays.asList("Java", "java", "adarsh", "AdArSh", "ADARSH", "python");
		long count = words.stream().map(String::toLowerCase).distinct().count();
		System.out.println("Total number of distinct words are: "+count); // Output: 3
	}
}
