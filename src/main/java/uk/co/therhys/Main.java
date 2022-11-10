package uk.co.therhys;

public class Main {
    public static void main(String[] args) {
        ForwarderThread forwarderThread = new ForwarderThread("mac", 4000);

        forwarderThread.start();

        try {
            forwarderThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}