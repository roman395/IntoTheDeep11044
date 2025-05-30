package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorRangeSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake
{
    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad1;
    DcMotor m;
    ColorRangeSensor s;
    boolean sthIn;
    public Intake(LinearOpMode linearOpMode)
    {
        this.linearOpMode = linearOpMode;
        hardwareMap = linearOpMode.hardwareMap;
        gamepad1 = linearOpMode.gamepad1;
        m = hardwareMap.get(DcMotor.class, "Intake");
        s = hardwareMap.get(ColorRangeSensor.class,"sensor");
    }
    
    public void Teleop()
    {
        
        if (gamepad1.a)
            m.setPower(1);
        else if (gamepad1.b)
            m.setPower(-1);
        else
            m.setPower(0);
        
    }
}

