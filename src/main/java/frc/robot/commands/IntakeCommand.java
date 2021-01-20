package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class IntakeCommand extends CommandBase{
    private IntakeSubsystem intake;
    private double speed;
    private boolean fin;
    public  IntakeCommand(IntakeSubsystem subsystem, double speed) {
        intake=subsystem;
        this.speed=speed;
        addRequirements(intake);
    }
    public void execute(){
        intake.runIntake(speed);
        fin=true;
    }
    @Override
    public boolean isFinished() {
        return fin;
    }
    @Override
    public void end(boolean interupted){
        
    }
}