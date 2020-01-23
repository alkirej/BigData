import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WordCount
{

/**
 * is the supplied character a valid character for a word?
 * Letters and numbers are considered valid for words.
 *
 * @param   letter  the letter to check for validity as text.
 * @return  true if the supplied character is a letter or #
 */
  private static boolean isValidChar( char letter )
  {
      if ( letter>='a' && letter<='z' )
          return true;
      if ( letter>='A' && letter<='Z' )
          return true;
      if ( letter>='0' && letter<='9' )
          return true;

      return false;
  }

  /**
   * Give a set of text and a starting location, get the word from the
   * text beginning at the given location and going until the next
   * space or other not text character is found.
   *
   * @param  completeText the text to search for the next word
   * @param  startLoc     the location of the first character in our words
   * @return the next word in the text. If multiple non text characters
   *         occur in a row, it is possible for a 0 length String to be
   *         returned.
   */
  private static String getNextWord( String completeText, int startLoc )
  {
      assert (null!=completeText);
      assert (startLoc>=0);
      assert (startLoc<completeText.length());

      StringBuilder returnVal = new StringBuilder();
      int currentLoc = startLoc;
      char letter = completeText.charAt( currentLoc );

      while ( isValidChar(letter) )
      {
        returnVal.append(letter);
        currentLoc++;
        letter = completeText.charAt( currentLoc );
      }

      return returnVal.toString().toLowerCase();
  }

  /**
   * Given some text, generate a map containing alpha-numeric wordCounts
   * as the key and the # of times the word occurs in the text as the
   * value.
   *
   * @param  text  the text to break into words that are counted.
   * @return a Map with words as the keys (String) and their associated wordCounts
   *         as the value (Integer)
   */
  public static Map<String,Integer> generateWordCounts( String text )
  {
      int    stringLoc    = 0;
      Map<String,Integer> wordCounts = new HashMap<String,Integer>();

      while ( stringLoc < text.length() )
      {
          String word = getNextWord( text, stringLoc );
          stringLoc += word.length() + 1;

          if ( word.length() > 0 )
          {
              Integer currentWordCount = wordCounts.get( word );
              if ( null == currentWordCount )
                  currentWordCount = 1;
              else
                  currentWordCount ++;

              wordCounts.put( word, currentWordCount );
          } // if word found
      } // while

      return wordCounts;
  } // generate word wordCounts


  /**
   * Enter here from the command line
   *
   * @param art[0] = the filename to read and count words for
   */
  public static void main( String[] arg )
  {
      assert (arg != null);
      assert (arg.length>0);

      try
      {
          FileReader    fileReader   = new FileReader( arg[0] );
          StringBuilder fileContents = new StringBuilder();

          int nextChar = fileReader.read();

          while ( nextChar >= 0 )
          {
              nextChar = fileReader.read();
              fileContents.append( (char) nextChar );
          }

          String completeText = fileContents.toString();
          Map<String,Integer> wordCounts = generateWordCounts( completeText );

          wordCounts.forEach( (k,v) -> System.out.println( k + "-" + v ) );

      } catch (IOException ioe )
      {
          System.out.println( "File Error:" );
          ioe.printStackTrace();
      }
    }
}
