package uk.co.therhys;

import java.io.IOException;
import java.io.InputStream;

public class ForwarderThread extends Thread {
    private String host;
    private int externalPort;
    private int internalPort;

    public ForwarderThread(String host, int externalPort){
        this.host = host;
        this.externalPort = externalPort;
        this.internalPort = externalPort;
    }

    public ForwarderThread(String host, int externalPort, int internalPort){
        this.host = host;
        this.externalPort = externalPort;
        this.internalPort = internalPort;
    }

    @Override
    public void run() {
        String command = String.format("ssh -L %d:localhost:%d %s", internalPort, externalPort, host);

        System.out.println(command);

        try {
            Process proc = Runtime.getRuntime().exec(command);
            InputStream is = proc.getInputStream();
            InputStream es = proc.getErrorStream();

            byte[] buf = new byte[1024];
            while(proc.isAlive()){
                while(is.available() > 0){
                    is.read(buf);
                    //System.out.println(new String(buf));
                }

                while(es.available() > 0){
                    es.read(buf);
                    //System.err.println(new String(buf));
                }
            }

        }catch (IOException e){
            e.printStackTrace();

            System.out.printf("Connection to %s:%d ended!%n", host, externalPort);
        }
    }
}
