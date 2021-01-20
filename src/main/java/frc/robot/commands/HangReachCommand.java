package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.HangSubsystem;
import java.util.Timer;
import java.util.TimerTask;

public class HangReachCommand extends CommandBase{
    private HangSubsystem hang;
    private Timer time=new Timer();
    private boolean fin;
    public HangReachCommand(HangSubsystem hang){
        this.hang=hang;
        fin=false;
        addRequirements(hang);
    }
    public void execute(){
        hang.reach();
        time.schedule(new TimerTask(){

            @Override
            public void run() {
                fin=true;

            }
            
        }, 10000);
        
    }
    @Override
    public boolean isFinished() {
        return fin;
    }
    public void end(boolean interrupted){
        hang.stop();
        fin=false;
    }
}