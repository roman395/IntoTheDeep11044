package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
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

        s1=hardwareMap.get(CRServo.class,"leftP");
        s2=hardwareMap.get(CRServo.class,"rightP");
        s2.setDirection(DcMotorSimple.Direction.REVERSE);
        g2=linearOpMode.gamepad2;


    }
    public void Control() {
       float i = -g2.right_stick_y;
       s2.setPower(i);
       s1.setPower(i);

    }
}
