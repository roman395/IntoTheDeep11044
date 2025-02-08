package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift {
    public DcMotorEx motorL;
    public DcMotorEx motorR;

    public final  int maxRot = 3800;
    public final  int specScoreRot = 1650;
    public final  int minRot = 0;
    public static int currentRot;
    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad g2;

    public Lift(LinearOpMode linearOpMode) {
        this.linearOpMode = linearOpMode;
        hardwareMap = linearOpMode.hardwareMap;
        motorR = hardwareMap.get(DcMotorEx.class, "rightLift");
        motorL = hardwareMap.get(DcMotorEx.class, "leftLift");
        motorL.setDirection(DcMotorSimple.Direction.REVERSE);
        motorR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        g2 = linearOpMode.gamepad1;
    }

    public void Control() {
        if (g2.dpad_up) {
            motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorL.setPower(0.5);
            motorR.setPower(0.5);
        } else if(g2.dpad_down) {
            motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorL.setPower(-0.5);
            motorR.setPower(-0.5);
        }
        else{
            motorR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorL.setPower(0);
            motorR.setPower(0);
        }
        if(g2.touchpad){
            motorR.setTargetPosition(maxRot);
            motorL.setTargetPosition(maxRot);


            motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorL.setPower(1);
            motorR.setPower(1);
        }
        else if(g2.right_stick_button){
            motorR.setTargetPosition(minRot);
            motorL.setTargetPosition(minRot);

            motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorL.setPower(1);
            motorR.setPower(1);
        }
    }

    public void Autonom(int range) {

        motorR.setTargetPosition(range);
        motorL.setTargetPosition(range);

        motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorL.setPower(1);
        motorR.setPower(1);

    }

    public void Up() {
        motorR.setTargetPosition(maxRot);
        motorL.setTargetPosition(maxRot);


        motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorL.setPower(1);
        motorR.setPower(1);

    }

    public void Down() {
        motorR.setTargetPosition(minRot);
        motorL.setTargetPosition(minRot);

        motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorL.setPower(1);
        motorR.setPower(1);

    }

    public void Spec() {
        motorR.setTargetPosition(specScoreRot);
        motorL.setTargetPosition(specScoreRot);
        motorL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

}
