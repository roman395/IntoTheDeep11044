package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

//TODO set positions and check motor
@Config
public class Lift
{
  public static int take_Pos;
  public static int score_sample_pos;
  public static int score_spec_pos;
  DcMotor mR;
  DcMotor mL;
  LinearOpMode linearOpMode;
  HardwareMap hardwareMap;
  Gamepad gamepad1;
  ElapsedTime timer;

  public Lift(LinearOpMode linearOpMode)
  {
    this.linearOpMode = linearOpMode;
    hardwareMap = linearOpMode.hardwareMap;
    gamepad1 = linearOpMode.gamepad1;
    mR = hardwareMap.get(DcMotor.class, "liftR");
    mL = hardwareMap.get(DcMotor.class, "liftL");
    mR.setDirection(DcMotorSimple.Direction.REVERSE);
    timer = new ElapsedTime();
    timer.reset();
  }

  public void TakeSample()
  {
    mL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    mR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    mL.setTargetPosition(take_Pos);
    mR.setTargetPosition(take_Pos);

    mL.setPower(1);
    mR.setPower(1);
  }

  public void ScoreSample()
  {
    mL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    mR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    mL.setTargetPosition(score_sample_pos);
    mR.setTargetPosition(score_sample_pos);

    mL.setPower(1);
    mR.setPower(1);
  }

  public void ScoreSpec()
  {
    mL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    mR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

    mL.setTargetPosition(score_spec_pos);
    mR.setTargetPosition(score_spec_pos);

    mL.setPower(1);
    mR.setPower(1);
  }

  public void TeleOp()
  {
    if (gamepad1.dpad_down)
    {
      mR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      mL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      mR.setPower(1);
      mL.setPower(1);
    }
    if (gamepad1.dpad_up)
    {
      mR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      mL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      mR.setPower(-1);
      mL.setPower(-1);
    }
    else
    {
      mR.setPower(0);
      mL.setPower(0);
    }
  }

  double integralSum = 0;
  double out;
  double lastError = 0;
  public static PIDFCoefficients cof = new PIDFCoefficients(0.01, 0, 1e-5, 0);

  public double PIDController(DcMotor m)
  {
    int targetPos = m.getTargetPosition();
    int position = m.getCurrentPosition();
    double error = targetPos - position;

    double derivative = (error - lastError) / timer.seconds();
    integralSum += error * timer.seconds();
    out = (cof.p * error) + (cof.i * integralSum) + (cof.d * derivative) + cof.f;
    m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    m.setPower(out);

    lastError = error;
    timer.reset();
    return error;
  }
}
