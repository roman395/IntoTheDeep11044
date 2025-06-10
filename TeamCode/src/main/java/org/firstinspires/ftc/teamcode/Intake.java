package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

@Config
public class Intake
{
    public static double takeRot = 0.57;
    public static double outRot = 0.01;
    private final DcMotor m;
    final Servo serv;
    private final ColorSensor s;
    private boolean sthIn;
    
    public Intake(LinearOpMode linearOpMode)
    {
        HardwareMap hardwareMap = linearOpMode.hardwareMap;
        m = hardwareMap.get(DcMotor.class, "Intake");
        s = hardwareMap.get(ColorSensor.class, "sensor");
        serv = hardwareMap.get(Servo.class, "IntakeS");
        
    }
    
    public void Update()
    {
        //red alliance
        //sthIn = (s.red() >= s.blue() && s.alpha() > 36) || (s.green() >= s.red() && s.green() >= s.blue() && s.alpha() > 36);
        //blue alliance
        sthIn = (s.blue() > s.red() && s.alpha() > 36) || (s.green() > s.red() && s.green() > s.blue()
                && s.alpha() > 36 && s.green() > 100);
    }
    
    public boolean GetState() {return sthIn;}
    
    public void InMode() {m.setPower(-1);}
    
    public void OutMode() {m.setPower(1);}
    
    public void OffMode() {m.setPower(0);}
    
    public void TakeRot() {serv.setPosition(takeRot);}
    
    public void OutRot() {serv.setPosition(outRot);}
}
