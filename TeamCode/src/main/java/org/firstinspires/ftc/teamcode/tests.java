package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class tests extends LinearOpMode
{
  enum States
  {
    TAKE_WITHOUT_SAMPLE,
    TAKE_WITH_SAMPLE,
    BROADCAST,
    BASKET,
    RESET,
    RUNG
  }

  Intake intake;
  Mecanum train;
  Claw claw;
  Lift lift;
  Linkage linkage;
  Perekid perekid;
  Gamepad g;
  boolean inputAfterTake = false;
  ElapsedTime timer;
  boolean fake = false;

  @Override
  public void runOpMode() throws InterruptedException
  {
    States s = States.TAKE_WITHOUT_SAMPLE;
    g = gamepad1;
    intake = new Intake(this);
    train = new Mecanum(this);
    claw = new Claw(this);
    lift = new Lift(this);
    linkage = new Linkage(this);
    perekid = new Perekid(this);
    timer = new ElapsedTime();
    waitForStart();

    timer.reset();
    while (opModeIsActive())
    {
      intake.BlueUpdate();
      train.TeleOp();
      linkage.TeleOp();
      lift.TeleOp();
      if ((-g.right_stick_y <= 0.1 && -g.right_stick_y >= -0.1) || (linkage.getPosition() > Linkage.maxPromotion
              || linkage.getPosition() < Linkage.minPromotion))
        linkage.PIDController();
      if (g.triangle)
      {
        intake.OutRot();
        //добавить лифт идет в нолик
        perekid.Take_Spec_Pose();
        claw.Open();
        claw.TakeSpec();

        s = States.TAKE_WITHOUT_SAMPLE;
      }
      switch (s)
      {
        case TAKE_WITHOUT_SAMPLE:
          if (g.right_bumper)
          {
            intake.InMode();
            intake.TakeRot();
          }
          else if (g.left_bumper)
            intake.OutMode();
          else
            intake.OffMode();
          if (intake.GetState() && !fake)
            fake = true;
          if (intake.GetState() && fake)
          {
            s = States.TAKE_WITH_SAMPLE;
            intake.OutRot();
            fake = false;
          }
          break;
        case TAKE_WITH_SAMPLE:
          if (g.right_bumper)
          {
            while (g.right_bumper)
              intake.InMode();

          }
          else if (g.left_bumper) {
            while (g.left_bumper)
              intake.OutMode();
            s = States.TAKE_WITHOUT_SAMPLE;
          }
          else
            intake.OffMode();

      }
      telemetry.addData("State", s);
      telemetry.addData("shtIn", intake.GetState());
      telemetry.addData("red", intake.s.red());
      telemetry.addData("green", intake.s.green());
      telemetry.addData("blue", intake.s.blue());
      telemetry.addData("alpha", intake.s.alpha());
      telemetry.addData("servo intake", intake.serv.getPosition());
      telemetry.addData("IsDone", linkage.getState());
      telemetry.update();

    }
  }
}
