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
    LED l;
    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad2;
    public Intake(LinearOpMode linearOpMode) {
        this.linearOpMode=linearOpMode;
        hardwareMap=linearOpMode.hardwareMap;
        s=hardwareMap.get(CRServo.class,"Intake");
        p=new Perekid(linearOpMode);
        //fish=hardwareMap.get(DigitalChannel.class,"FlyingFish");
        //l=hardwareMap.get(LED.class,"lamp");

        gamepad2=linearOpMode.gamepad2;
    }
    //0-off
    //1-in
    //-1-out
    public void Autonom(int i){
        if(i==1) s.setPower(1);
        else if (i==-1) s.setPower(-1);
        else s.setPower(0);

    }
    public void Control(){
        if(gamepad2.right_trigger>=0.5){
              s.setPower(1);
        }
        else if (gamepad2.left_trigger>=0.5) {
              s.setPower(-1);
          }

        else {

            s.setPower(0);
        }

        /*if(!fish.getState()){
            l.enable(true);

        }
        else {
            l.enable(false);
        }

         */


    }

}
