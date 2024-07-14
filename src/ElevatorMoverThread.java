import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.util.Map;

class ElevatorMoverThread extends Thread {
    private ElevatorUI elevatorUI;
    private int targetFloor, speedForElevator;
    private int[] floorPositions;
    private Map<Integer, JButton> floorButtons;
    private Map<Integer, JButton> floorOnButtons;
    private Map<Integer, JButton> buttonForSpeedElevator;
    private ToggleSwitch isServiceToggle;
    private boolean isService = false;

    public ElevatorMoverThread(ElevatorUI elevatorUI, int targetFloor, int speedForElevator, int[] floorPositions, Map floorButtons, Map floorOnButtons, ToggleSwitch isServiceToggle, boolean  isService, Map  buttonForSpeedElevator) {
        this.floorButtons = floorButtons;
        this.elevatorUI = elevatorUI;
        this.targetFloor = targetFloor;
        this.floorPositions = floorPositions;
        this.floorOnButtons = floorOnButtons;
        this.isServiceToggle = isServiceToggle;
        this.isService = isService;
        this.speedForElevator = speedForElevator;
        this.buttonForSpeedElevator = buttonForSpeedElevator;
    }

    @Override
    public void run() {
        while(elevatorUI.getLiveFloorInt() != targetFloor && isService == false && isServiceToggle.isSelected() == false){
            if(elevatorUI.getLiveFloorInt() < targetFloor){
                elevatorUI.setLiveFloorInt(elevatorUI.getLiveFloorInt() + 1);
            }else if(elevatorUI.getLiveFloorInt() > targetFloor){
                elevatorUI.setLiveFloorInt(elevatorUI.getLiveFloorInt() - 1);
            }
            if (elevatorUI.getLiveFloorInt() == 1){
                floorButtons.get(1).setBorder(null);
                floorOnButtons.get(1).setForeground(Color.BLUE);
            }else if (elevatorUI.getLiveFloorInt() == 2){
                floorButtons.get(2).setBorder(null);
                floorOnButtons.get(2).setForeground(Color.BLUE);
            }else if (elevatorUI.getLiveFloorInt() == 3){
                floorButtons.get(3).setBorder(null);
                floorOnButtons.get(3).setForeground(Color.BLUE);
            } else if (elevatorUI.getLiveFloorInt() == 4) {
                floorButtons.get(4).setBorder(null);
                floorOnButtons.get(4).setForeground(Color.BLUE);
            } else if (elevatorUI.getLiveFloorInt() == 5){
                floorButtons.get(5).setBorder(null);
                floorOnButtons.get(5).setForeground(Color.BLUE);
            }
            elevatorUI.setPositionY(floorPositions[5 - elevatorUI.getLiveFloorInt()]);
            elevatorUI.updateLiveFloorLabel();
            elevatorUI.repaint();
            try {
                Thread.sleep(speedForElevator);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        }
    }
