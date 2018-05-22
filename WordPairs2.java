import java.util.*;
import java.io.*;

public class WordPairs2
{
   String fileToRead = "";
   DiGraph g;
   int NumOfWords;
   Map<Integer,Collection<String>> trackWordsMap;
     
   public WordPairs2(String fileName)
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
         
         //System.out.println(g);
      }
      catch (FileNotFoundException exc)
      {
         System.out.println("File not found.");
      }
   }
   
   public Set getAdj(String word)
   {
      return g.getAdjacent(word);
   }
   
   private String BreadthFirst(String first, String last, int maxLength)
   {
      String lastString = last;
      
      String result = "";
      
      NumOfWords = 1;
      
      Queue<String> queue = new LinkedList<String>();
      
      Set<String> visitedList = new HashSet<String>();
            
      Map<String,String> aMap = new HashMap<String,String>();
      
      Map<String,Set<String>> parentsMap = new HashMap<String,Set<String>>();
      
      trackWordsMap = new HashMap<Integer,Collection<String>>();
      
      
      if((g.validVertex(first) && (g.validVertex(last)||last==null)) && (!first.equals(last)))
      {              
         queue.add(first);
            
         visitedList.add(first);
         
         if(maxLength == -1)
         {   
            while (!queue.isEmpty())
            {
               String wordToRemove = queue.remove();
                     
               if(wordToRemove.equals(last))
               {
                  while(aMap.get(lastString)!= first)
                  {
                     result = ","+aMap.get(lastString)+" "+lastString+""+result;
                     lastString = aMap.get(lastString);
                  }
                  result = aMap.get(lastString)+" "+lastString+result;
                  return ("["+result+"]");
               }
                  
               Iterator<String> iterator = g.getAdjacent(wordToRemove).iterator();  
                           
               while (iterator.hasNext())
               {
                  String iteratedWord = iterator.next();
                              
                  if (!visitedList.contains(iteratedWord))
                  {
                     visitedList.add(iteratedWord);
                     aMap.put(iteratedWord,wordToRemove);
                     queue.add(iteratedWord);
                     NumOfWords += 1;  
                  }
               }
            }
            return("[]");
         }
         else
         {
            Set<String> parentList = new HashSet<String>();

            parentsMap.put(first,parentList);
            int sizeCounter = 0;
            
            Collection<String> levelList = new TreeSet<String>();//
            levelList.add(first);//
            
            trackWordsMap.put(parentList.size(),levelList);//
         
            while (!queue.isEmpty() && parentList.size()<=maxLength)
            {
               System.out.println("before loop: "+parentList);//
               
               String wordToRemove = queue.remove();
                           
               Iterator<String> iterator = g.getAdjacent(wordToRemove).iterator();  
                  
               while (iterator.hasNext() && parentList.size()<=maxLength)
               {                  
                  String iteratedWord = iterator.next();
                     
                  if (!visitedList.contains(iteratedWord))
                  {
                     parentList = new HashSet<String>(parentsMap.get(wordToRemove));
                     parentList.add(wordToRemove);
                     
                     if(parentList.size() > sizeCounter)//
                     {
                        levelList = new TreeSet<String>();
                        sizeCounter++;
                     }
                     
                     levelList.add(iteratedWord);//
                     visitedList.add(iteratedWord);
                     queue.add(iteratedWord);
                     parentsMap.put(iteratedWord, parentList);
                     NumOfWords += 1;
                  }
                  
               }
               
               System.out.println("after loop; "+parentList+"\n");//
               
               trackWordsMap.put(parentList.size(),levelList);//
               
            }
            if(queue.isEmpty() && (parentList.size() == maxLength()))
            {
               NumOfWords += 1;
               trackWordsMap.put(parentList.size()+1,levelList);
            }
         }
      }
      return("[]");
   }
  
   
   public String wordChain (String first, String last)
   {
      return(BreadthFirst(first, last,-1));
   }
   
   
   public int chainLength(String first, String last)
   {
      String wordPair = BreadthFirst(first, last,-1);
      
      if(!wordPair.equals("[]"))
      {
         String[] pairArray = wordPair.split(","); 
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
      String result = "";
      String firstWord = word;
     
      Queue<String> queue = new LinkedList<String>();
      
      Set<String> visitedListForChain = new HashSet<String>();//
      
      Map<String,String> aMap = new HashMap<String,String>();    
      
      if(g.validVertex(word))
      {              
         queue.add(word);
           
         while (!queue.isEmpty())
         {
            String wordToRemove = queue.remove();
                     
            if(visitedListForChain.contains(word))
            {
               while(aMap.get(word)!= firstWord)
               {
                  result = ","+aMap.get(word)+" "+word+""+result;
                  word = aMap.get(word);
               }
               result = aMap.get(word)+" "+word+result;
               return ("["+result+"]");
            }
                  
            Iterator<String> iterator = g.getAdjacent(wordToRemove).iterator();  
                           
            while (iterator.hasNext())
            {
               String iteratedWord = iterator.next();
                              
               if (!visitedListForChain.contains(iteratedWord))
               {
                  visitedListForChain.add(iteratedWord);
                  aMap.put(iteratedWord,wordToRemove);
                  queue.add(iteratedWord);
               }
            }
         }
         return("[]");
      }
      return("[]");
   }
}