# Java Thread-Pool Socket Server

A Java TCP server that uses a fixed-size `ExecutorService` thread pool to handle multiple client connections concurrently on port 8010.

## Contents

- `Server.java` — the main server class  
- `Client.java` — simple client to test connections (optional)

## Prerequisites

- Java 8+ (JDK 11 or higher recommended)  
- Git (if you plan to version-control)  

## How it works

1. The server listens on port **8010**.  
2. When a client connects, the server submits a task to the thread pool instead of spinning up a new thread each time.  
3. Each worker thread writes a greeting back to the client and then closes the socket.  

## Configuration

- **Port**: change the `port` variable in `main()` if you need a different listening port.  
- **Pool size**: adjust `poolSize` (currently set to `10`) in the constructor call  
  ```java
  Server server = new Server(/* poolSize = */ 10);
