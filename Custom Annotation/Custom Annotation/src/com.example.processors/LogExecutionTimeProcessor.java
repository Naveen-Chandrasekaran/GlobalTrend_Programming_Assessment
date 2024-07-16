// src/com/example/processors/LogExecutionTimeProcessor.java
package com.example.processors;

import com.example.annotations.LogExecutionTime;
import java.lang.reflect.Method;

public class LogExecutionTimeProcessor {
    
    public static void process(Object obj) {
        Method[] methods = obj.getClass().getDeclaredMethods();
        
        for (Method method : methods) {
            if (method.isAnnotationPresent(LogExecutionTime.class)) {
                try {
                    long start = System.currentTimeMillis();
                    method.setAccessible(true);
                    method.invoke(obj);
                    long end = System.currentTimeMillis();
                    System.out.println("Execution time of " + method.getName() + ": " + (end - start) + "ms");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
