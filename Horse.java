
/**
 * The [Horse] class is used to provide all the various different methods
 * and instance variables related to an individual race horse.  
 * 
 * @author Oliver Munn
 * @version 1.0.0
 */
public class Horse
{
    /////////////////////////////////////////
    // Instance variables for horse class. //
    /////////////////////////////////////////

    /// Used to determine how the horse is represented on the screen. 
    private char horseSymbol;

    /// The name of the horse that will be announced upon victory.  
    private String horseName;

    /// Determines the speed of the horse and likelihood of falling.  (from 0-1)
    /// 
    /// - A higher confidence means the horse runs faster but is also more prone to falling.  
    /// - A lower confidence means the horse runs slower but is more stable.  

    private double horseConfidence;

    /////////////////////////////////////
    // Horse class method definitions. //
    /////////////////////////////////////
    
      
    // Constructor of class Horse
    /**
     * Creates a new instance of [Horse] taking three arguments: a [horseSymbol], [horseName], and [horseConfidence]
     * 
     * @param horseSymbol Will be used to determine how the horse is represented on the screen.  
     * @param horseName Is used as the name of the horse.   
     * @param horseConfidence Determines the speed of the horse and likelihood of falling.  (from 0-1)   
     */
    public Horse(char horseSymbol, String horseName, double horseConfidence)
    {
        this.horseSymbol = horseSymbol;
        this.horseConfidence = horseConfidence;
        this.horseName = horseName;
       
    }
    
    
    
    //Other methods of class Horse
    public void fall()
    {
        
    }
    
    public double getConfidence()
    {
        return this.horseConfidence;
    }
    
    public int getDistanceTravelled()
    {
        
    }
    
    public String getName()
    {
        return this.horseName;
    }
    
    public char getSymbol()
    {
        return this.horseSymbol;
    }
    
    public void goBackToStart()
    {
        
    }
    
    public boolean hasFallen()
    {
        
    }

    public void moveForward()
    {
        
    }

    public void setConfidence(double newConfidence)
    {
        
    }
    
    public void setSymbol(char newSymbol)
    {
        
    }
    
}
