package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class Perekid{
    CRServo s1;
    CRServo s2;

    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad g2;
    public Perekid(LinearOpMode linearOpMode){
        this.linearOpMode=linearOpMode;
        hardwareMap=linearOpMode.hardwareMap;

        s1=hardwareMap.get(CRServo.class,"Perekid1");
        s2=hardwareMap.get(CRServo.class,"Perekid2");
        s2.setDirection(DcMotorSimple.Direction.REVERSE);
        g2=linearOpMode.gamepad2;

    }
    public void Control() {
        if (g2.triangle) {

            s1.setPower(1);
            s2.setPower(1);

        } else if (g2.cross) {

            s1.setPower(-1);
            s2.setPower(-1);
        }
        else {
            s1.setPower(0);
            s2.setPower(0);
        }
    }
}
