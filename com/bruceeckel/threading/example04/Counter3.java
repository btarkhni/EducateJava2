package com.bruceeckel.threading.example04;

import com.bruceeckel.swing.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is example from the Bruce Eckel "Thinking in Java" second edition (Russian version) book:
 * Chapter.14 "MultiThreading", sub-section "United thread with main class", pp.652-653
 * This is modification of:
 * @see com.bruceeckel.threading.example02.Counter2, using
 * @see Runnable interface
 *
 */
public class Counter3 extends JApplet implements Runnable {

    private class SeparateSubTask extends Thread {

        SeparateSubTask() {
            start();
        }

        void invertFlag() {runFlag = !runFlag;}


    }

    private int count = 0;
    private boolean runFlag = true;
    private Thread selfThread = null;
    private JButton
        start = new JButton("Start"),
        onOff = new JButton("Toggle");
    private JTextField t = new JTextField(10);


    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
            } catch(InterruptedException e) {
                System.err.println("Execution has been interrupted");
            }
            if(runFlag) {
                // actually - is not drawn !!
                ++count;
                t.setText(Integer.toString(count));
                System.out.println("count == " + Integer.toString(count));
            }
//            if(count == 3)
//                break;
        }
    }



    public void init() {
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        cp.add(t);
        start.addActionListener(new StartL());
        cp.add(start);
        onOff.addActionListener(new OnOffL());
        cp.add(onOff);
    }



    class StartL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(selfThread == null) {
                selfThread = new Thread(Counter3.this);
                selfThread.start();
            }
        }
    }


    class OnOffL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            runFlag = !runFlag;
        }
    }



    public static void main(String[] args) {
        Console.run(new Counter3(), 300, 100);
    }

}
