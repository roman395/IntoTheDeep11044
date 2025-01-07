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
            s1.setPosition(0.48);
            s2.setPosition(0.48);
        } else {
            s1.setPosition(0.878);
            s2.setPosition(0.878);
        }
    }

    public void Control() {

        if (g2.triangle) {
            s1.setPosition(0.48);
            s2.setPosition(0.48);
        }
        else if (g2.circle){
            s1.setPosition(0.8);
            s2.setPosition(0.8  );
        }
        else if (g2.cross) {
            s1.setPosition(0.878);
            s2.setPosition(0.878);
        }
    }
}

