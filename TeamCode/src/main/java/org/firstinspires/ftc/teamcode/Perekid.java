package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class Perekid{
    ServoImplEx s1;
    ServoImplEx s2;

    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad g2;
    public Perekid(LinearOpMode linearOpMode){
        this.linearOpMode=linearOpMode;
        hardwareMap=linearOpMode.hardwareMap;

        s1=hardwareMap.get(ServoImplEx.class,"leftP");
        s2=hardwareMap.get(ServoImplEx.class,"rightP");
        g2=linearOpMode.gamepad2;


    }
    public void Control() {
        if (g2.triangle) {
            s1.setPosition(1);
            s2.setPosition(0);

        } else if (g2.cross) {

            s1.setPosition(0);
            s2.setPosition(1);

        }

    }
}
