package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Linkage
{
    public static int maxPromotion = 100000000;
    public static int minPromotion = 0;
    
    DcMotor m;
    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad g;
    
    public Linkage(LinearOpMode linearOpMode)
    {
        this.linearOpMode = linearOpMode;
        hardwareMap = linearOpMode.hardwareMap;
        g = linearOpMode.gamepad1;
        m = hardwareMap.get(DcMotor.class, "linkage");
        m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        m.setDirection(DcMotorSimple.Direction.REVERSE);
        m.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    
    public void Manual()
    {
        m.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if (-g.right_stick_y > 0.1 && m.getCurrentPosition() < maxPromotion)
            m.setPower(-g.right_stick_y);
        else if (-g.right_stick_y < -0.1 && m.getCurrentPosition() > minPromotion)
            m.setPower(-g.right_stick_y);
        else
            m.setPower(0);
    }
    
    public void Promotion()
    {
        m.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m.setTargetPosition(maxPromotion);
        m.setPower(1);
    }
    
    public void Start()
    {
        m.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        m.setTargetPosition(minPromotion);
        m.setPower(1);
    }
}
