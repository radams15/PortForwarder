package uk.co.therhys;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        ConfigParser configParser = new ConfigParser("conf.ini");

        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame(configParser.getHosts());

            frame.setVisible(true);
        });
    }
}