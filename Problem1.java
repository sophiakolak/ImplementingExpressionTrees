public class Problem1{

  public static void main(String[] args) {
      ExpressionTree exp = new ExpressionTree("34 2 - 5 *");
      System.out.println("The value of the postifix expression is: "+exp.eval());
      System.out.println("The prefix of the postifix expression is: "+exp.prefix());
      System.out.println("The infix of the postifix expression is: "+exp.infix());
      System.out.println("The postfix of the postifix expression is: "+exp.postfix());

}

}