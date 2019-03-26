package com.bruceeckel.threading.example02;

import com.bruceeckel.swing.Console;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is example from the Bruce Eckel "Thinking in Java" second edition (Russian version) book:
 * Chapter.14 "MultiThreading", sub-section "Threads using for creation of interactive UI", pp.650-651
 * This is modification of:
 * @see com.bruceeckel.threading.example01.Counter1
 * making UI which was stuck there again interactive one.
 *
 */
public class Counter2 extends JApplet {

    private class SeparateSubTask extends Thread {
        private int count = 0;
        private boolean runFlag = true;

        SeparateSubTask() {
            start();
        }

        void invertFlag() {runFlag = !runFlag;}

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

    }

    private SeparateSubTask sp = null;
    private JButton
        start = new JButton("Start"),
        onOff = new JButton("Toggle");
    private JTextField t = new JTextField(10);

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
            if(sp == null)
                sp = new SeparateSubTask();
        }
    }

    class OnOffL implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(sp != null)
                sp.invertFlag();
        }
    }



    public static void main(String[] args) {
        Console.run(new Counter2(), 300, 100);
    }

}
