package frclib.vision;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.AxisCamera;
import edu.wpi.first.wpilibj.vision.USBCamera;

public class Camera {

	USBCamera cam;
	AxisCamera axiscam;

	public Camera(USBCamera cam) {
		this.cam = cam;
	}

	public Camera(AxisCamera cam) {
		this.axiscam = cam;
	}

	public void sendLiveFeed() {
		if (cam != null || axiscam != null) {
			Image image = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
			getImage(image);
			CameraServer.getInstance().setImage(image);
		} else {
			return;
		}
	}

	public void setFPS(int fps) {
		if (cam != null) {
			cam.setFPS(fps);
		} else if (axiscam != null) {
			axiscam.writeMaxFPS(fps);
		} else {
			return;
		}
	}

	public void getImage(Image image) {
		if (cam != null) {
			cam.getImage(image);
		} else if (axiscam != null) {
			axiscam.getImage(image);
		} else {
			return;
		}
	}

}
