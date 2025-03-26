
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
    
    // Constructor-initialised fields

    /// Used to determine how the horse is represented on the screen. 
    private char horseSymbol;

    /// The name of the horse that will be announced upon victory.  
    private String horseName;

    /// Determines the speed of the horse and likelihood of falling.  (from 0-1)
    /// 
    /// - A higher confidence means the horse runs faster but is also more prone to falling.  
    /// - A lower confidence means the horse runs slower but is more stable.  

    private double horseConfidence;

    // Implicitly initialised fields

    /// A field that determines weather or not the horse has fallen.  
    private boolean hasFallen = false;

    /// A field that is used to store the horse's progress during the race.  
    private int distanceTravelled = 0;


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
        // Set the provided arguments to the corresponding instance variables
        this.horseSymbol = horseSymbol;
        this.horseConfidence = horseConfidence;
        this.horseName = horseName;
       
    }
    
    
    
    //Generic methods of class Horse

    /// Sets the [hasFallen] flag to true.  
    public void fall()
    {
        this.hasFallen = true;
    }
    
    /**
     * Gets the [horseConfidence] field. 
     *  
     * A higher confidence means the horse runs faster but is also more prone to falling.  
     * A lower confidence means the horse runs slower but is more stable.  
     * 
     * @return The horse confidence.  
     */
    public double getConfidence()
    {
        return this.horseConfidence;
    }
    
    /**
     * Gets the total distance the horse has travelled throughout the race.  
     * @return The distance travelled.
     */
    public int getDistanceTravelled()
    {
        return this.distanceTravelled;
    }
    
    /**
     * Gets the horse's name.  
     * @return The horse's name.  
     */
    public String getName()
    {
        return this.horseName;
    }
    
    /**
     * Gets the symbol that is used to represent the horse during the race.  
     * @return The horse's racing symbol.  
     */
    public char getSymbol()
    {
        return this.horseSymbol;
    }
    
    /**
     * Resets the horse's position in the race,
     * sending them back to the start by setting the [distanceTravelled] to 0
     * This will also reset their [hasFallen] flag.  
     */
    public void goBackToStart()
    {
        this.distanceTravelled = 0;
        this.hasFallen = false;
    }
    
    /**
     * Gets the flag that determines if the horse has fallen or not.  
     * @return A boolean [hasFallen] indicator.  
     */
    public boolean hasFallen()
    {
        return this.hasFallen;
    }

    /**
     * Moves the horse forwards 1 by 
     * incrementing [distanceTravelled] by 1.   
     */
    public void moveForward()
    {
        this.distanceTravelled += 1;
    }

    /**
     * Setter to set a new horse confidence.  
     * @param newConfidence 
     * Must be a double between 0 and 1.  
     * A higher confidence means the horse runs faster but is also more prone to falling.  
     * A lower confidence means the horse runs slower but is more stable.  
     */
    public void setConfidence(double newConfidence)
    {
        this.horseConfidence = newConfidence;
    }
    
    /**
     * Setter to set a new horse symbol.  
     * This is used to change the symbol that is used to represent the horse during the race.  
     * 
     * @param newSymbol A character parameter that will replace the existing horse symbol on the screen.  
     */
    public void setSymbol(char newSymbol)
    {
        this.horseSymbol = newSymbol;
    }
    
}
