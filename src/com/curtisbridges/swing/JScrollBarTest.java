package com.curtisbridges.swing;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class JScrollBarTest extends JFrame {
    Timer       timer;
    TimerTask   task;

    JTabbedPane tabs;
    JTextArea   textArea;
    JScrollPane scrollPane;

    public JScrollBarTest() {
        super("Scroller Test");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createComponents();
        createTimerAndTask();
    }

    protected void createComponents() {
        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        tabs = new JTabbedPane();

        textArea = new JTextArea();
        textArea.setEditable(false);

        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        caret.setVisible(false);

        scrollPane = new JScrollPane(textArea);

        tabs.add("Status", scrollPane);

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 50));

        container.add(panel, BorderLayout.NORTH);
        container.add(tabs, BorderLayout.CENTER);

        JLabel label = new JLabel("Theoretical status bar.");
        container.add(label, BorderLayout.SOUTH);
    }

    protected void createTimerAndTask() {
        timer = new Timer("ScrollerThread");
        task = new TimerTask() {
            public void run() {
                JScrollBarTest.this.doText();
            }
        };

        timer.schedule(task, 250, 250);
    }

    public void doText() {
        textArea.append("Hello, World!\n");
    }

    public static void main(String[] args) {
        JScrollBarTest scroller = new JScrollBarTest();

        scroller.setSize(640, 480);
        scroller.setVisible(true);
    }
}
