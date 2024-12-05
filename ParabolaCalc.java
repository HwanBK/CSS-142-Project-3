
/**
 * Supplier code for calculation functions.
 *
 * @author Hwansu Kim (Billy)
 * @version 01/21/2022
 */
public class ParabolaCalc{
    
    /**
     *  Solves for f(x) based on variable x-input.
     * 
     *  @param  xPosition   variable x, in floating-point type.
     *  @return             f(x) or y, in floating-point type.               
     */
    public static double parabolaFunction(double xPosition) {
        double solutionY = -Math.pow(xPosition, 2) + 25;
        if (solutionY == -0.0) {
            solutionY = 0.0;
        }
        return solutionY;
    }
    
    /**
     * Solves for f'(x) based on variable x-input.
     * 
     * @param   xPosition   variable x, floating-point type.
     * @return              f'(x) or tangent line at x, in floating-point type.
     */
    public static double derivativeFunction(double xPosition) {
        double solutionPrime = -2 * xPosition;
        if (solutionPrime == -0.0) {
            solutionPrime = 0.0;
        }
        return solutionPrime;
    }
    
    /**
     * Solves for the area under the parabola based on the x-domain and number of rectangles.
     * 
     * @param   numOfRec    the number of rectangles used, integer type; must not be zero or negative.
     * @return              the area under the parabola, floating-point type.
     */
    public static double leftRiemannSum(int numOfRec) {
        double startX = 0.0;
        double endX = 5.0;
        double sum = 0.0;
        
        if (numOfRec <= 0) {
            throw new IllegalArgumentException ("Number of rectangles must not be 0 or negative.");
        }
        for (double x = startX; x < endX; x = x + (endX - startX) / numOfRec) {
            sum += parabolaFunction(x);
        }
        return sum * ((endX - startX) / numOfRec);
    }
    
    /**
     * Solves for the percentage of over estimation from leftRiemannSum method.
     * 
     * @param   numOfRec    the number of rectangles used, integer type; must not be zero or negative.
     * @return              the percentage of over-estimation of the area, floating-point type.
     */
    public static double overEsti(int numOfRec) {
        if (numOfRec <= 0) {
            throw new IllegalArgumentException ("Number of rectangles must not be 0 or negative.");
        }
        double area = leftRiemannSum(numOfRec);
        double actualArea = 125.0 - (125.0 / 3.0);
        
        return ((area / actualArea) - 1) * 100;
    }
}
