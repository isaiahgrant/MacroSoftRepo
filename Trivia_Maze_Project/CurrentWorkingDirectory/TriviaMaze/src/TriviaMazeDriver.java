


public class TriviaMazeDriver 
{

	public static void main(String[] args) 
	{
		int i;
		TriviaItem temp = null;
		TriviaFactoryDB factory = new TriviaFactoryDB();
		

		for(i = 0; i < 101; i++)
		{
			temp = factory.getTriviaItem();
			System.out.println("Question " + i);
			System.out.println(temp.askQuestion());
			System.out.println();
		}
		
		factory.closeFactory();
		
		
	}

}
