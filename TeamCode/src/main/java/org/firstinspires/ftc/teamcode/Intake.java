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

    DigitalChannel fish;
    LED l;
    LinearOpMode linearOpMode;
    HardwareMap hardwareMap;
    Gamepad gamepad2;
    public Intake(LinearOpMode linearOpMode) {
        this.linearOpMode=linearOpMode;
        hardwareMap=linearOpMode.hardwareMap;
        s=hardwareMap.get(CRServo.class,"Intake");

        //fish=hardwareMap.get(DigitalChannel.class,"FlyingFish");
        //l=hardwareMap.get(LED.class,"lamp");

        gamepad2=linearOpMode.gamepad1;
    }
    //1 = input
    //-1 = output
    //0 = stop
    public void Autonom(int input){
        if(input==1) s.setPower(1);
        else if (input==-1) s.setPower(-1);
        else s.setPower(0);
    }
    public void Control(){
        if(gamepad2.left_bumper){
              s.setPower(1);
        }
        else if (gamepad2.right_bumper) {
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
