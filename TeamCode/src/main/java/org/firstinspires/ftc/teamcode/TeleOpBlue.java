package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOpBlue extends LinearOpMode
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
      if (g.options)
      {
        intake.OutRot();
        s = States.RESET;
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
          if (g.square)
            inputAfterTake = true;
          if (g.left_bumper)
            intake.OutMode();
          else
            intake.OffMode();
          if (inputAfterTake)
          {
            if (intake.GetState())
              intake.InMode();
            else
            {
              s = States.BROADCAST;
              intake.OffMode();
              inputAfterTake = false;
            }
          }
          else if (!intake.GetState())
            s = States.RUNG;
          break;
        case BROADCAST:
          claw.TakeSample();
          claw.Open();
          linkage.BroadCast();
          if (linkage.getState())
            perekid.Take_Sample_Pose();
          if (linkage.getState() && claw.GetState() >= 3)
          {
            claw.Close();
            linkage.resetState();
            s = States.BASKET;
          }

          break;
        case BASKET:
          //lift.ScoreSample();
          claw.ScoreSpec();
          perekid.Score_Pose();
          if (g.triangle)
          {
            claw.Open();
            s = States.RESET;
          }
          break;
        case RESET:
          if (g.square)
          {
            linkage.resetState();
            perekid.ParallelPos();
            claw.ScoreSpec();
            claw.Open();
            //  lift.TakeSample();
            s = States.TAKE_WITHOUT_SAMPLE;
          }
          break;
        case RUNG:
          perekid.ParallelPos();
          intake.OffMode();
          claw.ScoreSpec();
          // lift.ScoreSpec();
          if (g.triangle)
          {
            claw.Open();
            s = States.RESET;
          }
          break;
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
