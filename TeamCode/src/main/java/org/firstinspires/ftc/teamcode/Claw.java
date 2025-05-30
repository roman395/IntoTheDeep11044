package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Claw
{
    public Servo s;
    public static double open;
    public static double close;
    public static double up;
    public static double down;
    
    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad1;
    
    public Claw(LinearOpMode linearOpMode)
    {
        this.linearOpMode = linearOpMode;
        hardwareMap = linearOpMode.hardwareMap;
        gamepad1 = linearOpMode.gamepad1;
        s = hardwareMap.get(Servo.class, "Claw");
    }
    
    public void TeleOp()
    {
        if (gamepad1.a)
            s.setPosition(open);
        else if (gamepad1.b)
            s.setPosition(close);
    }
}
