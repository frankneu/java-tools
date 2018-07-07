package com.frank.neu.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;


//评测题目: 统计订阅人数：
//同时有N个商品（用long类型的商品id表示），
//每个商品都可以被任何一个用户（long类型的用户id）订阅，
//每被订阅一次，该商品的订阅数加1，同一个用户对同一个商品只能订阅一次+编辑写一个类，
//用3个方法提供以下功能（这3个方法都是在单机多线程环境下调用）：
//+1）为指定的用户id订阅指定的商品id
//+2)+返回所有商品的订阅总数
//+3）根据商品ID返回这个商品的订阅数
public class Subscribe
{

	private AtomicInteger totalCounts;
	//
	private List<Long> merchants;
	
	private Map<Long, Set<Long>> map; 
	
	private static Subscribe instance;
	
	private Subscribe(){
		totalCounts = new AtomicInteger(0);
		merchants = Lists.newArrayList();
		map = Maps.newConcurrentMap();
	}
	
	public synchronized static Subscribe getInstance(){
		if(null == instance){
			instance = new Subscribe();
		}
		return instance;
	}
	
	//商品用户订阅关系
  	/**
    *  商品订阅
    *  1,成功
    *  2,已经订阅
    */
  	public int subscriberMerchant(long userId, long merchantId){
    	if(map.containsKey(merchantId)){
    		Set<Long> set = map.get(merchantId);
    		synchronized (set) {
				if(set.contains(userId)){
					return 2;
				}else{
					totalCounts.incrementAndGet();
					set.add(userId);
					return 1;
				}
			}
    	}else{
    		Set<Long> set = Sets.newConcurrentHashSet();
    		map.put(merchantId, set);
    		set.add(userId);
    		totalCounts.incrementAndGet();
    		return 1;
    	}
    	
    }
  	
  	/**
	获取订阅总数
  	 */
	public int getTotalSubscribeCount(){
		return totalCounts.get();
	}	
	
	/**
	根据商品ID返回这个商品的订阅数
	 */
	public int getMerchantSubscribeCount(Long merchantId){
		if(map.containsKey(merchantId)){
			return map.get(merchantId).size();
		}else{
			return 0;
		}
	}	
}
