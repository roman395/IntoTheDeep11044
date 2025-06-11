package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp(group = "test")
public class ServoSetToZero extends LinearOpMode
{
    public static String servoConfig = "";
    public static double pos = 0.5;
    
    @Override
    public void runOpMode() throws InterruptedException
    {
        Servo s = hardwareMap.get(Servo.class, servoConfig);
        waitForStart();
        while (opModeIsActive())
        {
            s.setPosition(pos);
            telemetry.addData("servo pose", s.getPosition());
            telemetry.update();
        }
    }
}
