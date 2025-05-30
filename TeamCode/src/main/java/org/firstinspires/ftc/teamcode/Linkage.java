package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Linkage
{
    public static double maxPromotion;
    public static double minPromotion;
    
    DcMotor m;
    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad1;
    
    public Linkage(LinearOpMode linearOpMode)
    {
        this.linearOpMode = linearOpMode;
        hardwareMap = linearOpMode.hardwareMap;
        gamepad1 = linearOpMode.gamepad1;
        m = hardwareMap.get(DcMotor.class, "linkage");
        m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    
    public void Teleop()
    {
        if (gamepad1.a && m.getCurrentPosition() < maxPromotion)
            m.setPower(1);
        else if (gamepad1.b && m.getCurrentPosition() > minPromotion)
            m.setPower(-1);
        else
            m.setPower(0);
    }
}
