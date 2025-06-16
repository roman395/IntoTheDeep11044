package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class Linkage
{
  public static int maxPromotion = 190;
  public static int minPromotion = 0;
  public static int broadcast_promotion = 37;

  DcMotor m;
  LinearOpMode linearOpMode;
  HardwareMap hardwareMap;
  Gamepad g;
  public static PIDFCoefficients cof = new PIDFCoefficients(0.01, 0, 1e-5, 0);

  double targetPos = 0;
  double lastError = 0;
  double integralSum = 0;
  double out;
  private boolean done = false;
  ElapsedTime timer;

  public Linkage(LinearOpMode linearOpMode)
  {

    this.linearOpMode = linearOpMode;
    hardwareMap = linearOpMode.hardwareMap;
    g = linearOpMode.gamepad1;
    m = hardwareMap.get(DcMotor.class, "linkage");
    m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    m.setDirection(DcMotorSimple.Direction.REVERSE);
    m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    timer = new ElapsedTime();
    timer.reset();
  }

  public void TeleOp()
  {
    if (g.dpad_left)
      maxPromotion -= 10;
    else if (g.dpad_right)
      maxPromotion += 100;

    if (-g.right_stick_y > 0 && m.getCurrentPosition() < maxPromotion)
    {
      m.setPower(-g.right_stick_y);
    }
    else if (-g.right_stick_y < 0 && m.getCurrentPosition() > minPromotion)
    {
      m.setPower(-g.right_stick_y);
    }
    if (-g.right_stick_y >= 0.1 || -g.right_stick_y <= -0.1)
    {
      targetPos = m.getCurrentPosition();
    }


    linearOpMode.telemetry.addData("currentPos", m.getCurrentPosition());
    linearOpMode.telemetry.addData("targetPos", targetPos);
  }

  public boolean getState() {return done;}

  public void resetState() {done = false;}

  public void BroadCast()
  {
    targetPos = broadcast_promotion;

    if (error < 3 && targetPos == m.getCurrentPosition())
      done = true;
  }

  public int getPosition() {return m.getCurrentPosition();}

  double error = 0;

  public double PIDController()
  {
    int position = m.getCurrentPosition();
    error = targetPos - position;

    double derivative = (error - lastError) / timer.seconds();
    integralSum += error * timer.seconds();
    out = (cof.p * error) + (cof.i * integralSum) + (cof.d * derivative) + cof.f;
    m.setPower(out);

    lastError = error;
    timer.reset();
    return error;
  }
}
