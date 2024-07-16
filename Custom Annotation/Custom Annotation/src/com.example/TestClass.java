// src/com/example/TestClass.java
package com.example;

import com.example.annotations.LogExecutionTime;
import com.example.processors.LogExecutionTimeProcessor;

public class TestClass {

    @LogExecutionTime
    public void testMethod() {
        try {
            Thread.sleep(2000); // Simulate some work with a 2-second sleep
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestClass test = new TestClass();
        LogExecutionTimeProcessor.process(test);
    }
}
