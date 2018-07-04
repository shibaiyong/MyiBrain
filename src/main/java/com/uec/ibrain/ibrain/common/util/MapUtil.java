package com.uec.ibrain.ibrain.common.util;

import java.util.*;
import java.util.Map.Entry;

/**
 * <p>Copyright: All Rights Reserved</p>
 * <p>Company: http://www.ronglian.com</p>
 * <p>Description: Map操作工具类 </p>
 * <p>Author:ftliu/刘芳彤</p>
 * <p>Date: 2018/2/2</p>
 */
public class MapUtil {
    public static List<Entry<String,Integer>> sortByValueDesc(Map<String, Integer> tm){
//        Map<String, String> tm=new TreeMap<String, String>();
//        tm.put("a", "ddd");   tm.put("b", "ccc");
//        tm.put("c", "bbb");   tm.put("d", "aaa");
        //这里将map.entrySet()转换成list
        List<Entry<String,Integer>> list = new ArrayList<>(tm.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Entry<String,Integer>>() {
            //降序排序
            @Override
            public int compare(Entry<String, Integer> o1,
                               Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        for(Entry<String,Integer> mapping:list){
            System.out.println(mapping.getKey()+":"+mapping.getValue());
        }
        return  list;
    }

    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Object> sortMap = new TreeMap<>(
                new MapKeyComparator());

        sortMap.putAll(map);

        return sortMap;
    }
    static class MapKeyComparator implements Comparator<String>{
        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }
}
