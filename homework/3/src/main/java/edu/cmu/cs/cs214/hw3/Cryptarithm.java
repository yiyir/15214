package edu.cmu.cs.cs214.hw3;

//import edu.cmu.cs.cs214.hw2.expression.Expression;

public class Cryptarithm {
    //private Expression left;
    //private Expression right;

    public Cryptarithm(String[] args) {
        StringBuilder leftExpression = new StringBuilder();
        StringBuilder rightExpression = new StringBuilder();
        boolean isLeft = true;
        int index;
        for (int i = 0; i < args.length; i++) {
            if (i%2 == 0 && args[i].matches("[A-Z ]+")){
                System.out.println("pass");
            }
            else if(i%2 != 0 && (args[i].equals("+") || args[i].equals("-") ||args[i].equals("*")||args[i].equals("="))) {
                System.out.println("pass");
                
            }else {
                throw new IllegalArgumentException();
            }
            
           
        }
   }

//    public boolean isValid() {
//        return Double.compare(left.eval(), right.eval())==0;
//    }
    public static void main(String[] args) {
        Cryptarithm sb = new Cryptarithm(args);
        
    }
}
