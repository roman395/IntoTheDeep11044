package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends LinearOpMode
{
    enum States
    {
        WITHOUT_SAMPLE,
        WITH_SAMPLE
    }
    
    Intake intake;
    Mecanum train;
    Claw claw;
    Lift lift;
    Linkage linkage;
    Perekid perekid;
    Gamepad g;
    
    @Override
    public void runOpMode() throws InterruptedException
    {
        States s = States.WITHOUT_SAMPLE;
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
            linkage.Manual();
            train.TeleOp();
            intake.Update();
            lift.TeleOp();
            if (g.circle)
            {
                perekid.parallelPos();
            }
            if (g.triangle)
            {
                intake.OutRot();
                s = States.WITHOUT_SAMPLE;
            }
            switch (s)
            {
                case WITH_SAMPLE:
                    intake.OutRot();
                    if (!intake.GetState())
                    {
                        intake.OffMode();
                    }
                    else
                    {
                        intake.InMode();
                    }
                    break;
                case WITHOUT_SAMPLE:
                    if (g.right_bumper)
                    {
                        intake.TakeRot();
                        intake.InMode();
                    }
                    else if (g.left_bumper)
                    {
                        intake.OutMode();
                    }
                    else
                        intake.OffMode();
                    
                    if (intake.GetState())
                    {
                        s = States.WITH_SAMPLE;
                    }
                    break;
            }
            
            telemetry.addData("Color_perekid:", claw.Color());
            telemetry.addData("Right perekid", perekid.rightPerekid.getPosition());
            telemetry.update();
        }
    }
}
