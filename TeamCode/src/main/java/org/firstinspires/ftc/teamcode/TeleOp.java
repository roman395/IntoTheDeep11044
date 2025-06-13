package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends LinearOpMode
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
        waitForStart();
        while (opModeIsActive())
        {
            train.TeleOp();
            linkage.TeleOp();
            if (g.right_bumper)
            {
                intake.InMode();
                intake.TakeRot();
            }
            else if (g.left_bumper)
                intake.OutMode();
            else
                intake.OffMode();
            if (g.options)
                intake.OutRot();
            switch (s)
            {
                case TAKE_WITHOUT_SAMPLE:
                    if (intake.GetState())
                    {
                        s = States.TAKE_WITH_SAMPLE;
                        intake.OutRot();
                    }
                    break;
                case TAKE_WITH_SAMPLE:
                    if (g.right_bumper)
                        inputAfterTake = true;
                    if (inputAfterTake)
                        if (intake.GetState())
                            intake.OutMode();
                        else
                        {
                            s = States.BROADCAST;
                            inputAfterTake = false;
                        }
                    else if (!intake.GetState())
                        s = States.RUNG;
                    break;
                case BROADCAST:
                    lift.TakeSample();
                    perekid.Take_Sample_Pose();
                    claw.TakeSample();
                    claw.Open();
                    if (claw.Color() == Claw.requirement_number_for_sensor)
                    {
                        claw.Close();
                        s = States.BASKET;
                    }
                    break;
                case BASKET:
                    lift.ScoreSample();
                    claw.ScoreSpec();
                    perekid.Score_Pose();
                    if (g.triangle)
                    {
                        claw.Open();
                        s = States.RESET;
                    }
                    break;
                case RESET:
                    if (g.triangle)
                    {
                        perekid.Take_Sample_Pose();
                        claw.TakeSample();
                        lift.TakeSample();
                        s = States.TAKE_WITHOUT_SAMPLE;
                    }
                    break;
                case RUNG:
                    perekid.ParallelPos();
                    claw.ScoreSpec();
                    lift.ScoreSpec();
                    if(g.triangle){
                        claw.Open();
                        s = States.RESET;
                    }
                    break;
            }
            linkage.PIDController();
            telemetry.addData("State", s);
            telemetry.update();
            
        }
    }
}
