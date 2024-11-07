// Copyright 2021-2024 FRC 6328
// http://github.com/Mechanical-Advantage
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// version 3 as published by the Free Software Foundation or
// available in the root directory of this project.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU General Public License for more details.

package frc.robot.subsystems.flywheel;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;

public class FlywheelIOTalonSRX implements FlywheelIO {
  private final TalonSRX flywheelMotor = new TalonSRX(6);

  public FlywheelIOTalonSRX() {
    var config = new TalonSRXConfiguration();
    config.peakCurrentLimit = 80;
    config.peakCurrentDuration = 250;
    config.continuousCurrentLimit = 60;
    config.voltageCompSaturation = 12.0;
    flywheelMotor.configAllSettings(config);
  }

  @Override
  public void updateInputs(FlywheelIOInputs inputs) {
    inputs.appliedVolts = flywheelMotor.getMotorOutputVoltage();
    inputs.currentAmps = new double[] {flywheelMotor.getSupplyCurrent()};
  }

  @Override
  public void setVoltage(double volts) {
    flywheelMotor.set(TalonSRXControlMode.PercentOutput, volts * 12.0);
  }
}
