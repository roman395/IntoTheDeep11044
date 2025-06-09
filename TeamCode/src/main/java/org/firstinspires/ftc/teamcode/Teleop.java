package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;

@TeleOp
public class Teleop extends LinearOpMode
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
        //claw = new Claw(this);
        //lift = new Lift(this);
        linkage = new Linkage(this);
        //perekid = new Perekid(this);
        waitForStart();
        while (opModeIsActive())
        {
            train.TeleOp();
            intake.Update();
            switch (s)
            {
                case WITH_SAMPLE:
                    linkage.Start();
                    intake.OutRot();
                    if(!intake.GetState())
                        intake.OffMode();
                    
                    break;
                case WITHOUT_SAMPLE:
                    linkage.Manual();
                    if(g.cross)
                    {
                        intake.TakeRot();
                        intake.InMode();
                    }
                    else if(g.circle){
                        intake.OffMode();
                        intake.OutRot();
                    }
                    
                    if(intake.GetState())
                        s = States.WITH_SAMPLE;
                    break;
            }
            
            telemetry.addData("linkage", linkage.m.getCurrentPosition());
            telemetry.addData("Intake Servo", intake.serv.getPosition());
            telemetry.update();
        }
    }
}
