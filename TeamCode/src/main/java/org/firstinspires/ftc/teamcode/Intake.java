package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Intake
{
  public static double takeRot = 0.55;
  public static double outRot = 0.01;
  private final DcMotor m;
  Servo serv;
  ColorSensor s;
  private boolean sthIn;
  LinearOpMode opmode;

  public Intake(LinearOpMode linearOpMode)
  {
    opmode = linearOpMode;
    HardwareMap hardwareMap = linearOpMode.hardwareMap;
    m = hardwareMap.get(DcMotor.class, "Intake");
    s = hardwareMap.get(ColorSensor.class, "sensor");
    serv = hardwareMap.get(Servo.class, "IntakeS");

  }

  public void BlueUpdate()
  {
    //blue alliance

    sthIn = (s.blue() > s.red() && s.alpha() > 44) || (s.green() > s.red() && s.green() > s.blue() && s.alpha() > 44 && s.green() > 100);
    opmode.telemetry.addLine("Working");

  }

  public void RedUpdate()
  {
    //red alliance
    if (serv.getPosition() == takeRot || serv.getPosition() == outRot)
      sthIn = (s.red() >= s.blue() && s.alpha() > 44) || (s.green() >= s.red() && s.green() >= s.blue() && s.alpha() > 44);
  }

  public boolean GetState() {return sthIn;}

  public void InMode() {m.setPower(-1);}

  public void OutMode() {m.setPower(1);}

  public void OffMode() {m.setPower(0);}

  public void TakeRot() {serv.setPosition(takeRot);}

  public void OutRot() {serv.setPosition(outRot);}
}
