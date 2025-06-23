package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOpBlue extends LinearOpMode {
  enum States {
    TAKE_WITHOUT_SAMPLE,
    TAKE_WITH_SAMPLE,
    BROADCAST,
    BASKET,
    After_Basket,
    RESET,
    Score_Spec,
    INIT,
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
  public void runOpMode() throws InterruptedException {
    States s = States.INIT;
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
    while (opModeIsActive()) {
      intake.BlueUpdate();
      train.TeleOp();
      linkage.TeleOp();
      lift.TeleOp();
      if ((-g.right_stick_y <= 0.1 && -g.right_stick_y >= -0.1) || (linkage.getPosition() > Linkage.maxPromotion
              || linkage.getPosition() < Linkage.minPromotion))
        linkage.PIDController();

      switch (s) {

        case INIT:
          intake.OutRot();
          //добавить лифт идет в нолик
          perekid.Take_Spec_Pose();
          claw.Open();
          claw.TakeSpec();

          s = States.TAKE_WITHOUT_SAMPLE;

        case TAKE_WITHOUT_SAMPLE:
          if (claw.GetState() >= 3) {
            claw.Close();
            claw.TakeSpecUp();
            linkage.resetState();
            s = States.Score_Spec;
          }
          if (g.right_bumper) {
            intake.InMode();
            intake.TakeRot();
          } else if (g.left_bumper)
            intake.OutMode();
          else
            intake.OffMode();
          if (intake.GetState() && !fake)
            fake = true;
          if (intake.GetState() && fake) {
            s = States.TAKE_WITH_SAMPLE;
            intake.OutRot();
            fake = false;
          }
          break;
        case TAKE_WITH_SAMPLE:
          if (g.right_bumper) {
            while (g.right_bumper)
              intake.InMode();
            s = States.BASKET;


          } else if (g.left_bumper) {
            while (g.left_bumper)
              intake.OutMode();
            s = States.INIT;
          } else
            intake.OffMode();
          break;
        case BASKET:
          //лифт на приподнятую позицию
          claw.Open();
          claw.TakeSample();
          perekid.Take_Sample_Pose();
          // лифт в позицию взятия НАСТРОИТЬ
          if (linkage.getState() && claw.GetState() >= 3) {
            claw.Close();
            linkage.resetState();
          }
          //ЛИФТ НА Скорингову ПОЗИҮИӨ
          perekid.Score_Pose();
          claw.ScoreSample();
          if (g.circle) {
            claw.Open();
            s = States.After_Basket;
          }
          break;
        case After_Basket:
          if(g.right_stick_x>0.5)
            s = States.INIT;
          break;
        case Score_Spec:
          //лифт на позицию
          perekid.ParallelPos();
          if(g.circle)
            claw.Open();
          if(g.triangle)
            s = States.INIT;




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
}