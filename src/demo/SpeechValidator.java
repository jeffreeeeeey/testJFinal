package demo;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class SpeechValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void handleError(Controller c) {
		c.keepModel(Speech.class);
		
		String actionKey = getActionKey();
		if (actionKey.equals("/speech/save")) {
			c.render("addSpeech.html");
		}
	}
	
}
