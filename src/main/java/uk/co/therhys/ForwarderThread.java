package uk.co.therhys;

import java.io.IOException;
import java.io.InputStream;

public class ForwarderThread extends Thread {
    private String host;
    private int externalPort;
    private int internalPort;

    private Process proc;

    public static ForwarderThread fromHost(Host host){
        return new ForwarderThread(host.hostname, host.externalPort, host.internalPort);
    }

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

    public void kill(){
        if(proc != null) {
            proc.destroy();

            if (proc.isAlive()) {
                proc.destroyForcibly();
            }

            proc = null;
        }
    }

    @Override
    public void run() {
        String command = String.format("ssh -L %d:localhost:%d %s", externalPort, internalPort, host);

        System.out.println(command);

        try {
            proc = Runtime.getRuntime().exec(command);
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
