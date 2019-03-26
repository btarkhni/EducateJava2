package com.bruceeckel.threading.example01;

import com.bruceeckel.swing.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This is example from the Bruce Eckel "Thinking in Java" second edition (Russian version) book:
 * Chapter.14 "MultiThreading", sub-section "Interactive UI", pp.646-647
 * Here is problematic code, which is stuck (ceased to be interactive)
 *
 */
public class Counter1 extends JApplet {

    private int count = 0;
    private JButton
        start = new JButton("Start"),
        onOff = new JButton("Toggle");
    private JTextField t = new JTextField(10);
    private boolean runFlag = true;

    public void init() {
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(t);
        start.addActionListener(new StartL());
        cp.add(start);
        onOff.addActionListener(new OnOffL());
        cp.add(onOff);
    }


    public void go() {
        while(true) {
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.err.println("Execution has been interrupted");
            }
            if(runFlag) {
                // actually - is not drawn !!
                t.setText(Integer.toString(count++));
                System.out.println("count == " + Integer.toString(count));
            }
//            if(count == 3)
//                break;
        }
    }


    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            go();
        }
    }

    class OnOffL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            runFlag = !runFlag;
        }
    }



    public static void main(String[] args) {
        Console.run(new Counter1(), 300, 100);
    }
}
