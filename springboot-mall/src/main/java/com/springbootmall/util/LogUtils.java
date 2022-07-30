package com.springbootmall.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 比较两个对象中所有属性值是否相等
 * @param <T>
 */
public class LogUtils<T> {

    /**
     * 对象比较器
     * 比较结果eg：1、字段名称loginName,旧值:liu,新值:gu;2、字段名称address,旧值:hunan,新值:neimenggu
     *
     * @param oldBean
     * @param newBean
     * @return
     */
    public List<String> compareObject(Object oldBean, Object newBean) {
        String str = "";
        List<String> list = new ArrayList<>();
        if (oldBean.getClass() == newBean.getClass()) {
            T pojo1 = (T) oldBean;
            T pojo2 = (T) newBean;
            try {
                Class clazz = pojo1.getClass();
                Field[] fields = pojo1.getClass().getDeclaredFields();
                int i = 1;
                for (Field field : fields) {
                    //遍历获取属性名 field.getName()
                    if ("serialVersionUID".equals(field.getName())) {
                        continue;
                    }
                    //获取object的全部属性
                    //PropertyDescriptor[] pds = Introspector.getBeanInfo(clazz,Object.class).getPropertyDescriptors();

                    //获取object的属性
                    PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                    //获取属性的get方法
                    Method getMethod = pd.getReadMethod();
                    // 在pojo1上调用get方法等同于得到pojo1的属性值
                    Object o1 = getMethod.invoke(pojo1);
                    Object o2 = getMethod.invoke(pojo2);
                    if (o1 == null || o2 == null) {
                        continue;
                    }
                    if (o1 instanceof List || o2 instanceof List) {
                        continue;
                    }
                    if (!o1.toString().equals(o2.toString())) {
                        list.add(field.getName());
                        if (i != 1) {
                            str += ";";
                        }
                        str += i + "、字段名称" + field.getName() + ",旧值:" + o1 + ",新值:" + o2;
                        i++;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

}