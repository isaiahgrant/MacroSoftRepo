
public class MultiChoice extends TriviaItem
{

	private String ChoiceA;
	private String ChoiceB;
	private String ChoiceC;
	private String ChoiceD;
	
	
	public MultiChoice()
	{
		super();
		this.ChoiceA = "Default";
		this.ChoiceB = "Default";
		this.ChoiceC = "Default";
		this.ChoiceD = "Default";
	}
	
	public MultiChoice(String type, String category, String trivia, String a, String b, String c, String d, String answer) 
	{
		super(type, category, trivia, answer);
		this.ChoiceA = a;
		this.ChoiceB = b;
		this.ChoiceC = c;
		this.ChoiceD = d;
	}


	public String getChoiceA() 
	{
		return ChoiceA;
	}


	public String getChoiceB() 
	{
		return ChoiceB;
	}


	public String getChoiceC() 
	{
		return ChoiceC;
	}


	public String getChoiceD() 
	{
		return ChoiceD;
	}
	
	
	public void setChoiceA(String choiceA) 
	{
		ChoiceA = choiceA;
	}
	
	
	public void setChoiceB(String choiceB) 
	{
		ChoiceB = choiceB;
	}
	
	
	public void setChoiceC(String choiceC) 
	{
		ChoiceC = choiceC;
	}


	public void setChoiceD(String choiceD) 
	{
		ChoiceD = choiceD;
	}


	@Override
	public String askQuestion() 
	{
		return "The Category is: " + getCategory() +"\n" +
				getTrivia() + "\n" + 
				"A) " + getChoiceA() + "\n" + 
				"B) " + getChoiceB() + "\n" + 
				"C) " + getChoiceC() + "\n" + 
				"D) " + getChoiceD() + "\n" + 
				"Please answer by entering one of the letters above:";
	}

}
