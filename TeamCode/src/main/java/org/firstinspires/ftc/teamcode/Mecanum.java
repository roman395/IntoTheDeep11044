package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class Mecanum
{
  DcMotor FL;
  DcMotor FR;
  DcMotor BL;
  DcMotor BR;
  double rx;
  boolean operation = false;
  IMU imu;

  LinearOpMode linearOpMode;
  HardwareMap hardwareMap;
  Gamepad gamepad1;

  public Mecanum(LinearOpMode linearOpMode)
  {
    this.linearOpMode = linearOpMode;
    hardwareMap = linearOpMode.hardwareMap;
    gamepad1 = linearOpMode.gamepad1;
    FL = hardwareMap.get(DcMotor.class, "leftFront");
    FR = hardwareMap.get(DcMotor.class, "rightFront");
    BL = hardwareMap.get(DcMotor.class, "leftRear");
    BR = hardwareMap.get(DcMotor.class, "rightRear");
    FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    FL.setDirection(DcMotorSimple.Direction.REVERSE);
    BL.setDirection(DcMotorSimple.Direction.REVERSE);
    imu = hardwareMap.get(IMU.class, "imu");
    // Adjust the orientation parameters to match your robot
    IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
            RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
    // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
    imu.initialize(parameters);
  }

  public void TeleOp()
  {
    if (gamepad1.back)
      operation = !operation;
    if (operation)
    {
      rx = -(gamepad1.left_trigger - gamepad1.right_trigger);
      double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
      double x = gamepad1.left_stick_x;

      if (gamepad1.options)
      {
        imu.resetYaw();
      }

      double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

      // Rotate the movement direction counter to the bot's rotation
      double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
      double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

      rotX = rotX * 1.1;  // Counteract imperfect strafing

      // Denominator is the largest motor power (absolute value) or 1
      // This ensures all the powers maintain the same ratio,
      // but only if at least one is out of the range [-1, 1]
      double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
      double frontLeftPower = (rotY + rotX + rx) / denominator;
      double backLeftPower = (rotY - rotX + rx) / denominator;
      double frontRightPower = (rotY - rotX - rx) / denominator;
      double backRightPower = (rotY + rotX - rx) / denominator;

      FL.setPower(frontLeftPower);
      BL.setPower(backLeftPower);
      FR.setPower(frontRightPower);
      BR.setPower(backRightPower);
    }
    else
    {
      double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
      double x = gamepad1.left_stick_x; // Counteract imperfect strafing
      double rx = -(gamepad1.left_trigger - gamepad1.right_trigger);

      // Denominator is the largest motor power (absolute value) or 1
      // This ensures all the powers maintain the same ratio,
      // but only if at least one is out of the range [-1, 1]
      double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
      double frontLeftPower = (y + x + rx) / denominator;
      double backLeftPower = (y - x + rx) / denominator;
      double frontRightPower = (y - x - rx) / denominator;
      double backRightPower = (y + x - rx) / denominator;

      FL.setPower(frontLeftPower);
      BL.setPower(backLeftPower);
      FR.setPower(frontRightPower);
      BR.setPower(backRightPower);
    }
  }
}
