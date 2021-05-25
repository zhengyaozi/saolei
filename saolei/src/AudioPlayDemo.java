import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;

import javax.swing.*;
import java.io.File;

public class AudioPlayDemo extends JFrame implements ActionListener{
    boolean looping = false;
    File file1 = new File("memories.wav");
    AudioClip sound1;
    AudioClip chosenClip;

    JButton playButton = new JButton("播放");
    JButton loopButton = new JButton("循环播放");
    JButton stopButton = new JButton("停止");
    JLabel status = new JLabel("");
    JPanel controlPanel = new JPanel();
    Container container = getContentPane();

    public AudioPlayDemo() {
        try {
            sound1 = Applet.newAudioClip(file1.toURL());
            chosenClip = sound1;
        } catch(OutOfMemoryError e){
            System.out.println("内存溢出");
            e.printStackTrace();
        } catch(Exception e){
            e.printStackTrace();
        }

        playButton.addActionListener(this);
        loopButton.addActionListener(this);
        stopButton.addActionListener(this);
        stopButton.setEnabled(false);

        controlPanel.add(playButton);
        controlPanel.add(loopButton);
        controlPanel.add(stopButton);

        container.add(controlPanel, BorderLayout.CENTER);
        container.add(status, BorderLayout.SOUTH);

        setSize(300, 130);
        this.setLocation(100,100);//设置窗口在屏幕正中间显示

        setTitle("音乐播放");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口时退出程序
    }

    public void actionPerformed(ActionEvent event) {
        if (chosenClip == null) {
            status.setText("声音未载入");
            return;
        }
        Object source = event.getSource(); //获取用户洗涤激活的按钮

        if (source == playButton ) {
            stopButton.setEnabled(true);
            loopButton.setEnabled(true);
            chosenClip.play();
            status.setText("正在播放");
        }

        if (source == loopButton) {
            looping = true;
            chosenClip.loop();
            loopButton.setEnabled(false);
            stopButton.setEnabled(true);
            status.setText("正在循环播放");
        }
        if (source == stopButton) {
            if (looping) {
                looping = false;
                chosenClip.stop();
                loopButton.setEnabled(true);
            } else {
                chosenClip.stop();
            }
            stopButton.setEnabled(false);
            status.setText("停止播放");
        }
    }
    public static void main(String s[]) {
        new AudioPlayDemo();
    }
}