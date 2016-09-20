package frclib.pid;

public class PID2 {
	private double kp = 0.0;
	private double ki = 0.0;
	private double kd = 0.0;
	private double setpoint = 0.0;
	private double input = 0.0;
	private double output = 0.0;
	private double error = 0.0;
	private double outMax = 0.0;
	private double outMin = 0.0;
	private double offset = 0.0;
	private double looptime;
	private double totalerror = 0;
	private double lasterror = 0;
	private double P;
	private double I;
	private double D;
	private long end;
	private long start;

	public PID2(double p, double i, double d, double setpoint) {
		this.kp = p;
		this.ki = i;
		this.kd = d;
		this.setpoint = setpoint;
	}

	public void setTunings(double p, double i, double d) {
		this.kp = p;
		this.ki = i;
		this.kd = d;
	}

	public void setSetpoint(double value) {
		this.setpoint = value;
	}

	public void setOutputLimits(double min, double max) {
		this.outMax = max;
		this.outMin = min;
	}

	public void setOnTargetOffset(double value) {
		this.offset = value;
	}

	public boolean onTarget() {
		return this.setpoint + this.offset > this.output && this.setpoint - this.offset < this.output;
	}

	public double compute(double in) {
		this.start = System.currentTimeMillis();
		this.input = in;
		this.error = this.setpoint - this.input;
		this.totalerror += error;
		this.looptime = this.end - this.start;
		this.P = this.kp * this.error;
		this.I = this.ki * totalerror * looptime;
		this.D = this.kd * this.error - this.lasterror / this.looptime;
		this.output = this.P + this.I + this.D;
		if (this.output > this.outMax) {
			this.output = this.outMax;
		} else if (this.output < this.outMin) {
			this.output = this.outMin;
		}
		this.end = System.currentTimeMillis();
		return this.output;
	}
}
