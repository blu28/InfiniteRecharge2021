package frc.robot.subsystems;

import java.util.TimerTask;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import java.util.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class PDPSubsystem extends SubsystemBase{
    private PowerDistributionPanel pdp;
    private Timer time;
    private boolean active=true;
    public PDPSubsystem(){
        pdp=new PowerDistributionPanel();
        time=new Timer();
    }
    public void periodic(){
        if(active){
            active=false;
            time.schedule(new TimerTask() {

			    @Override
			    public void run() {
				    for(int c=0;c<16;c++){
                        SmartDashboard.putNumber("Channel "+c, pdp.getCurrent(c));
                    }
                    SmartDashboard.putNumber("PDP amp draw", pdp.getTotalCurrent());
                    active=true;
			    }

            },100);
        }
        
    }
}