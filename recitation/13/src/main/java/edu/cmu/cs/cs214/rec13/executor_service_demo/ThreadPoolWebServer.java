package edu.cmu.cs.cs214.rec13.executor_service_demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolWebServer {
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(100);  // 100 threads

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8000); // 8000 is a common development web port
        while (true) {
            Socket connection = socket.accept();
            threadPool.submit(() -> handleRequest(connection));
        }
    }

    private static void handleRequest(Socket connection) {
        // Request-handling logic goes here
        System.out.println("Got a connection!");
    }
}
