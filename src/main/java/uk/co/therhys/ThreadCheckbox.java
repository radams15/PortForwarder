package uk.co.therhys;

import javax.swing.*;
import java.awt.*;

public class ThreadCheckbox extends JPanel {
    private String name;
    private ForwarderThread thread;
    private Host host;

    private JCheckBox checkBox;

    public ThreadCheckbox(String name, Host host) {
        super();

        this.name = name;
        this.host = host;

        setLayout(new FlowLayout());

        checkBox = new JCheckBox();

        checkBox.addActionListener(a -> {
            if(checkBox.isSelected()){
                beginThread();
            }else{
                endThread();
            }
        });

        add(new JLabel(name));
        add(checkBox);
    }

    public void beginThread(){
        thread = ForwarderThread.fromHost(host);

        thread.start();
    }

    public void endThread(){
        if(thread != null) {
            thread.kill();
            thread = null;
        }
    }
}
