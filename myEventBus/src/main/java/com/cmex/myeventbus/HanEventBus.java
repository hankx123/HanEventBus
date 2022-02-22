package com.cmex.myeventbus;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HanEventBus {

    private static HanEventBus instance;
    private HanEventBus(){
        clsList = new ArrayList();
    }
    public synchronized static HanEventBus getDefault(){
        if(instance == null){
            instance = new HanEventBus();
        }
        return instance;
    }

    private List<Object> clsList;

    public void register(Object cls){
        clsList.add(cls);
    }

    public void unRegister(Object cls){
        clsList.remove(cls);
    }

    public void post(Object data){
        for(Object cls : clsList){
            try {
                Class<?> clsClass = cls.getClass();
                Constructor<?> constructor = clsClass.getConstructor();
                Object instance = constructor.newInstance();

                Method method = clsClass.getMethod("OnBusEvent", Object.class);
                method.invoke(instance, data);
                Method method2 = clsClass.getMethod("OnBusEvent2", Object.class);
                method2.invoke(instance, data);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}