package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimeLightSubsystem extends SubsystemBase{
    private NetworkTable table=NetworkTableInstance.getDefault().getTable("limelight");;
    private NetworkTableEntry ty = table.getEntry("ty");
    private NetworkTableEntry tx = table.getEntry("tx");
    private NetworkTableEntry ta = table.getEntry("ta");
    private double d;

    public LimeLightSubsystem(){



    }
    public void periodic(){
        dashBoard();
    }

    public void dashBoard(){
        
        //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        double tan = Math.tan((y+Constants.ANGLE)*Math.PI/180);
        try {
            d=(Constants.PORT_HEIGHT-Constants.CAM_HEIGHT)/tan;
        } catch (Exception divideByZeroException) {
            System.out.println("Exception");
        }
        

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        SmartDashboard.putNumber("Distance to target", d);
    }
    public double returnD(){
        System.out.println(d);
        return d;
    }
	public double returnTY() {
		return ty.getDouble(0.0);
    }
    public void lightOn(){
    }
}
