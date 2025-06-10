package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw
{
    Servo opener;
    Servo rotate;
    public static double open;
    public static double close;
    public static double take_spec;
    public static double take_spec_up;
    public static double score_spec;
    public static double score_sample;
    public static double take_sample;
    
    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad1;
    
    public Claw(LinearOpMode linearOpMode)
    {
        this.linearOpMode = linearOpMode;
        hardwareMap = linearOpMode.hardwareMap;
        gamepad1 = linearOpMode.gamepad1;
        opener = hardwareMap.get(Servo.class, "Claw");
        rotate = hardwareMap.get(Servo.class,"ClawAngle");
    }
    
    public void Open_Close()
    {
        if (gamepad1.a)
            opener.setPosition(open);
        else if (gamepad1.b)
            opener.setPosition(close);
    }
    public void TakeSpec(){rotate.setPosition(take_spec);}
    public void TakeSpecUp(){rotate.setPosition(take_spec_up);}
    public void ScoreSpec(){rotate.setPosition(score_spec);}
    public void ScoreSample(){rotate.setPosition(score_sample);}
    public void TakeSample(){rotate.setPosition(take_sample );}
}
