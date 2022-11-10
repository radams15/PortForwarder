package uk.co.therhys;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainFrame extends JFrame {
    private List<ThreadCheckbox> forwarderThreads = new ArrayList<>();

    public MainFrame(Map<String, Host> hosts){
        super();

        setContentPane(new MainPanel(hosts, forwarderThreads));

        setSize(640, 480);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void dispose(){
        for(ThreadCheckbox checkbox : forwarderThreads){
            checkbox.endThread();
        }

        System.out.println("End");

        super.dispose();
    }
}
