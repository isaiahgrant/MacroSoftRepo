
public abstract class TriviaItem 
{
	public static final String TYPE_MULTIPLE_CHOICE = "mc";
	public static final String TYPE_TRUE_FALSE = "tf";
	
	private String type;
	private String category;
	private String trivia;
	private String answer;
	
	public TriviaItem()
	{
		this.type = "default type";
		this.category = "default category";
		this.trivia = "default trivia";
		this.answer = "default answer";
	}
	
	public TriviaItem(String type, String category, String trivia, String answer)
	{
		this.type = type;
		this.category = category;
		this.trivia = trivia;
		this.answer = answer;
	}
	

	public String getType() 
	{
		return type;
	}
	
	
	public String getCategory() 
	{
		return category;
	}
	
	
	public String getTrivia() 
	{
		return trivia;
	}
	
	
	public String getAnswer() 
	{
		return answer;
	}

	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	
	public void setCategory(String category) 
	{
		this.category = category;
	}
	

	public void setTrivia(String trivia) 
	{
		this.trivia = trivia;
	}

	

	public void setAnswer(String answer) 
	{
		this.answer = answer;
	}
	
	
	public abstract String askQuestion();
}
