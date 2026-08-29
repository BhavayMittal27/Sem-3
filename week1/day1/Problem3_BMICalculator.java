package week1.day1;

import java.util.Random;

public class Problem3_BMICalculator {
    public static void main(String[] args) {
        double[] heights = new double[10];
        double[] weights = new double[10];
        
        // Exact samples from the PDF
        heights[0] = 1.75; weights[0] = 70.0;
        heights[1] = 1.60; weights[1] = 90.0;
        
        Random rand = new Random(10);
        for (int i = 2; i < 10; i++) {
            heights[i] = Math.round((1.5 + rand.nextDouble() * 0.5) * 100.0) / 100.0;
            weights[i] = Math.round((45 + rand.nextDouble() * 55) * 10.0) / 10.0;
        }
        
        printWellnessReport(heights, weights);
    }
    
    public static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi <= 24.9) {
            return "Normal";
        } else if (bmi >= 25.0 && bmi <= 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }
    
    public static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("Person | Height (m) | Weight (kg) | BMI | Status");
        System.out.println("-------------------------------------------------");
        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            // Round to 2 decimal places
            bmi = Math.round(bmi * 100.0) / 100.0;
            String status = getBmiStatus(bmi);
            System.out.printf("Person %d | %.2f m | %.1f kg | BMI: %.2f | Status: %s\n", 
                              (i + 1), heights[i], weights[i], bmi, status);
        }
    }
}
