package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class tests extends LinearOpMode
{
    public DcMotor m;
    
    @Override
    public void runOpMode() throws InterruptedException
    {
        m = hardwareMap.get(DcMotor.class, "test");
        
        waitForStart();
        while (opModeIsActive())
        {
            telemetry.addData("Pos:", m.getCurrentPosition());
            telemetry.update();
            m.setPower(1);
        }
    }
}
