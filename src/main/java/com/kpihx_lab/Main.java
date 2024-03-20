package com.kpihx_lab;

import java.util.concurrent.Semaphore;
import java.io.IOException;
import java.io.FileWriter;

public class Main {
    public static final String resourcesPath = "src/main/resources/"; 

    public static void main(String[] args) {
        String fileName = "criticalSection.txt";
        try {
            createFile(fileName);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int numberProcesses = 5;
        Semaphore semaphore = new Semaphore(1);
        Thread[] processes = new Thread[numberProcesses];

        for (int i = 0; i < numberProcesses; i++) {
            processes[i] = new Thread(new Process(i, fileName, semaphore));
        }

        for (Thread process : processes) {
            process.setUncaughtExceptionHandler(new ProcessUncaughtExceptionHandler());
            process.start();
        }
    }

    public static void createFile(String fileName) throws IOException {
        FileWriter writer = new FileWriter(resourcesPath + fileName);
        writer.close();
    }
}