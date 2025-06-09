package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;
import com.qualcomm.robotcore.hardware.ServoImplEx;

@TeleOp
public class tests extends LinearOpMode
{
    public Servo m;
    
    @Override
    public void runOpMode() throws InterruptedException
    {
        m = hardwareMap.get(Servo.class,"IntakeS");
        waitForStart();
        while (opModeIsActive()){
            m.setPosition(0.5);
           
                   }
    }
}
