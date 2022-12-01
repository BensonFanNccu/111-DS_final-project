import java.io.IOException;

public class Controller {

	private String input;
	private CallGoogle cg = new CallGoogle();
	
	public void execute() throws IOException {
//		input = userInput.getText();
		cg.callAlgorithm(input);
	}
}
