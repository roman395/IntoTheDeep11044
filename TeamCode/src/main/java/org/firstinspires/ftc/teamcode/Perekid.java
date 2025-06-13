package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Perekid
{
    public static double take_spec_pos = 0.97;
    public static double parallelPos = 0.38;
    public static double scorePos = 0.64;
    public static double take_sample_pos = 0.64;
    
    Servo leftPerekid;
    Servo rightPerekid;
    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad1;
    
    public Perekid(LinearOpMode linearOpMode)
    {
        this.linearOpMode = linearOpMode;
        hardwareMap = linearOpMode.hardwareMap;
        gamepad1 = linearOpMode.gamepad1;
        leftPerekid = hardwareMap.get(Servo.class, "LeftPerekid");
        rightPerekid = hardwareMap.get(Servo.class, "RightPerekid");
        leftPerekid.setDirection(Servo.Direction.REVERSE);
    }
    
    public void ParallelPos()
    {
        leftPerekid.setPosition(parallelPos);
        rightPerekid.setPosition(parallelPos);
    }
    public void Take_Spec_Pose()
    {
        leftPerekid.setPosition(take_spec_pos);
        rightPerekid.setPosition(take_spec_pos);
    }
    public void Score_Pose()
    {
        leftPerekid.setPosition(scorePos);
        rightPerekid.setPosition(scorePos);
    }
    public void Take_Sample_Pose()
    {
        leftPerekid.setPosition(take_sample_pos);
        rightPerekid.setPosition(take_sample_pos);
    }
    
}
