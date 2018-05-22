import java.util.*;
import java.io.*;

public class WordPairs1
{
   String fileToRead;
   String blockChain;
   DiGraph g;
   int NumOfWords;
   Map<Integer,Collection<String>> trackWordsMap;
   Map<String,List<String>> parentsMap;
     
   public WordPairs1(String fileName)
   {
      String[] wordArray = new String[2];
      
      g = new DiGraph();
     
      fileToRead = fileName;
     
      try
      {
         File infile = new File(fileToRead);
        
         Scanner in = new Scanner(infile);
        
         while (in.hasNextLine())
         {
            String sentence = in.nextLine();
           
            wordArray = sentence.split(" ");
           
            g.addVertex(wordArray[0]);
            g.addVertex(wordArray[1]);
            g.addEdge(wordArray[0],wordArray[1]);
         }
      }
      catch (FileNotFoundException exc)
      {
         System.out.println("File not found.");
      }
   }
   
     
   
   public String wordChain (String first, String last)
   {
      BreadthFirst(first, last,-1);
      
      if(blockChain =="")
      {
         return ("[]");
      }
      return blockChain;
   }
   
   
   public int chainLength(String first, String last)
   {
      BreadthFirst(first, last,-1);
      
      if(!blockChain.equals(""))
      {
         String[] pairArray = blockChain.split(","); 
         return(pairArray.length);  
      }
    
      return Integer.MAX_VALUE;
   }   
    
   public int reachableFrom(String word)
   {
      BreadthFirst(word,null,-1);
      return NumOfWords; 
   }
   
   public int reachableFrom(String word, int maxLength)
   {
      if(maxLength >= 0)
      {
         BreadthFirst(word,null,maxLength);
         return (NumOfWords-1);
      }
      return 0;
   }
   
   public String reachableWords(String word, int maxLength)
   {
      String result = "";
      
      if(maxLength >= 0)
      {
         BreadthFirst(word,null,maxLength);
         for(int i = 0; i<trackWordsMap.size()-1; i++)
         {
            result += trackWordsMap.get(i) + "\n";
         }
         return result;
      }
      return "[]";
   }
   
   public String cycle(String word)
   {
      BreadthFirst(word,word,-1); 
      
      if(blockChain =="")
      {
         return ("[]");
      }
      return blockChain;
   
   }
   
   private String makeBlockChain(String word, List x)
   {
      String chain = "";
      
      x.add(word);
                        
      for(int i = 0; i<x.size()-1; i++)
      {
         chain+= x.get(i)+" "+x.get(i+1)+",";
      }
      chain = "["+chain.substring(0,chain.length()-1)+"]";
      
      return chain;
   
   }
   
   private void BreadthFirst(String first, String last, int maxLength)
   {
      
      NumOfWords = 1;
      
      blockChain = "";
      
      Queue<String> queue = new LinkedList<String>();
            
      parentsMap = new HashMap<String,List<String>>();
      
      trackWordsMap = new HashMap<Integer,Collection<String>>();
      
      Set<String> visitedList = new HashSet<String>();
      
      List<String> parentList = new ArrayList<String>();
      
      Collection<String> levelList = new TreeSet<String>();
      
      if(g.validVertex(first) && (g.validVertex(last)||last==null))
      {  
         if(!first.equals(last))
         {   
            visitedList.add(first);
         }
            
         queue.add(first);
                  
         parentsMap.put(first,parentList);
         
         int sizeCounter = 0;
         
         levelList.add(first);
            
         trackWordsMap.put(parentList.size(),levelList);
         
         while (!queue.isEmpty() && ((parentList.size()<=maxLength)||(maxLength<0)))
         {      
            String wordToRemove = queue.remove();
            
            if(first.equals(last))
            {
               if(visitedList.contains(first))
               {
                  List<String> chainList = new ArrayList<String>(parentsMap.get(first));
                  blockChain = makeBlockChain(first, chainList);
                  break;
               }   
            }
            
            else
            {
               if(wordToRemove.equals(last))
               {
                  
                  List<String> chainList = new ArrayList<String>(parentsMap.get(wordToRemove));
                  blockChain = makeBlockChain(wordToRemove,chainList);
                  break;
               }
            }
                                       
            Iterator<String> iterator = g.getAdjacent(wordToRemove).iterator();  
                  
            while (iterator.hasNext() && ((parentList.size()<=maxLength)||(maxLength<0)))
            {                  
               String iteratedWord = iterator.next();
                  
               if(!visitedList.contains(iteratedWord))
               {
                  
                  parentList = new ArrayList<String>(parentsMap.get(wordToRemove));
                  
                  parentList.add(wordToRemove);
                     
                  if(parentList.size() > sizeCounter)
                  {
                     levelList = new TreeSet<String>();
                     sizeCounter++;
                  }
                     
                  levelList.add(iteratedWord);
                  visitedList.add(iteratedWord);
                  queue.add(iteratedWord);
                  
                  parentsMap.put(iteratedWord, parentList);
                  
                  NumOfWords += 1;
               }
               
                  
            }
               
            trackWordsMap.put(parentList.size(),levelList);
               
         }
      }
   }

}