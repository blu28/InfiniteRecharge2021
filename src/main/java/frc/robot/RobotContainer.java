/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AdjustLauncherCommand;
import frc.robot.commands.ChangeLauncherSpeedCommand;
import frc.robot.commands.DriveWithJoysticksCommand;
import frc.robot.commands.HangPullCommand;
import frc.robot.commands.HangReachCommand;
import frc.robot.commands.HopperCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.IntakeHomeCommand;
import frc.robot.commands.autonomous.AutonomousCommand;
import frc.robot.commands.autonomous.AutonomousCommand1;
import frc.robot.commands.DeployIntakeCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.HangSubsystem;
import frc.robot.subsystems.HopperSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.LauncherSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;
import frc.robot.subsystems.PDPSubsystem;
import frc.robot.commands.IntakeCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import io.github.oblarg.oblog.Logger;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private SendableChooser<Command> chooser=new SendableChooser<>();
  // OI joysticks 
  
  public  Joystick driverOI;
  public static  Joystick coDriverOI;
  // The robot's subsystems and commands are defined here...
  private  DriveSubsystem m_driveSubsystem;
  private  LimeLightSubsystem m_LimeLightSubsystem;
  private  DriveWithJoysticksCommand m_DriveWithJoysticksCommand;
  public  HopperSubsystem m_hopperSubsystem;
  private  HopperCommand m_hopperCommand;
  private PDPSubsystem pdp;
  public static PIDController launcherPID;
  private LauncherSubsystem mLauncherSubsystem;
  public IntakeSubsystem mIntakeSubsystem;
  private IntakeCommand feedIntakeCommand;
  private IntakeCommand stopIntakeCommand;
  private IntakeCommand reverseIntake;
  private AutonomousCommand AUTO;
  private AutonomousCommand1 AUTO1;
  private IntakeHomeCommand home;
  private HangSubsystem mhang;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    // Shuffleboard.addEventMarker("LauncherSpeed", EventImportance.kHigh);
    Logger.configureLoggingAndConfig(this, false);
    configCommands();
    configureButtonBindings();

  }

  public void configCommands() {
    driverOI = new Joystick(0);
    coDriverOI = new Joystick(1);
    // The robot's subsystems and commands are defined here...
    m_driveSubsystem = new DriveSubsystem();
    m_LimeLightSubsystem = new LimeLightSubsystem();
    m_DriveWithJoysticksCommand = new DriveWithJoysticksCommand(m_driveSubsystem, driverOI, m_LimeLightSubsystem,
        coDriverOI);
    m_hopperSubsystem = new HopperSubsystem();
    m_hopperCommand = new HopperCommand(m_hopperSubsystem, .90, coDriverOI);

    launcherPID = new PIDController(Constants.LAUNCHERKP, Constants.LAUNCHERKI, Constants.LAUNCHERKD);
    mLauncherSubsystem = new LauncherSubsystem(launcherPID);
    mIntakeSubsystem = new IntakeSubsystem();
    feedIntakeCommand = new IntakeCommand(mIntakeSubsystem, -0.3);
    stopIntakeCommand = new IntakeCommand(mIntakeSubsystem, 0);
    reverseIntake = new IntakeCommand(mIntakeSubsystem, 0.3);
    AUTO = new AutonomousCommand(m_driveSubsystem, mLauncherSubsystem, m_LimeLightSubsystem, m_hopperSubsystem);
    AUTO1 = new AutonomousCommand1(m_driveSubsystem, mLauncherSubsystem, m_LimeLightSubsystem, m_hopperSubsystem);
    home=new IntakeHomeCommand(mIntakeSubsystem);
    mhang=new HangSubsystem();
    CommandScheduler.getInstance().setDefaultCommand(m_driveSubsystem, m_DriveWithJoysticksCommand);
    chooser.addOption("limeLight disabled", AUTO1);
    chooser.addOption("limeLight enabled", AUTO);
    SmartDashboard.putData("Autonomous mode:",chooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    JoystickButton Ac = new JoystickButton(coDriverOI, 1);
    Ac.whenPressed(new AdjustLauncherCommand(mLauncherSubsystem, m_LimeLightSubsystem));
    JoystickButton Bc = new JoystickButton(coDriverOI, 2);
    Bc.whenPressed(new ChangeLauncherSpeedCommand(0, mLauncherSubsystem));
    JoystickButton Xc = new JoystickButton(coDriverOI, 3);
    Xc.whenPressed(new ChangeLauncherSpeedCommand(2750, mLauncherSubsystem));// 2950
    JoystickButton Yc = new JoystickButton(coDriverOI, 4);
    Yc.whenPressed(new ChangeLauncherSpeedCommand(3000,mLauncherSubsystem));// 3750
    JoystickButton LEFT_BUMPERc = new JoystickButton(coDriverOI, 5);
    LEFT_BUMPERc.whenHeld(new ChangeLauncherSpeedCommand(-500, mLauncherSubsystem));
    JoystickButton RIGHT_BUMPERc = new JoystickButton(coDriverOI, 6);
    //RIGHT_BUMPERc.whenHeld(m_hopperCommand);
    JoystickButton BACK = new JoystickButton(driverOI, 7);
    JoystickButton START = new JoystickButton(driverOI, 8);
    JoystickButton A = new JoystickButton(driverOI, 1);
    A.whenPressed(feedIntakeCommand);
    JoystickButton B = new JoystickButton(driverOI, 2);
    B.whenPressed(stopIntakeCommand);
    JoystickButton X = new JoystickButton(driverOI, 3);
    X.whenPressed(reverseIntake);
    JoystickButton Y = new JoystickButton(driverOI, 4);
    Y.whenPressed(home);
    Button dPadRight=new Button(){
      @Override
      public boolean get(){
        return driverOI.getPOV(0)==90;
      }
    };
    Button dPadLeft=new Button(){
      @Override
      public boolean get(){
        return driverOI.getPOV(0)==270;
      }
    };
    Button dPadDown=new Button(){
      @Override
      public boolean get(){
        return driverOI.getPOV(0)==180;
      }
    };
    Button dPadUp=new Button(){
      @Override
      public boolean get(){
        return driverOI.getPOV(0)==0||driverOI.getPOV(0)==360;
      }
    };
    dPadRight.whenPressed(new DeployIntakeCommand(mIntakeSubsystem, 1));
    dPadLeft.whenPressed(new DeployIntakeCommand(mIntakeSubsystem, -1));
    dPadUp.whenPressed(new HangReachCommand(mhang));
    dPadDown.whenPressed(new HangPullCommand(mhang));

  }

  public Command getDriveCommand() {

    return m_DriveWithJoysticksCommand;
  }

  public static PIDController getPidController() {
    return launcherPID;
  }

  public Command getHopperCommand() {
    return m_hopperCommand;
  }

  public Command getAutonomousCommand() {
    return chooser.getSelected();
  }
  public Command getHomeCommand(){
    return home;
  }
  public Command getStopIntakeCommand(){
    return stopIntakeCommand;
  }
}
