package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;  


@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends LinearOpMode {
    Mecanum mecanum;
    Intake i;
    Lift l;
    Perekid p;
    @Override
    public void runOpMode() throws InterruptedException {
        mecanum=new Mecanum(this);
        i=new Intake(this);
        l=new Lift(this);
        p=new Perekid(this);
        waitForStart();
        while (opModeIsActive()) {
            mecanum.TeleOp();
            p.Control();
            i.Control();
            l.Control();
            telemetry.addData("PositionL",l.motorL.getCurrentPosition());
            telemetry.addData("PositionR",l.motorR.getCurrentPosition());
            telemetry.update();
        }

    }
}

