import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElevatorUI extends JFrame implements ActionListener {
    private int positionX = 0, positionY, floorY5 = 400, floorY4 = 475, floorY3 = 550, floorY2 = 625, floorY1 = 700;
    private JLabel liveFloor;
    private int liveFloorInt;
    private JPanel panelLiveFloor;

    public ElevatorUI() {
        setTitle("Elevator Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        positionY = floorY1;
        ui();
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
    }

    private void ui() {
        JLabel label = new JLabel("Elevator Simulator");
        label.setBounds(185, 25, 300, 25);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        add(label);

        makeFloor(450, 100, 85, 30, "Floor 1");
        makeFloor(450, 140, 85, 30, "Floor 2");
        makeFloor(450, 180, 85, 30, "Floor 3");
        makeFloor(450, 220, 85, 30, "Floor 4");
        makeFloor(450, 260, 85, 30, "Floor 5");

        panelLiveFloor = new JPanel();
        panelLiveFloor.setBounds(100, 118, 250, 150);
        panelLiveFloor.setBackground(new Color(193, 192, 192));
        add(panelLiveFloor);

        liveFloor = new JLabel();
        liveFloor.setText("1");
        liveFloor.setFont(new Font("Arial", Font.BOLD, 30));
        liveFloor.setBounds(23, 100, 25, 25);
        panelLiveFloor.add(liveFloor);








    }

    public JButton makeFloor(int x, int y, int width, int height, String text) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.ITALIC, 15));
        button.setBackground(new Color(193, 192, 192));
        button.setForeground(new Color(244, 60, 36));
        button.setFocusable(false);
        button.setVisible(true);
        button.addActionListener(this);
        add(button);
        return button;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.GRAY);
        g2d.drawLine(100, floorY1, getWidth(), floorY1);
        g2d.drawLine(100, floorY2, getWidth(), floorY2);
        g2d.drawLine(100, floorY3, getWidth(), floorY3);
        g2d.drawLine(100, floorY4, getWidth(), floorY4);
        g2d.drawLine(100, floorY5, getWidth(), floorY5);
        g2d.drawLine(100, 775, getWidth(), 775);
        g2d.setPaint(Color.DARK_GRAY);
        g2d.drawRect(positionX, positionY, 100, 75);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Floor 1":
                positionY = floorY1;
                liveFloor.setText("1");
                break;
            case "Floor 2":
                positionY = floorY2;
                liveFloor.setText("2");
                break;
            case "Floor 3":
                positionY = floorY3;
                liveFloor.setText("3");
                break;
            case "Floor 4":
                positionY = floorY4;
                liveFloor.setText("4");

            case "Floor 5":
                positionY = floorY5;
                liveFloor.setText("5");
                break;
        }
    }
}
