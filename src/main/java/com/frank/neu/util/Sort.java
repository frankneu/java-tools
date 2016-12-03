package com.frank.neu.util;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.Gson;

/**
 * 排序算法
 * @author frank
 *
 */
public class Sort {

	/**
	 * 插入排序
	 * 特点：stable sort、In-place sort
		最优复杂度：当输入数组就是排好序的时候，复杂度为O(n)，而快速排序在这种情况下会产生O(n^2)的复杂度。
		最差复杂度：当输入数组为倒序时，复杂度为O(n^2)
		插入排序比较适合用于“少量元素的数组”。
		其实插入排序的复杂度和逆序对的个数一样，当数组倒序时，逆序对的个数为n(n-1)/2，因此插入排序复杂度为O(n^2)。
	 * @param list
	 * @return
	 */
	public static List<Integer> insertSort(List<Integer> list){
		Gson gson = new Gson();
		for(int i = 1; i < list.size(); ++i){
			int source = list.get(i);
			System.out.println(i + "  before:" + gson.toJson((list)));
			list.remove(i);			
			System.out.println(i + "  before:" + source);
			if(-1 == source){
				System.out.println(i + "  before:" + gson.toJson((list)));
			}
			int j = 0;
			for(; j < i; ++j ){
				if(source >= list.get(j)){
					list.add(j, source);
					System.out.println(j + "  after :" + gson.toJson((list)));
					break;					
				}
			}
			if(j == i)
				list.add(i, source);
		}
		return list;
	}
	
	/**
	 * 冒泡排序
	 * 特点：stable sort、In-place sort
		思想：通过两两交换，像水中的泡泡一样，小的先冒出来，大的后冒出来。
		最坏运行时间：O(n^2)
		最佳运行时间：O(n^2)（当然，也可以进行改进使得最佳运行时间为O(n)）
	 * @param list
	 * @return
	 */
	public static List<Integer> bubbleSort(List<Integer> list){
		boolean flag = false;
		Gson gson = new Gson();
		for(int i = 0; i<list.size(); ++i){
			if(flag)
				return list;
			for(int j = list.size()-1; j>i; j--){
				if(list.get(i) < list.get(j)){
					int a = list.get(i);
					int b = list.get(j);
					list.remove(j);
					list.remove(i);
					list.add(i, b);
					list.add(j, a);
					System.out.println(a + " " + b + "  :" + gson.toJson((list)));
					flag = false;
				}
			}
		}
		return list;
	}
	
	/**
	 * 选择排序
	 * 特性：In-place sort，unstable sort。
		思想：每次找一个最小值。
	 * @param list
	 * @return
	 */
	public static List<Integer> selectSort(List<Integer> list){
		for(int i=1; i < list.size(); ++i){
			int min = i;
			for(int j = i+1; j < list.size(); ++j){
				if(list.get(min) > list.get(j)){
					min = j;
				}
			}
			int a = list.get(i);
			int b = list.get(min);
			list.remove(min);
			list.remove(i);
			list.add(i, b);
			list.add(min, a);
		}
		return list;
	}
	
	/**
	 * 归并排序
	 * 特点：stable sort、Out-place sort
		思想：运用分治法思想解决排序问题。
		最坏情况运行时间：O(nlgn)
		最佳运行时间：O(nlgn)
		分治法介绍：分治法就是将原问题分解为多个独立的子问题，且这些子问题的形式和原问题相似，只是规模上减少了，求解完子问题后合并结果构成原问题的解。
		分治法通常有3步：Divide（分解子问题的步骤） 、 Conquer（递归解决子问题的步骤）、 Combine（子问题解求出来后合并成原问题解的步骤）。
		假设Divide需要f(n)时间，Conquer分解为b个子问题，且子问题大小为a，Combine需要g(n)时间，则递归式为：
		T(n)=bT(n/a)f(n)g(n)
	 * @param list
	 * @return
	 */
	public static List<Integer> mergeSort(List<Integer> list){
		return list;
	}
	
	public static List<Integer> quickSort(List<Integer> list){
		return list;
	}
	public static void main(String[] args){
		Gson gson = new Gson();
		List<Integer> list = Lists.newArrayList(1,3,4,6,2,-1,5, 5);
		System.out.println(gson.toJson(bubbleSort(list)));
	}
}
