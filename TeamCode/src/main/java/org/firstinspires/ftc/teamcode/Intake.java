package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class Intake {
    CRServo servo;
    DigitalChannel fish;
    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad2;

    public Intake(LinearOpMode linearOpMode) {
        this.linearOpMode = linearOpMode;
        hardwareMap = linearOpMode.hardwareMap;
        servo = hardwareMap.get(CRServo.class, "Intake");
        //fish=hardwareMap.get(DigitalChannel.class,"FlyingFish");
        //fish.getState();
        gamepad2 = linearOpMode.gamepad1;
    }

    //0-off
    //1-in
    //-1-out
    public void Autonom(int i) {
        if (i == -1) servo.setPower(1);
        else if (i == 1) servo.setPower(-1);
        else if (i == 0) servo.setPower(0);

    }

    public void Control() {
        if (gamepad2.left_bumper) {
            servo.setPower(1);
        } else if (gamepad2.right_bumper) {
            servo.setPower(-1);
        } else {
            servo.setPower(0);
        }
    }

}
