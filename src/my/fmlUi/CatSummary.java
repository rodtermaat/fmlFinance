/*
 * This class is used to hold the Category Summary data from SQlite
 * Just used to have a method to load and iterate thru the data for a 
 * report / graph
 */
package my.fmlUi;

/**
 *
 * @author termaat
 */
public class CatSummary {
    private final String category;
    private final int amount;
    
    public CatSummary(String category, int amount){
        this.category = category;
        this.amount = amount;
    }
    
}
