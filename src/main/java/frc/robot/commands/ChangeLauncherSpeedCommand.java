
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LauncherSubsystem;
/**
 * changes the setpoint of the launcher PID controller
 */
public class ChangeLauncherSpeedCommand extends CommandBase{
    private boolean fin=false;
    private double setspeed;
    private LauncherSubsystem mLauncher;
    /**
     * the command to change launcher speed
     * @param speed the speed to adjust to
     * @param launcher the launcher subsystem
     */
    public ChangeLauncherSpeedCommand(double speed, LauncherSubsystem launcher) {
        setspeed=speed;
        mLauncher=launcher;
        addRequirements(mLauncher);
    }
    public void initialize(){

    }
    /**
     * called when the command is scheduled
     */
    public void execute(){
        mLauncher.setSetpoint(setspeed);
        if(!RobotContainer.coDriverOI.getRawButton(Constants.LEFT_BUMPER)){
            fin=true;
        }
    }
    @Override
    public boolean isFinished() {
        return fin;
    }
    @Override
    public void end(boolean interrupted) {
        if(RobotContainer.coDriverOI.getRawButtonReleased(Constants.LEFT_BUMPER)){
            mLauncher.setSetpoint(0);
        }
    }
}