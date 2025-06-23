package pedroPathing.constants;

import com.pedropathing.localization.*;
import com.pedropathing.localization.constants.*;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class LConstants {
    static {
        TwoWheelConstants.forwardY = -2.2259;

        TwoWheelConstants.strafeX = -4.0855;
        TwoWheelConstants.forwardTicksToInches=0.00115;
        TwoWheelConstants.strafeTicksToInches=0.000585;
        TwoWheelConstants.forwardEncoder_HardwareMapName = "rightRear";
        TwoWheelConstants.strafeEncoder_HardwareMapName = "leftRear";
        TwoWheelConstants.forwardEncoderDirection = Encoder.FORWARD;
        TwoWheelConstants.strafeEncoderDirection = Encoder.FORWARD;
        TwoWheelConstants.IMU_HardwareMapName = "imu";
        TwoWheelConstants.IMU_Orientation = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.RIGHT, RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD);
    }
}




