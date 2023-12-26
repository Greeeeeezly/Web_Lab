package com.example.springdatabasicdemo.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class PageViewService {

    private List<String> searchStrings = new ArrayList<>();

    private long lastPosition = 0;

    public void processLogFile(String filePath) {
        try (RandomAccessFile reader = new RandomAccessFile(filePath, "r");
             FileChannel channel = reader.getChannel()) {

            channel.position(lastPosition);
            String line;
            while ((line = reader.readLine()) != null) {
                processLogLine(line);
            }
            lastPosition = channel.position();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLogLine(String logLine) {
        String startDelimiter = "Controller  : ";
        String endDelimiterAdmin = "admin";
        String endDelimiterUser = "user";
        int end;

        int start = logLine.indexOf(startDelimiter);
        if(logLine.contains(endDelimiterAdmin)){
            end = logLine.indexOf(endDelimiterAdmin);
        }
        else end = logLine.indexOf(endDelimiterUser);


        if (start >= 0 && end >= 0 && end > start) {
            String searchString = logLine.substring(start + startDelimiter.length(), end).trim();
            searchStrings.add(searchString);
        }
    }


    public Map<String, Long> countOccurrences() {
        String filePath="A:\\logs\\spring.log";
        processLogFile(filePath);
        Map<String, Long> counts = new HashMap<>();
        for (String searchString : searchStrings) {
            try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
                long count = lines
                        .filter(line -> line.contains(searchString))
                        .count();
                counts.put(searchString, count);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return counts.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e2,
                                LinkedHashMap::new));
    }
}
