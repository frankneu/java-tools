package com.frank.neu.util;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.google.common.collect.Maps;
import com.google.common.hash.Hashing;

/**
 * 
 * @author frank
 *
 */
public class MyHashing {
	public static void main(String[] args) {
		int conflict9 = 0;
		int conflict11 = 0;
		int conflict20 = 0;
		Map<Integer, Integer> map_9 = Maps.newHashMap();
		Map<Integer, Integer> map_11 = Maps.newHashMap();
		Map<Integer, Integer> map_10 = Maps.newHashMap();
		Map<Integer, Integer> map_20 = Maps.newHashMap();
		Random r = new Random();
		double size = 100000000f;
		while(size -- > 0){
			int code = r.nextInt();
			int bucket_9 = Hashing.consistentHash(code, 9);
			int bucket_10 = Hashing.consistentHash(code, 10);
			int bucket_11 = Hashing.consistentHash(code, 11);
			int bucket_20 = Hashing.consistentHash(code, 20);
			if(map_9.containsKey(bucket_9)){
				int value = map_9.get(bucket_9) + 1;
				map_9.put(bucket_9, value);
			}else{
				map_9.put(bucket_9, 1);
			}
			if(map_10.containsKey(bucket_10)){
				int value = map_10.get(bucket_10) + 1;
				map_10.put(bucket_10, value);
			}else{
				map_10.put(bucket_10, 1);
			}
			if(map_11.containsKey(bucket_11)){
				int value = map_11.get(bucket_11) + 1;
				map_11.put(bucket_11, value);
			}else{
				map_11.put(bucket_11, 1);
			}
			if(map_20.containsKey(bucket_20)){
				int value = map_20.get(bucket_20) + 1;
				map_20.put(bucket_20, value);
			}else{
				map_20.put(bucket_20, 1);
			}
			if(bucket_9 != bucket_10){
				conflict9 ++;
			}
			if(bucket_11 != bucket_10){
				conflict11 ++;
			}
			if(bucket_20 != bucket_10){
				conflict20 ++;
			}
		}
		System.out.println(conflict9/100000000f);
		System.out.println(conflict11/100000000f);
		System.out.println(conflict20/100000000f);
		for(Entry<Integer, Integer> e : map_9.entrySet()){
			System.out.println(e.getValue()/100000000f);
		}
		for(Entry<Integer, Integer> e : map_10.entrySet()){
			System.out.println(e.getValue()/100000000f);
		}
		for(Entry<Integer, Integer> e : map_11.entrySet()){
			System.out.println(e.getValue()/100000000f);
		}
		for(Entry<Integer, Integer> e : map_20.entrySet()){
			System.out.println(e.getValue()/100000000f);
		}
		
	}
}
