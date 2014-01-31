package com.example1.wocao;

import java.util.Comparator;
// this is a compare and sort the array for sorting a counter
public class countercmp implements Comparator<counter>{

	@Override
	public int compare(counter lhs, counter rhs) {
		// TODO Auto-generated method stub
		return lhs.count-rhs.count;
	}

}
