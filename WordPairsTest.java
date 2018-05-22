/**
  Program to perform basic testing of the WordPairs class.  
  Expected output is below.
  
  There are 3 pairs in the chain from 'account' to 'steak':
  [account book,book club,club steak]
  
  There are 23 words reachable from book
  
  There are 13 words reachable within 2 levels of book:
  [book]
  [club, end, jacket, learning, review, scorpion, value]
  [chair, foot, moss, sandwich, steak]
  
  The shortest cycle from book to book: [book club,club foot,foot rule,rule book]
  There is no cycle from account to account

  There are 1056 words reachable from book
  The shortest cycle from account to account: [account current,current account]
   
*/
public class WordPairsTest
{     
   public static void main (String[] args)
   {
   //    WordPairs wp = new WordPairs("wordpairs1.txt");
//       
//       System.out.println("There are " + wp.chainLength("book", "book") +
//                          " pairs in the chain from 'account' to 'steak':\n" + 
//                          wp.wordChain("book", "book"));
//       System.out.println("\nThere are " + wp.reachableFrom("book") + 
//                         " words reachable from book");
//       System.out.println("\nThere are " + wp.reachableFrom("book", 2) + 
//                          " words reachable within 2 levels of dead:\n" + 
//                          wp.reachableWords("book", 2));
// 
//       String cycle1 = wp.cycle("book");
//       String cycle2 = wp.cycle("account");
//       if (cycle1.equals("[]"))
//          System.out.println("There is no cycle from book to book");
//       else
//          System.out.println("The shortest cycle from book to book: " + cycle1);
//       if (cycle2.equals("[]"))
//          System.out.println("There is no cycle from account to account");
//       else
//          System.out.println("The shortest cycle from account to account: " + cycle2);
         
      WordPairs wp2 = new WordPairs("wordpairs1.txt");
      System.out.println("\nThere are " + wp2.reachableFrom("poetic",6)+ " words reachable within 2 levels of dead:\n" + wp2.reachableWords("poetic",3)+wp2.reachableFrom("poetic"));
      //System.out.println("\nThere are " + wp2.reachableFrom("book") + 
//                          " words reachable from book");
//       cycle2 = wp2.cycle("account");
//       if (cycle2.equals("[]"))
//          System.out.println("There is no cycle from account to account");
//       else
//          System.out.println("The shortest cycle from account to account: " + cycle2);  
   }
}
