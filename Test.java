import java.util.*;
import java.io.*;

public class Test
{
   public static void main(String[] args)
   {
      WordPairs1 obj = new WordPairs1("wordPairs1.txt");
      System.out.println(obj.wordChain("book","book"));
      System.out.println(obj.chainLength("book","book"));
      System.out.println(obj.reachableFrom("dead"));
      System.out.println(obj.reachableFrom("dead",7));
      System.out.println(obj.reachableWords("dead",7));
      //System.out.println(obj.cycle("account"));
      
      // String word = "dead";
//       System.out.println(word+"'s children: "+obj.getAdj(word));
//       
//       Iterator<String> iterator = obj.getAdj(word).iterator();
//       
//       while(iterator.hasNext())
//       {
//          String iteratedWord = iterator.next();
//          
//          System.out.println(iteratedWord+"'s children: "+obj.getAdj(iteratedWord));
//       }

      
      
   }
}