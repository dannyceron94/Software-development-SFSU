package lab5_2019;

import com.sun.deploy.util.StringUtils;
import sun.font.TrueTypeFont;

import java.util.*;

import static java.util.Collections.lastIndexOfSubList;
import static java.util.Collections.reverse;

public class Main {

    public static void main(String[] args) {
        Map label = new HashMap<String,Integer>();
        int programCounter = 0;
        int lastPoint=0;
        boolean pit = false;
        String goTo ="0";
        int newPoint;
        List<String> program = new ArrayList<String>();
        Stack<String> stack = new Stack<String>(); // TODO string probably not best
        Stack<String> tempStack;

        program.add("GOTO start<<1>>");
        program.add("LABEL Read");
        program.add("LINE -1");
        program.add("FUNCTION Read -1 -1");
        program.add("READ");
        program.add("RETURN ");
        program.add("LABEL Write");
        program.add("LINE -1");
        program.add("FUNCTION Write -1 -1");
        program.add("FORMAL dummyFormal 0");
        program.add("LOAD 0 dummyFormal");
        program.add("WRITE");
        program.add("RETURN ");
        program.add("LABEL start<<1>>");
        program.add("LINE 1");
        program.add("FUNCTION main 1 4");
        program.add("LIT 0 i");
        program.add("LIT 0 j");
        program.add("LINE 2");
        program.add("LOAD 0 i");
        program.add("LOAD 1 j");
        program.add("BOP +");
        program.add("LIT 7");
        program.add("BOP +");
        program.add("STORE 0 i");
        program.add("LINE 3");
        program.add("LOAD 0 i");
        program.add("ARGS 1");
        program.add("CALL Write");
        program.add("STORE 1 j");
        program.add("POP 2");
        program.add("HALT");

        while(!program.get(programCounter).equals("HALT")){
//            System.out.println(program.get(programCounter));
            String[] grammar = program.get(programCounter).split(" ");

            if(grammar[0].equals("GOTO"))//-------------------------GOTO------------------------
            {
                System.out.println("GOTO");
                goTo=grammar[1];
                pit=false;
            }
            else //-------------------------label------------------------
            {
                if(grammar[0].equals("LABEL")){
                label.put(grammar[1],programCounter);
                    if(grammar[1].equals(goTo)){
                        pit = true;}
                }

                if(pit==true)
                {
                    switch (grammar[0]) {
                        case "LIT"://-------------------------LIT------------------------
                            System.out.println("-------------------------LIT------------------------");
                            if(grammar.length>2)
                            {
                                stack.push(grammar[1] + " " + grammar[2]);//stacking tag and value
                                System.out.println(stack.peek());
                            }
                            else
                            {
                                stack.push(grammar[1]);
                                System.out.println(stack.peek());
                            }

                            break;
                        case "BOP"://-------------------------BOP------------------------
                            System.out.println("-------------------------BOP------------------------");
                            int sum = 0;
                            String tmp = "";
                            for (int i =0;i<2;i++)
                            {
                                System.out.println("sum "+sum);
                                String temp = stack.pop();
                                try
                                {
                                    sum += Integer.parseInt(temp);
                                }
                                catch (NumberFormatException e)
                                {
                                }
                            }

                            stack.push(String.valueOf(sum));
                            System.out.println(stack.peek());

                            break;

                        case "LOAD"://-------------------------LOAD------------------------

                            System.out.println("-------------------------LOAD------------------------");

                            String load="";
                            tempStack = new Stack<>();
                            while(!stack.empty()){
                                String[] temp = stack.pop().split(" ");

                                if (grammar.length>2&&temp.length>1&&grammar[2].equals(temp[1])) {
                                    load = temp[0];
                                    tempStack.push(temp[0]+" "+temp[1]);
                                    System.out.println(tempStack.peek());
                                }
                                else if(temp.length>1)
                                {
                                    tempStack.push(temp[0]+" "+temp[1]);
                                    System.out.println(tempStack.peek());
                                }
                                else
                                {
                                    tempStack.push(temp[0]);
                                    System.out.println(tempStack.peek());
                                }
                            }

                            reverse(tempStack);
                            stack =tempStack;
                            if (!load.equals(""))
                            {
                                stack.push(load);
                                System.out.println(stack.peek());
                            }

                            break;

                        case"STORE":
                            tempStack = new Stack<>();
                            String tag = grammar[2];
                            System.out.println("-------------------------STORE------------------------");
                            boolean check = false;
                            int counter=0;
                            int index = Integer.parseInt(grammar[1]);
                            load = "";
                            //I could add an exception
                            while(grammar.length>2&&!stack.empty()) {
                                String[] temp = stack.pop().split(" ");
                                if(index==counter){
                                    load  = temp[0]+" "+tag;
                                    check = true;
                                    if(temp.length>1) {
                                        tempStack.push(temp[0] + " " + temp[1]);
                                        System.out.println(tempStack.peek());
                                    }
                                }
                                else if(temp.length>1&&tag.equals(temp[1])){

                                    tempStack.push(load);
                                    System.out.println(tempStack.peek());
                                    check= false;
                                }
                                else if(temp.length>1)
                                {
                                    tempStack.push(temp[0]+" "+temp[1]);
                                    System.out.println(tempStack.peek());
                                }

                                if(stack.empty()&&check==true)
                                {
                                    System.out.println("researching the stack");
                                    stack = tempStack;
                                    tempStack= new Stack<String>();
                                    reverse(stack);
                                }
                                counter++;
                            }
                            reverse(tempStack);
                            stack = tempStack;
                            break;
                        case"ARGS":
                            System.out.println("-------------------------ARGS------------------------");

                            break;
                        case"CALL":
                            System.out.println("-------------------------CALL------------------------");
                            System.out.println("Label: "+grammar[1]);
                            lastPoint = programCounter;
                            newPoint = (int)label.get(grammar[1]);
                            programCounter=newPoint;
                            break;
                        case"POP":
                            System.out.println("-------------------------POP------------------------");
                            System.out.println("stack size is " +stack.size());
                            int limit = Integer.parseInt(grammar[1]);
                            for (int i=0;i<limit;i++)
                            {
                                System.out.println(stack.pop());
                            }
                            break;
                        case"RETURN":
                            System.out.println("-------------------------RETURN------------------------");

                            programCounter = lastPoint;
                            break;
                    }
                }
            }

            programCounter++;
        }

        // TODO run the program and print every stack after each step
    }
}
