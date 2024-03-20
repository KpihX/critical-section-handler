package com.kpihx_lab;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Semaphore;

public class Process implements Runnable {
    private final int numberActions = 2;
    private final int waitTime = 100; // ms
    private Semaphore semaphore;
    private int id;
    private String fileName;

    public Process(int id, String fileName, Semaphore semaphore) {
        this.semaphore = semaphore;
        this.id = id;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            writeToFile("Process " + id + ": I am starting!");
            for (int i = 0; i < numberActions; i++) {
                Thread.sleep(waitTime);
                semaphore.release();
                writeToFile("Process " + id + ": This is my line N°" + i + "."); ;
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            semaphore.release();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void writeToFile(String message) throws IOException{
        FileWriter writer = new FileWriter(Main.resourcesPath + fileName, true);
        writer.write(message + "\n");
        writer.close();
    }
}
