package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeHomeCommand extends CommandBase {
    private IntakeSubsystem intake;
    private int speed=1;
    private boolean fin;

    public IntakeHomeCommand(IntakeSubsystem subsystem) {
        intake=subsystem;
        addRequirements(intake);
    }
    public void execute(){
        intake.deploy.set(speed*.25);
        if(!intake.getSwitch()){
            intake.deploy.set(0);
            fin=true;
        }
        
    }
    @Override
    public boolean isFinished() {
        return fin;
    }
    @Override
    public void end(boolean interupted){
        intake.resetEncoder();
        fin=false;
    }
}