package com.demo.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@RequestMapping
@RestController
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }

    private final String file = "/date/date.txt";

    @GetMapping("demo")
    public void test(){
        System.out.println("Hello world from Docker");
    }

    private void writeToFile(){
        try(FileWriter file = new FileWriter(this.file,true);PrintWriter printWriter = new PrintWriter(file)){
            printWriter.println(new Date().toString());
            printWriter.flush();
        }catch (IOException e){
            System.out.println(e);
        }
    }

    @PostMapping("writ-date")
    public void writeDate(){
        writeToFile();
    }

    @GetMapping("read-date")
    public List<String> readDate(){
        return readToFile();
    }

    private List<String> readToFile(){
        try(BufferedReader fileReader = new BufferedReader(new FileReader(file))) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return bufferedReader.lines().collect(Collectors.toList());
        }catch (IOException e){
            System.out.println(e);
        }
        return Collections.EMPTY_LIST;
    }

}
