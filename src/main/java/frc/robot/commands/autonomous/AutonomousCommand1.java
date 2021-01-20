package frc.robot.commands.autonomous;

import java.util.Timer;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.ChangeLauncherSpeedCommand;
import frc.robot.commands.DefaultDrive;
import frc.robot.commands.HopperCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;

public class AutonomousCommand1 extends CommandGroupBase {
    private DriveSubsystem drive;
    private LauncherSubsystem launcherSubsystem;
    private LimeLightSubsystem lime;
    private HopperSubsystem hopperSubsystem;
    private DefaultDrive solo;
    private CommandGroupBase auto;
    private boolean done = false;
    private Timer time = new Timer();

    public AutonomousCommand1(DriveSubsystem drive, LauncherSubsystem launcherSubsystem, LimeLightSubsystem lime,
            HopperSubsystem hopperSubsystem) {
        this.drive = drive;
      this.launcherSubsystem = launcherSubsystem;
      this.lime=lime;
      this.hopperSubsystem=hopperSubsystem;
      addRequirements(drive);
      addRequirements(launcherSubsystem);
      addRequirements(lime);
      addRequirements(hopperSubsystem);
      solo=new DefaultDrive(drive,0.5);
      addCommands(new ChangeLauncherSpeedCommand(2950,launcherSubsystem),new WaitCommand(2),
                new HopperCommand(hopperSubsystem, Constants.HOPPER_SPEED),
                solo,new WaitCommand(1),new DefaultDrive(drive, 0));
   }

   public void inititialize(){
   }

   @Override
   public void addCommands(Command... commands) {
      auto=sequence(commands);
   }
   public void execute(){
      if(!done){
         auto.schedule();
         done=true;
      }
   }
   public boolean isFinished(){
      return done;
   }
   public void end(boolean interrupted){
      done=false;
   }

 }