package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@Config
@TeleOp(group = "test")
public class MotorSetPower extends LinearOpMode
{
    public static String motorConfig = "";
    public static double power = 0;
    
    @Override
    public void runOpMode() throws InterruptedException
    {
        DcMotor m = hardwareMap.get(DcMotor.class, motorConfig);
        waitForStart();
        while (opModeIsActive())
        {
            m.setPower(power);
            telemetry.addData("servo pose", m.getCurrentPosition());
            telemetry.update();
        }
    }
}
