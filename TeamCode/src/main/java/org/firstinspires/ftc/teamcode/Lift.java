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

    public final static int maxRot=4560;
    public final static int specScoreRot=1950;
    public final static int minRot=0;
    public static int currentRot;
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
        motorR.setVelocity(10000);
        motorL.setVelocity(10000);
    }
    public  void Autonom(int range){

        motorR.setTargetPosition(range);
        motorL.setTargetPosition(range);
        motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);


    }
    public void Up() {
        motorR.setTargetPosition(maxRot);
        motorL.setTargetPosition(maxRot);
        motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void Down(){
        motorR.setTargetPosition(minRot);
        motorL.setTargetPosition(minRot);
        motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
    public void Spec(){
        motorR.setTargetPosition(specScoreRot);
        motorL.setTargetPosition(specScoreRot);
        motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
