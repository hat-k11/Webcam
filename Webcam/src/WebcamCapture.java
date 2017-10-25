import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class WebcamCapture {
	public static void main(String[] args){
		/* make file name */
		String fileName = makeFileName();
		
		Webcam webcam = null;
		webcam = Webcam.getDefault();
		webcam.setViewSize(new Dimension(640,480));
		
		if(webcam != null){
			System.out.println("Webcam:"+webcam.getName());
			webcam.open();
			BufferedImage bImage = webcam.getImage();
			try{
				ImageIO.write(bImage, "PNG", new File(fileName));
				System.out.println("Captured!");
			}catch (IOException e){
				e.printStackTrace();
			}
			webcam.close();
		}
 	}
	
	public static String makeFileName(){
		/* get date and time */
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = sdf.format(calendar.getTime()).toString();
		
		/* get user name */
		String name = System.getProperty("user.home");
		name = name.replaceAll("\\\\", "/");
		
		return (name + "/Pictures/capture-" + time + ".png");
	}
}
