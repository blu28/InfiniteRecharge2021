package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDrive extends CommandBase{
    private DriveSubsystem drive;
    private double speed;
    public DefaultDrive(DriveSubsystem drive, double speed){
        this.drive=drive;
        this.speed=speed;
        addRequirements(drive);
    }
    @Override
    public void execute() {
        
        drive.drive(speed, speed);
    }
    @Override
    public boolean isFinished() {
        return true;
    }
    public void execute(boolean interrupted){

    }
}