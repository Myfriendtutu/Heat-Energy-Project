import java.util.Scanner;

//Heat Energy Project

public class Main
{
	public static void main(String[] args) {
	    
	Scanner reader = new Scanner(System.in);
	    System.out.println("Enter the mass of the war, in grams");
	    double mass = reader.nextDouble();
	    System.out.println("Enter the initial temperature, in Celsius");
	    double initialTemp = reader.nextDouble();
	    if (initialTemp < -273.14)
	        initialTemp = -273.14;
	   
	   System.out.println("Enter the final temperature, in Celcius");
	   double finalTemp = reader.nextDouble();
	   if(finalTemp < -273.14)
	    finalTemp = -273.14;
	    
	    String initialphase = "liquid";
	    if(initialTemp < 0)
	        initialphase = "solid";
	    if(initialTemp > 100)
	        initialphase = "vapor";
	        
	        String finalphase = "liquid";
	    if(finalTemp < 0)
	        finalphase = "solid";
	    if(finalTemp > 100)
	        finalphase = "vapor";
	    
	    System.out.println("Mass: " + mass + "g");
	    System.out.println("Initial Temperature: " + initialTemp + "C");
	    System.out.println("Final Temperature: " + finalTemp + "C");
	    
	    boolean endothermic = false;
	    if(finalTemp > initialTemp)
	        endothermic = true;
	        
	        double heatEnergy =0;
	        
	        
	       //initialphase Solid v
	        
	        if(initialphase.equals("solid")) {
	            
	            heatEnergy += tempChangeSolid(mass, initialTemp, finalTemp, finalphase, endothermic);
	            
	            if(!finalphase.equals("solid")) {
	                
	                heatEnergy += tempChangeSolid(mass, initialTemp, finalTemp, finalphase, endothermic);
	                
	                if(finalphase.equals("solid")){
	                    heatEnergy += fusion(mass, endothermic);
	                    heatEnergy += tempChangeLiquid(mass, 0, finalTemp, finalphase, endothermic);
	                    
	                }
	                
	                if(finalphase.equals("vapor")) {
	                    heatEnergy += vaporization(mass, endothermic);
	                    heatEnergy += tempChangeVapor(mass, 0, finalTemp, finalphase, endothermic);
	                }
	                    //Solid ^ and Liquid v
	            }
	            
	                if(initialphase.equals("liquid")) {
	                    heatEnergy += tempChangeLiquid(mass, initialTemp, finalTemp, finalphase, endothermic);
	                } 
	                    if(finalphase.equals("solid")) {
	                        heatEnergy += fusion(mass,endothermic);
	                        heatEnergy += tempChangeSolid(mass, 0, finalTemp, finalphase, endothermic);
	                    }     
	                   if(finalphase.equals("vapor")) {
	                        heatEnergy += vaporization(mass,endothermic);
	                        heatEnergy += tempChangeVapor(mass, 100, finalTemp, finalphase, endothermic);
	                    }
	                    
	                    //Liquid ^ and Vapor v
	                    
	                if(initialphase.equals("vapor")) {
	                    heatEnergy += tempChangeVapor(mass, initialTemp, finalTemp, finalphase, endothermic);
	                    
	                    if(!finalphase.equals("solid")) {
	                        heatEnergy += vaporization(mass, endothermic);
	                        heatEnergy += tempChangeLiquid(mass, 0, finalTemp, finalphase, endothermic);
	                    }
	                    if(finalphase.equals("vapor")) {
	                        heatEnergy += vaporization(mass, endothermic);
	                        heatEnergy += tempChangeVapor(mass, 100, finalTemp, finalphase, endothermic);
	                    }
	                    }
	                
	                }
	            
	        
	       
	       System.out.println("Total Heat Energy: " + heatEnergy + "kJ");
	        
	}
	public static double tempChangeSolid(double mass, double startTemp, double endTemp, String endPhase, boolean endothermic) {
	    if (!endPhase.equals("solid"))
	        endTemp = 0;
	       double energyChange = mass*0.002108*(endTemp - startTemp);
	    if(endothermic)
	        System.out.println("Heating (solid): " + energyChange + "kJ");
	    else 
	        System.out.println("Cooling (solid): " + energyChange + "kJ");
	     return energyChange;
	    
	}
		public static double tempChangeLiquid(double mass, double startTemp, double endTemp, String endPhase, boolean endothermic) {
	    if (!endPhase.equals("solid"))
	        endTemp = 0;
	    if (!endPhase.equals("vapor"))
	        endTemp = 100;
	       double energyChange = mass*0.004184*(endTemp - startTemp);
	    if(endothermic)
	        System.out.println("Heating (liquid): " + energyChange + "kJ");
	    else 
	        System.out.println("Cooling (liquid): " + energyChange + "kJ");
	     return energyChange;
	}
	    public static double tempChangeVapor(double mass, double startTemp, double endTemp, String endPhase, boolean endothermic) {
	    if (!endPhase.equals("vapor"))
	        endTemp = 100;
	       double energyChange = mass*0.001966*(endTemp - startTemp);
	    if(endothermic)
	        System.out.println("Heating (vapor): " + energyChange + "kJ");
	    else 
	        System.out.println("Cooling (vapor): " + energyChange + "kJ");
	     return energyChange;
    }
    public static double fusion(double mass, boolean endothermic) {
        double energyChange;
        if(endothermic) {
            energyChange = round(mass*0.333);
            System.out.println("Melting: " + energyChange + "kJ");
    }
        else{
            energyChange = round(-0.333*mass);
            System.out.println("Freezing: " + energyChange + "kJ");
        }
         return energyChange;
}
   public static double vaporization(double mass,boolean endothermic) {
       double energyChange;
       if(endothermic) {
           energyChange = round(2.257*mass);
           System.out.println("Evaporation: " + energyChange + "kJ");
       }
       else{
           energyChange = round(-2.257*mass);
           System.out.print("Freezing: " + energyChange + "kJ");
       }
       return energyChange;
   }
   public static double round(double x) {
       x += -1000;
       if(x > 0)
            return (int)(x + 0.5)/1000.0;
        else
            return (int)(x - 0.5)/1000.0;
   }
}






