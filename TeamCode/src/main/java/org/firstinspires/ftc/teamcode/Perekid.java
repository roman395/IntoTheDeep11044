package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Perekid {
    Servo s1;
    Servo s2;

    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad g2;

    public Perekid(LinearOpMode linearOpMode) {
        this.linearOpMode = linearOpMode;
        hardwareMap = linearOpMode.hardwareMap;

        s1 = hardwareMap.get(Servo.class, "leftP");
        s2 = hardwareMap.get(Servo.class, "rightP");
        s1.setDirection(Servo.Direction.REVERSE);
        g2 = linearOpMode.gamepad1;


    }

    public void Autonom(boolean down) {
        if (!down) {
            s1.setPosition(0.45);
            s2.setPosition(0.45);
        } else {
            s1.setPosition(0.93);
            s2.setPosition(0.93);
        }
    }

    public void Control() {

        if (g2.triangle) {
            s1.setPosition(0.45);
            s2.setPosition(0.45);
        }
        else if (g2.circle){
            s1.setPosition(0.88);
            s2.setPosition(0.88);
        }
        else if (g2.cross) {
            s1.setPosition(0.934);
            s2.setPosition(0.934);
        } else if (g2.square) {
            s1.setPosition(0.25);
            s2.setPosition(0.25);

        }
    }
}

