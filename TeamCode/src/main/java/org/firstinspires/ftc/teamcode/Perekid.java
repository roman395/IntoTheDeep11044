package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class Perekid {
    ServoImplEx s1;
    ServoImplEx s2;

    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad g2;

    public Perekid(LinearOpMode linearOpMode) {
        this.linearOpMode = linearOpMode;
        hardwareMap = linearOpMode.hardwareMap;

        s1 = hardwareMap.get(ServoImplEx.class, "leftP");
        s2 = hardwareMap.get(ServoImplEx.class, "rightP");
        s1.setDirection(Servo.Direction.REVERSE);
        s2.setDirection(Servo.Direction.REVERSE);
        g2 = linearOpMode.gamepad2;
      s1.setPwmEnable();
    s2.setPwmEnable();

    }

    public void Control() {
        if (g2.left_trigger >= 0.5) {
            s1.setPwmDisable();
            s2.setPwmDisable();
        } else {
            s1.setPwmEnable();
            s2.setPwmEnable();
        }
        if (g2.triangle) {
            s1.setPosition(0);
            s2.setPosition(1);
        } else if (g2.cross) {
            s1.setPosition(1);
            s2.setPosition(0);
        } else if (g2.circle) {
            s1.setPwmDisable();
            s2.setPwmDisable();
            s1.close();
            s2.close();
        }

    }
}
