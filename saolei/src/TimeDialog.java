import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TimeDialog {

    private JDialog dialog = new JDialog();

    private int seconds;

    public void showDialog(JFrame jFrameOfButton, String message, int closeInSec) {
        dialog.setLayout(null);

        seconds = closeInSec;
        JLabel label = new JLabel(message, JLabel.CENTER);
        label.setBounds(80, 10, 200, 20);

        ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();

        JButton confirm = new JButton("好咯...");
        confirm.setBounds(140, 120, 50, 60);
        confirm.addActionListener(e -> TimeDialog.this.dialog.dispose());

        dialog = new JDialog(jFrameOfButton, true);
        dialog.setTitle(seconds+"秒后自动关闭");
        dialog.setLayout(new GridLayout(2, 1));
        dialog.add(label);
        dialog.add(confirm);

        s.scheduleAtFixedRate(() -> {
            TimeDialog.this.seconds--;
            if (TimeDialog.this.seconds == 0) {
                TimeDialog.this.dialog.dispose();
                System.gc();
            } else {
                dialog.setTitle(seconds+"秒后自动关闭");
            }
        }, 1, 1, TimeUnit.SECONDS);

        dialog.setSize(new Dimension(250, 100));
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
