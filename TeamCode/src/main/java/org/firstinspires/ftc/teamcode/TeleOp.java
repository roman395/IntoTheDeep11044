package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class TeleOp extends LinearOpMode {
    enum State {
        Up,
        Down
    }

    Gamepad gamepad;
    Mecanum mecanum;
    Intake intake;
    Lift lift;
    Perekid perekid;
    ElapsedTime time;
    State state;

    @Override
    public void runOpMode() throws InterruptedException {
        state = State.Down;
        mecanum = new Mecanum(this);
        intake = new Intake(this);
        lift = new Lift(this);
        time = new ElapsedTime();
        perekid = new Perekid(this);
        gamepad = gamepad1;
        waitForStart();
        while (opModeIsActive()) {
            Lift.currentRot=lift.motorL.getCurrentPosition();
            mecanum.TeleOp();
            intake.Control();
            perekid.Control();
            if(gamepad.circle && perekid.s1.getPosition()!=0.88){
                if(perekid.s1.getPosition()>0.88){
                    perekid.s1.setPosition(perekid.s1.getPosition()-0.01);
                    perekid.s2.setPosition(perekid.s2.getPosition()-0.01);
                }
                else {
                    perekid.s1.setPosition(perekid.s1.getPosition()+0.01);
                    perekid.s2.setPosition(perekid.s2.getPosition()+0.01);
                }

                //s1.setPosition(0.88);
                //s2.setPosition(0.88);
            }
            lift.Control();
            telemetry.addData("PositionL", lift.motorL.getCurrentPosition());
            telemetry.addData("PositionR", lift.motorR.getCurrentPosition());
            telemetry.addData("Mode", mecanum.operation);
            telemetry.addData("state", state);
            telemetry.addData("motor", lift.motorL.getTargetPosition());

            telemetry.update();
        }




    }
}



