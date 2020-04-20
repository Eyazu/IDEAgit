package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPoolTest {
    public static void main(String[] args) {
        String directory;
        String keyword;
        Scanner in = new Scanner(System.in);

        System.out.println("Enter the directory");
        directory = in.nextLine();
        System.out.println("Enter the keyword");
        keyword = in.nextLine();


        /**
         * cal the time
         */
        long startTime = System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(100);
        MatchCounter counter = new MatchCounter(new File(directory), keyword, pool);
        Future<Integer> result = pool.submit(counter);
        try {
            System.out.println(result.get() + " matches files");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        int largestPoolSize = ((ThreadPoolExecutor) pool).getLargestPoolSize();
        System.out.println("the size : " + largestPoolSize);

        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000F;
        System.out.println("the time costs: " + Float.toString(seconds) + "s");
    }
}

class MatchCounter implements Callable<Integer> {

    private File directory;
    private String keyword;
    private ExecutorService pool;
    private Integer count;

    public MatchCounter(File directory, String keyword, ExecutorService pool) {
        this.directory = directory;
        this.keyword = keyword;
        this.pool = pool;
    }

    @Override
    public Integer call(){
        count = 0;
        try {


            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();
            for (File file : files) {
                if (file.isDirectory()) {
                    MatchCounter counter = new MatchCounter(file, keyword, pool);
                    Future<Integer> result = pool.submit(counter);
                    results.add(result);
                } else {
                    if (search(file)) ++count;
                }
            }
            for (Future<Integer> result : results) {
                try {
                    count += result.get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        return count;
    }
    boolean search(File file) {
        try {
            Scanner in = new Scanner(file, "UTF-8");
            boolean found = false;
            while(in.hasNext() && !found) {
                String line = in.nextLine();
                if(line.contains(keyword)) found = true;
            }
            return found;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
