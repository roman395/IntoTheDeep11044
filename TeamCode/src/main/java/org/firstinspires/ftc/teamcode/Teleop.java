package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class Teleop extends LinearOpMode
{
    
    Intake intake;
    Mecanum train;
    Claw claw;
    Lift lift;
    Linkage linkage;
    Perekid perekid;
    
    
    @Override
    public void runOpMode() throws InterruptedException
    {
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
            claw.TeleOp();
            lift.TeleOp();`
        }
    }
}
