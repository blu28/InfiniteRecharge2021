package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DeployIntakeCommand extends CommandBase{
    private IntakeSubsystem intake;
    private int speed;
    private boolean fin;
    public  DeployIntakeCommand(IntakeSubsystem subsystem, int speed) {
        intake=subsystem;
        this.speed=speed;
        addRequirements(intake);
    }
    public void execute(){
        fin=intake.deployIntake(speed);
    }
    @Override
    public boolean isFinished() {
        return fin;
    }
    @Override
    public void end(boolean interupted){
        //intake.resetEncoder();
        
    }
}