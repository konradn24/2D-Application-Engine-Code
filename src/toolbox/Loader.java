package toolbox;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Loader {

	/** Say where is folder with all resources and what file format will be use */
	public static String resources = "res/", format = ".png";
	/** If you load texture model and it fail, then this model is using */
	public static BufferedImage standardModel = null;
	
	/** Load texture model from file */
	public static BufferedImage loadModel(String file) {
		BufferedImage model = standardModel;
		
		try {
			model = ImageIO.read(new File(resources + file + format));
		} catch(IOException e) {
			System.err.println("Can't load " + file + " file!");
		}
		
		return model;
	}
}
