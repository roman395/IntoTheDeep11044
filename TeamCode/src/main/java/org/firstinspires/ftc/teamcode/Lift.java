package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class Lift
{
    public static int takePos;
    public static int scorePos;
    DcMotor m1;
    DcMotor m2;
    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad1;
    
    public Lift(LinearOpMode linearOpMode)
    {
        this.linearOpMode = linearOpMode;
        hardwareMap = linearOpMode.hardwareMap;
        gamepad1 = linearOpMode.gamepad1;
        m1 = hardwareMap.get(DcMotor.class, "liftR");
        m2 = hardwareMap.get(DcMotor.class, "liftL");
        
        m2.setDirection(DcMotorSimple.Direction.REVERSE);
        
    }
    
    public void TeleOp()
    {
        if (gamepad1.a)
        {
            
            m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            m2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            
            m1.setTargetPosition(takePos);
            m2.setTargetPosition(takePos);
            
            m1.setPower(1);
            m2.setPower(1);
        }
        else if (gamepad1.b)
        {
            m1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            m2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            
            m1.setTargetPosition(scorePos);
            m2.setTargetPosition(scorePos);
            
            m1.setPower(1);
            m2.setPower(1);
        }
    }
    
}
