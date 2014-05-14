//Created by: Andrey Melnikov
//5.7.2014

//This class contains two integers to represent the position inside
//A two dimensional array.

//This class contains a static method - areValidCoordinates
//To make sure coordinates are valid before being created.
//Use areValidCoordinates before creating an instance of this class
public class Coordinates 
{
	private int row;
	private int column;
	
	
	//Pre: row >= 0, column >= 0
	public Coordinates(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	
	//Note: Will throw IllegalArgumentException if row becomes < 0
	public void increaseRow(int amount)
	{
		int newRowAmount = this.row + amount;
		
		this.setRow(newRowAmount);
	}
	
	
	public void increaseRowByOne()
	{
		this.setRow(this.row + 1);
	}
	
	
	//Note: Will throw IllegalArgumentException if row becomes < 0
	public void decreaseRowByOne()
	{
		this.setRow(this.row - 1);
	}
	
	
	//Note: Will throw IllegalArgumentException if column becomes < 0
	public void increaseColumn(int amount)
	{
		int newColumnAmount = this.column + amount;
		
		this.setColumn(newColumnAmount);
	}
	
	
	public void increaseColumnByOne()
	{
		this.setColumn(this.column + 1);
	}
	
	
	//Note: Will throw IllegalArgumentException if column becomes < 0
	public void decreaseColumnByOne()
	{
		this.setColumn(this.column - 1);
	}
	
	
	//Throws an IllegalArgumentException if input is invalid (< 0)
	public void setRow(int row)
	{
		if(row < 0)
		{
			throw new IllegalArgumentException("Row can not be negative. Row:" + row);
		}
		
		this.row = row;
	}
	
	
	//Throws an IllegalArgumentException if input is invalid (< 0)
	public void setColumn(int column)
	{
		if(column < 0)
		{
			throw new IllegalArgumentException("Column can not be negative. Column:" + column);
		}
		
		this.column = column;
	}
	
	
	public void setCoordinates(int row, int column)
	{
		this.setRow(row);
		this.setColumn(column);
	}
	
	
	public int getRow()
	{
		return this.row;
	}
	
	
	public int getColumn()
	{
		return this.column;
	}
	
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj.getClass().getSimpleName().equals(this.getClass().getSimpleName()))
		{
			Coordinates that = (Coordinates)obj;
			
			return (this.row == that.row) && (this.column == that.column);
		}
		
		return false;
	}
	
	
	@Override
	public String toString()
	{
		return "(" + this.getRow() + "," + this.getColumn() + ")";
	}
	
	
	public static boolean areValidCoordinates(int row, int column)
	{
		return row >= 0 && column >= 0;
	}
}
