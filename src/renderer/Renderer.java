package renderer;

import core.Base;
import graphics.Text;
import graphics.Texture;
import graphics.controls.Button;
import graphics.controls.CheckBox;
import graphics.controls.TextField;

public class Renderer {
	
	/** Default offset is 2. If some graphics are not rendering try to increase this offset value. NOTE: The more of offset is lower fps! */
	public static int createOffset = 2;
	
	/** Number of created textures */
	public static int textures = createOffset;
	/** Number of created texts */
	public static int texts = createOffset;
	/** Number of created buttons */
	public static int buttons = createOffset;
	/** Number of created check boxes */
	public static int checkBoxes = createOffset;
	/** Number of created text fields */
	public static int textFields = createOffset;

	/** Render your texture on screen */
	public static void renderTexture(Texture... textures) {
		for(Texture texture : textures)
			if(texture.isRender()) Base.textures.add(texture);
	}
	
	/** Render your text on screen */
	public static void renderText(Text... texts) {
		for(Text text : texts)
			if(text.isRender()) Base.texts.add(text);
	}
	
	public static void renderButton(Button... buttons) {
		for(Button button : buttons)
			if(button.isRender()) Base.buttons.add(button);
	}
	
	public static void renderCheckBox(CheckBox... checkBoxes) {
		for(CheckBox checkBox : checkBoxes)
			if(checkBox.isRender()) Base.checkBoxes.add(checkBox);
	}
	
	public static void renderTextField(TextField... textFields) {
		for(TextField textField : textFields)
			if(textField.isRender()) Base.textFields.add(textField);
	}
}
