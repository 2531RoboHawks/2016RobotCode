package frclib.vision;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class Camera {

	USBCamera cam;
	AxisCamera axiscam;
	String name = "cam0";

	public Camera(USBCamera cam) {
		this.cam = cam;
	}

	public Camera(AxisCamera cam) {
		this.axiscam = cam;
	}

	public Camera(String cam) {
		this.name = cam;
	}

	public String getName() {
		return name;
	}

	public void getImage(Image image, int session) {
		NIVision.IMAQdxGrab(session, image, 1);
	}

}
