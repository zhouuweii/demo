package com.zw.admin.framework.common.utils;

import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 数据集合相关操作工具类
 * @author: ZhouWei
 * @create: 2021-01
 **/
@Component
public class MyCollectionUtil {
    /**
     * 随机抽取List集合中特定个数的元素
     * @param list 数据列表
     * @param count 返回个数
     * @return java.util.List<java.lang.String>
     **/
    public static List<String> listGetSubStringByRadom(List<String> list, int count) {
        List backList = null;
        backList = new ArrayList<String>();
        Random random = new Random();
        int backSum = 0;
        if (list.size() >= count) {
            backSum = count;
        } else {
            backSum = list.size();
        }
        for (int i = 0; i < backSum; i++) {
//			随机数的范围为0-list.size()-1
            int target = random.nextInt(list.size());
            backList.add(list.get(target));
            list.remove(target);
        }
        return backList;
    }

    /**
     * Map按照Value降序排序
     * @param map Map集合
     * @return java.util.Map<K, V>
     **/
    public static <K, V extends Comparable<? super V>> Map<K, V> mapSortByValueDescending(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return -compare;
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     *  Map按照Value升序排序
     * @param map Map集合
     * @return java.util.Map<K, V>
     **/
    public static <K, V extends Comparable<? super V>> Map<K, V> mapSortByValueAscending(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int compare = (o1.getValue()).compareTo(o2.getValue());
                return compare;
            }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    /**
     *  Map按照key降序排序
     * @param map Map集合
     * @return java.util.Map<java.lang.String, java.lang.String>
     **/
    public static <K, V extends Comparable<? super V>> Map<K, V> mapSortByKeyDescending(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                // 降序排序
                return -(Integer.parseInt(String.valueOf(o1.getKey().toString().replaceAll("[^0-9]", ""))) - Integer.parseInt(String.valueOf(o2.getKey().toString().replaceAll("[^0-9]", ""))));
            }
        });
        Map result = new LinkedHashMap();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());

        }
        return result;
    }

    /**
     *  Map按照key升序排序
     * @param map Map集合
     * @return java.util.Map<java.lang.String, java.lang.String>
     **/
    public static <K, V extends Comparable<? super V>> Map<K, V> mapSortByKeyAscending(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                // 升序排序
                return Integer.parseInt(String.valueOf(o1.getKey().toString().replaceAll("[^0-9]", ""))) - Integer.parseInt(String.valueOf(o2.getKey().toString().replaceAll("[^0-9]", "")));

            }
        });
        Map result = new LinkedHashMap();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());

        }
        return result;
    }

    /**
     *  将Map转换为EChart图表所需要的格式： name=MapKey   value=MapValue
     * @param map Map集合
     * @return java.util.List<java.util.Map < java.lang.String, V>>
     **/
    public static List<Map<String, Object>> mapToNameValue(Map<String, Long> map) {
        Map<String, Object> seriesItemMap = null;
        List<Map<String, Object>> seriesList = new ArrayList<>();
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            seriesItemMap = new HashMap<String, Object>();
            ;
            seriesItemMap.put("name", entry.getKey());
            seriesItemMap.put("value", entry.getValue());
            seriesList.add(seriesItemMap);
        }
        return seriesList;
    }

    /**
     *  多Map融合，相同的key，value相加，不同的key追加
     * @param target 总map
     * @param plus 需要合并的map
     * @return java.util.Map<java.lang.String, java.lang.Long>
     **/
    public static Map<String, Long> mapMerge(Map<String, Long> target, Map<String, Long> plus) {
        Object[] os = plus.keySet().toArray();
        String key;
        for (int i = 0; i < os.length; i++) {
            key = (String) os[i];
            if (target.containsKey(key)) {
                target.put(key, target.get(key) + plus.get(key));
            } else {
                target.put(key, plus.get(key));
            }
        }
        return target;
    }

}
