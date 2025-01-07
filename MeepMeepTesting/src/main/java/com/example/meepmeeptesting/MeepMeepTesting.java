package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;

import org.rowlandhall.meepmeep.MeepMeep;
import org.rowlandhall.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import org.rowlandhall.meepmeep.roadrunner.DefaultBotBuilder;
import org.rowlandhall.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        // Declare a MeepMeep instance
        // With a field size of 800 pixels
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Required: Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                // Option: Set theme. Default = ColorSchemeRedDark()
                .setColorScheme(new ColorSchemeRedDark())
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(12, 60, Math.toRadians(0)))
                                .lineToLinearHeading(new Pose2d(8+24*2,55,Math.toRadians(45)))
                                .addDisplacementMarker(()->{
                                })
                                .waitSeconds(1)
                                .lineToLinearHeading(new Pose2d(8+24*2,40,Math.toRadians(-90)))
                                .addDisplacementMarker(()->{
                                })
                                .waitSeconds(1)
                                .lineToLinearHeading(new Pose2d(8+24*2,55,Math.toRadians(45)))
                                .addDisplacementMarker(()->{
                                })
                                .waitSeconds(1)
                                .lineToLinearHeading(new Pose2d(8+24*2,40,Math.toRadians(-90-45)))
                                .addDisplacementMarker(()->{
                                })
                                .waitSeconds(1)
                                .lineToLinearHeading(new Pose2d(8+24*2,55,Math.toRadians(45)))
                                .addDisplacementMarker(()->{
                                })
                                .waitSeconds(1)   
                                .lineToLinearHeading(new Pose2d(8+24*2,40,Math.toRadians(-90+45)))
                                .waitSeconds(1)
                                .lineToLinearHeading(new Pose2d(8+24*2,55,Math.toRadians(45)))
                                .addDisplacementMarker(()->{
                                })
                                .waitSeconds(1)
                                .lineToLinearHeading(new Pose2d(24,12,Math.toRadians(0)))
                                .build()
                );

        // Set field image
        meepMeep.setBackground(MeepMeep.Background.FIELD_INTOTHEDEEP_JUICE_DARK)
                .setDarkMode(true)
                // Background opacity from 0-1
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}