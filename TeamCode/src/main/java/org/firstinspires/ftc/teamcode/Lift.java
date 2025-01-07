package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift{
    public DcMotorEx motorL;
    public DcMotorEx motorR;

    public int maxRot=4500;
    public int specScoreRot=1950;
    public int minRot=0;

    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad g2;

    public Lift(LinearOpMode linearOpMode){
        this.linearOpMode=linearOpMode;
        hardwareMap=linearOpMode.hardwareMap;
        motorR=hardwareMap.get(DcMotorEx.class,"rightLift");
        motorL=hardwareMap.get(DcMotorEx.class,"leftLift");
        motorL.setDirection(DcMotorSimple.Direction.REVERSE);
        motorR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        g2=linearOpMode.gamepad1;
    }
    public  void Autonom(int range){

        motorR.setTargetPosition(range);
        motorL.setTargetPosition(range);
        motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorR.setVelocity(2000);
        motorL.setVelocity(2000);

    }
    public void Control() {
        motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        if (g2.dpad_up) {
            motorL.setPower(1);
            motorR.setPower(1);
        }
        else if (g2.dpad_down) {
            motorR.setPower(-1);
            motorL.setPower(-1);
        }
        else {
            motorL.setPower(0);
            motorR.setPower(0);
        }
    }

}
