package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;
/*Реализуй методы:*/
public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;// сколько значений хранится по одному ключу
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

/*1) int size() - должен возвращать количество значений в нашей коллекции.*/
    @Override
    public int size() {
        return values().size();
        //напишите тут ваш код
    }

//2) V put(K key, V value) - должен добавить элемент value по ключу key.
    @Override
    public V put(K key, V value) {
        List<V> values= map.get(key);

        // Если по ключу key значений еще нет - верни null.
        V oldvalue= null;
        if(values==null){
            values= new ArrayList<>();
            // Если в мапе такой ключ уже есть, и количество значений по этому ключу меньше,
            // чем repeatCount - то добавь элемент value в конец листа в объекте map.
        }else {
            oldvalue= values.get(values.size()-1);
            // Если по такому ключу количество значений равняется repeatCount
            // - то удали из листа в объекте map элемент с индексом ноль,
            // и добавь в конец листа value.
            if(values.size()==repeatCount)
                values.remove(0);
        }
        values.add(value);
        map.put(key,values);
        // Метод должен возвращать значение последнего добавленного элемента
        // по ключу key
        // (но не значение, которое мы сейчас добавляем).
        return oldvalue;
    }

    //3) V remove(Object key) - должен удалить элемент по ключу key.
    // Метод должен возвращать элемент, который ты удалил.
    @Override
    public V remove(Object key) {
        List<V> values= map.get(key);// получаем лист по ключу
        if(values==null){
            return null;// если в мапе нет ключа
        }
        V storeValue= values.get(0);// получили элемент из листа под индексом 0
        values.remove(0);// удаляем эжлемент из листа с индексом 0
        if(values.size()==0){//// Если по какому-то ключу хранится лист размером ноль элементов
            map.remove(key);
        }
        return storeValue;
    }

    //4) Set<K> keySet() - должен вернуть сет всех ключей, которые есть в мапе map.
    @Override
    public Set<K> keySet() {
        //напишите тут ваш код
        return map.keySet();
    }
//5) Collection<V> values() - должен вернуть ArrayList<V> всех значений.
// Порядок значений в листе не имеет значения.
    @Override
    public Collection<V> values() {
        ArrayList<V> values = new ArrayList<>();
        for(List<V> value :map.values()){
            values.addAll(value);
        }
        return values;
    }
//6) boolean containsKey(Object key) - должен вернуть true,
// если в мапе присутствует ключ key, иначе вернуть false.
    @Override
    public boolean containsKey(Object key) {
        //напишите тут ваш код
        return keySet().contains(key);
    }
//7) boolean containsValue(Object value) - должен вернуть true,
// если в мапе присутствует значение value, иначе вернуть false.*/
    @Override
    public boolean containsValue(Object value) {
       return values().contains(value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}