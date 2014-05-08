
public class TrueFalse extends TriviaItem
{
	private String ChoiceA;
	private String ChoiceB;
	
	public TrueFalse()
	{
		super();
		this.ChoiceA = "True";
		this.ChoiceB = "False";
	}
	
	
	public TrueFalse(String type, String category, String trivia, String answer) 
	{
		super(type, category, trivia, answer);
		this.ChoiceA = "True";
		this.ChoiceB = "False";		
	}
	
	public String getChoiceA() 
	{
		return ChoiceA;
	}

	
	public String getChoiceB() 
	{
		return ChoiceB;
	}

	
	public void setChoiceA(String choiceA) 
	{
		ChoiceA = choiceA;
	}
	
	
	public void setChoiceB(String choiceB) 
	{
		ChoiceB = choiceB;
	}

	
	public String askQuestion()
	{
		return "The Category is: " + getCategory() +"\n" +
				getTrivia() + "\n" + 
				"A) " + getChoiceA() + "\n" + 
				"B) " + getChoiceB() + "\n" + 
				"Please answer by entering one of the letters above:";
	}
	

}
