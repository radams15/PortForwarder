package uk.co.therhys;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class MainPanel extends JPanel {
    private Map<String, Host> hosts;
    private List<ThreadCheckbox> forwarderThreads;

    public MainPanel(Map<String, Host> hosts, List<ThreadCheckbox> forwarderThreads){
        this.hosts = hosts;
        this.forwarderThreads = forwarderThreads;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        hosts.forEach((k,v) -> {
                    ThreadCheckbox box = new ThreadCheckbox(k, v);
                    forwarderThreads.add(box);
                    add(box);
                }
        );
    }
}
