package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveSoloCommand extends CommandBase{
    private DriveSubsystem drive;
    private LimeLightSubsystem lime;
    private double left,right,z,startZ;
    private boolean fin=false,first=true;
    private double leftAdjust,rightAdjust;
    private double lastTime = 0;
    private double firstTime = 0;
    private Timer timer = new Timer();
    private NetworkTable network = NetworkTableInstance.getDefault().getTable("Timer between execute Drive autonomous");
    private NetworkTableEntry networkEntry = network.getEntry("TIME");
    
    public DriveSoloCommand(DriveSubsystem drive,LimeLightSubsystem lime,double x,double y,double z){
        this.drive=drive;
        
        this.lime=lime;
        left=x;
        right=y;
        startZ=lime.returnD();
        this.z=startZ+z;
        addRequirements(drive);
        
    }
    public void execute(){
        
        firstTime = Timer.getFPGATimestamp();
        SmartDashboard.putNumber("Timing outside of execute", firstTime - lastTime);
        
        float Kp=0.1f;
        leftAdjust=left;
        rightAdjust=right;
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        //checks for input from drive to allign to target.
        //if (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0)==1){
            double steering_adjust = Kp * tx;
            steering_adjust= DriveSubsystem.deadzone(steering_adjust) > 0.3 ? 0.3 : DriveSubsystem.deadzone(steering_adjust);
            steering_adjust= DriveSubsystem.deadzone(steering_adjust) > -0.3 ? DriveSubsystem.deadzone(steering_adjust) : -0.3;
            leftAdjust+=steering_adjust;
            rightAdjust-=steering_adjust;
        //}
        
        
        if(lime.returnD()>=z&&left>0){
            fin=true;
            
            drive.drive(0, 0);
        }
        else if(lime.returnD()<=z&&left<0){
            fin=true;
            
            drive.drive(0, 0);
        }
        else{
            drive.drive(leftAdjust,rightAdjust);
        }
        lastTime = timer.getFPGATimestamp();
        SmartDashboard.putNumber("Timing within execute", lastTime - firstTime);
    }
    public boolean isFinished(){
        return fin;
    }
    public void end(boolean interrupted){
        drive.drive(0.0, 0.0);

    }


}