package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.LED;

@TeleOp
public class Intake{
    CRServo s;
    Perekid p;
    DigitalChannel fish;
    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad2;
    public Intake(LinearOpMode linearOpMode) {
        this.linearOpMode=linearOpMode;
        hardwareMap=linearOpMode.hardwareMap;
        s=hardwareMap.get(CRServo.class,"Intake");
        p=new Perekid(linearOpMode);
        fish=hardwareMap.get(DigitalChannel.class,"FlyingFish");
        fish.getState();
        gamepad2=linearOpMode.gamepad1;
    }
    //0-off
    //-1-in
    //1-out
    public void Autonom(int i){
        if(i==-1) s.setPower(1);
        else if (i==1) s.setPower(-1);
        else s.setPower(0);

    }
    public void Input(){
        s.setPower(-1);
    }
    public void Output(){
        s.setPower(1);
    }
    public void Off(){
        s.setPower(0);
    }


}
