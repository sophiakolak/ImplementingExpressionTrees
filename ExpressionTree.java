public class ExpressionTree {
    
    //instance variable for the root 
    public ExpressionNodes root;
       
    public ExpressionTree(String expression) 
    {    
        //creating a stack of expression trees 
        //tokenizing the given string.   
        MyStack<ExpressionNodes> thicStack = new MyStack<ExpressionNodes>();
        String[] token = expression.split(" ");
      
        //algorithm to create expression tree 
        for (int i = 0; i < token.length; i++)
        {
            String thisToken = token[i];
            if (isInteger(token[i]))
            {
                ExpressionNodes operand = 
                new ExpressionNodes(thisToken, null, null);
                thicStack.push(operand);
            }
            else
            {
                if(!thicStack.isEmpty())
                {
                    ExpressionNodes rightOp = thicStack.pop(); 
                    if(!thicStack.isEmpty())
                    {
                        ExpressionNodes leftOp = thicStack.pop(); 
                        ExpressionNodes operator = 
                        new ExpressionNodes(thisToken, leftOp, rightOp);
                        thicStack.push(operator);
                        root = operator;
                    }
                    //if given not enough operators
                    else{
                        System.out.println("invalid postfix");
                        System.exit(0);
                    }
                }
                //if given not enough operators
                else{
                    System.out.println("invalid posfix");
                    System.exit(0);
                }
            }
              
       
      
        }
        //if given too many operators 
        if (thicStack.size() != 1)
        {
            System.out.println("invalid postfix");
            System.exit(0);
        }
            
    }
    
    public class ExpressionNodes{
        
        public String token; 
        public ExpressionNodes lChild;
        public ExpressionNodes rChild;
        
        public ExpressionNodes(String token, 
        ExpressionNodes lChild, ExpressionNodes rChild)
        {
            this.token = token;
            this.lChild = lChild;
            this.rChild = rChild;
        }
    }
         
    public int eval()
    {
             
      return eval(root);
          
    }
    
    public String postfix()
    {
              
     return postfix(root);

    }
    
    
    public String prefix()
    {
              
     return prefix(root);
            
    }

    public String infix() 
    {
            
      return infix(root);
            
    }
    
    private int eval(ExpressionNodes t)
    {
      System.out.println(t.token);
      switch (t.token){
          case "+":
            return eval(t.lChild) + eval(t.rChild);
          case "-":
            return eval(t.lChild) - eval(t.rChild);
          case "*":
            return eval(t.lChild) * eval(t.rChild);
          case "/":
            return eval(t.lChild) / eval(t.rChild);
          default: 
            return Integer.parseInt(t.token);
      }
            
    }
    
    //to get the postfix expression, we must 
    //perform a post order traversal. 
    //left, right, mid
    private String postfix(ExpressionNodes t)
    {
      
        StringBuilder postfixy = new StringBuilder(300);
        String current = t.token;
        if (current.equals("+")){
            postfixy.append(postfix(t.lChild) + postfix(t.rChild) + "+");
            return postfixy.toString();
        }
        else if (current.equals("-")){
            postfixy.append(postfix(t.lChild) + postfix(t.rChild) + "-");
            return postfixy.toString();          
        }
        else if (current.equals("/")){
            postfixy.append(postfix(t.lChild) + postfix(t.rChild) + "/");
            return postfixy.toString();    
        }
        else if (current.equals("*")){
            postfixy.append(postfix(t.lChild) + postfix(t.rChild) + "*");
            return postfixy.toString();    
        }
        else{
            return current;
        }
            
    }
    
    //to get the prefix expression we must 
    //perform a pre order traversal. 
    //mid, left, right
    private String prefix(ExpressionNodes t)
    {
        StringBuilder prefixy = new StringBuilder(300);
        String current = t.token;
        if (current.equals("+"))
        {
          prefixy.append("+" + prefix(t.lChild) + prefix(t.rChild));
          return prefixy.toString();
        }
        else if (current.equals("-"))
        {
          prefixy.append("-" + prefix(t.lChild) + prefix(t.rChild));
          return prefixy.toString();
        }
        else if (current.equals("/"))
        {
          prefixy.append("/" + prefix(t.lChild) + prefix(t.rChild));
          return prefixy.toString(); 
        }
        else if (current.equals("*"))
        {
          prefixy.append("*" + prefix(t.lChild) + prefix(t.rChild));
          return prefixy.toString();   
        }
        else{
          return current;    
        }
            
    }
    
    //to get the infix expression we must 
    //perform a in order traversal. 
    //left, mid, right
    private String infix(ExpressionNodes t)
    {
        StringBuilder infixy = new StringBuilder(300);
        String current = t.token;
        if (current.equals("+"))
        {
          infixy.append("(" + infix(t.lChild) +")"+ 
                  "+" +"(" + infix(t.rChild) + ")");
          return infixy.toString();    
        }
        else if (current.equals("-"))
        {
          infixy.append("(" + infix(t.lChild) +")"+ 
                  "-" +"(" + infix(t.rChild) + ")");
          return infixy.toString();
        }
        else if (current.equals("/"))
        {
          return "(" + infix(t.lChild) +")"+ "/" +
                       "(" + infix(t.rChild) + ")";  
        }
        else if (current.equals("*"))
        {
          return "(" + infix(t.lChild) +")"+ "*" +
                       "(" + infix(t.rChild) + ")";   
        }
        else
        {
            return current;
        }
    }
    
    public boolean isInteger(String input)
    {
        try
        {
            Integer.parseInt(input);
            return true;
        }
      
        catch (Exception e)
        {
            return false;
        }
    }

    
}
       
     