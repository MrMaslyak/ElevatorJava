import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ElevatorUI extends JFrame implements ActionListener {
    private int positionX = 0, positionY, floorY5 = 400, floorY4 = 475, floorY3 = 550, floorY2 = 625, floorY1 = 700;
    private JLabel liveFloor;
    private int liveFloorInt = 1, speedForElevator;
    private JPanel panelLiveFloor;
    private Map<Integer, JButton> floorButtons;
    private Map<Integer, JButton> floorOnButtons;
    private Map<Integer, JButton> buttonForSpeedElevator;
    private int[] floorPositions;
    private ToggleSwitch isServiceToggle;
    private boolean isService = false;


    public ElevatorUI() {
        setTitle("Elevator Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        positionY = floorY1;
        ui();
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        floorPositions = new int[]{floorY5, floorY4, floorY3, floorY2, floorY1};
    }

    private void ui() {
        JLabel label = new JLabel("Elevator Simulator");
        label.setBounds(185, 25, 300, 25);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        add(label);

        floorButtons = new HashMap<>();
        floorButtons.put(1, makeFloor(450, 100, 85, 30, "Floor 1"));
        floorButtons.put(2, makeFloor(450, 140, 85, 30, "Floor 2"));
        floorButtons.put(3, makeFloor(450, 180, 85, 30, "Floor 3"));
        floorButtons.put(4, makeFloor(450, 220, 85, 30, "Floor 4"));
        floorButtons.put(5, makeFloor(450, 260, 85, 30, "Floor 5"));


        panelLiveFloor = new JPanel();
        panelLiveFloor.setBounds(100, 118, 250, 150);
        panelLiveFloor.setBackground(new Color(193, 192, 192));
        add(panelLiveFloor);

        liveFloor = new JLabel();
        liveFloor.setText("Live Floor: *");
        liveFloor.setFont(new Font("Arial", Font.BOLD, 30));
        liveFloor.setBounds(23, 100, 25, 25);
        panelLiveFloor.add(liveFloor);


        floorOnButtons = new HashMap<>();
        floorOnButtons.put(1, makeButtonOnFloor(500, 685, 50, 50, "<1>"));
        floorOnButtons.put(2, makeButtonOnFloor(500, 610, 50, 50, "<2>"));
        floorOnButtons.put(3, makeButtonOnFloor(500, 535, 50, 50, "<3>"));
        floorOnButtons.put(4, makeButtonOnFloor(500, 460, 50, 50, "<4>"));
        floorOnButtons.put(5, makeButtonOnFloor(500, 385, 50, 50, "<5>"));


        isServiceToggle = new ToggleSwitch();
        isServiceToggle.setBounds(480, 300, 25, 15);
        isServiceToggle.addActionListener(this);
        add(isServiceToggle);

        JLabel labelService = new JLabel("Service");
        labelService.setBounds(468, 323, 60, 15);
        labelService.setFont(new Font("Arial", Font.ITALIC, 15));
        add(labelService);

        buttonForSpeedElevator = new HashMap<>();
        buttonForSpeedElevator.put(1, buttonForSpeed(355, 140, 85, 30, "Speed lowly"));
        buttonForSpeedElevator.put(2, buttonForSpeed(355, 180, 85, 30, "Speed Middle"));
        buttonForSpeedElevator.put(3, buttonForSpeed(355, 220, 85, 30, "Speed Fast"));


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

    public JButton buttonForSpeed(int x, int y, int width, int height, String text) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.ITALIC, 8));
        button.setBackground(new Color(193, 192, 192));
        button.setForeground(new Color(12, 155, 246));
        button.setFocusable(false);
        button.setVisible(true);
        button.addActionListener(this);
        add(button);
        return button;
    }

    public JButton makeButtonOnFloor(int x, int y, int width, int height, String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(getBackground());
                }
                g.fillOval(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(getForeground());
                g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
            }

            @Override
            public boolean contains(int x, int y) {
                int radius = getWidth() / 2;
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                return (Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2)) <= Math.pow(radius, 2);
            }
        };
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.BOLD, 8));
        button.setBackground(new Color(193, 192, 192));
        button.setForeground(new Color(36, 64, 244));
        button.setFocusable(false);
        button.setVisible(true);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
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
        int targetFloor = 1;
        switch (e.getActionCommand()) {

            case "Floor 1":
                if (isService == false) {
                    targetFloor = 1;
                    floorButtons.get(1).setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    break;
                }
            case "<1>":
                if (isService == false) {
                    targetFloor = 1;
                    floorOnButtons.get(1).setForeground(Color.GREEN);
                    break;
                }
            case "Floor 2":
                if (isService == false) {
                    targetFloor = 2;
                    floorButtons.get(2).setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    break;
                }
            case "<2>":
                if (isService == false) {
                    targetFloor = 2;
                    floorOnButtons.get(2).setForeground(Color.GREEN);
                    break;
                }
            case "Floor 3":
                if (isService == false) {
                    targetFloor = 3;
                    floorButtons.get(3).setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    break;
                }
            case "<3>":
                if (isService == false) {
                    targetFloor = 3;
                    floorOnButtons.get(3).setForeground(Color.GREEN);
                    break;
                }
            case "Floor 4":
                if (isService == false) {
                    targetFloor = 4;
                    floorButtons.get(4).setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    break;
                }
            case "<4>":
                if (isService == false) {
                    targetFloor = 4;
                    floorOnButtons.get(4).setForeground(Color.GREEN);
                    break;
                }
            case "Floor 5":
                if (isService == false) {
                    targetFloor = 5;
                    floorButtons.get(5).setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                    break;
                }
            case "<5>":
                if (isService == false) {
                    targetFloor = 5;
                    floorOnButtons.get(5).setForeground(Color.GREEN);
                    break;
                }
            case "Speed lowly":
                if (isService == false) {
                    speedForElevator = 3000;
                    break;
                }
            case "Speed Middle":
                if (isService == false) {
                    speedForElevator = 2000;
                    break;
                }
            case "Speed Fast":
                if (isService == false) {
                    speedForElevator = 1000;
                    break;
                }
        }

        if (e.getSource() == isServiceToggle) {
            if (isServiceToggle.isSelected()) {
                isService = true;
            } else {
                isService = false;
            }
        }

        ElevatorMoverThread moverThread = new ElevatorMoverThread(this, targetFloor, speedForElevator, floorPositions, floorButtons, floorOnButtons, isServiceToggle, isService, buttonForSpeedElevator);
        moverThread.start();
    }

    public void updateLiveFloorLabel() {
        liveFloor.setText("Live Floor: " + liveFloorInt);
        liveFloor.setLocation(10, 100);
    }

    public int getLiveFloorInt() {
        return liveFloorInt;
    }

    public void setLiveFloorInt(int liveFloorInt) {
        this.liveFloorInt = liveFloorInt;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }


}
