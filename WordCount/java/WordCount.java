import java.io.FileReader;
import java.io.IOException;

public class WordCount
{

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
        letter = completeText.charAt( currentLoc );
        returnVal.append(letter);
        currentLoc++;
      }

      return returnVal.toString();
  }

  public static void main( String[] arg )
  {
    assert (arg != null);
    assert (arg.length>0);

    try
    {

      FileReader fr = new FileReader( arg[0] );
      StringBuilder sb = new StringBuilder( 1000000 );

      int nextChar = fr.read();

      while ( nextChar >= 0 )
      {
          nextChar = fr.read();
          sb.append( (char) nextChar );
      }

      String completeText = sb.toString();
      int    stringLoc    = 0;

      String s = getNextWord( completeText, 0 );

      System.out.println( s );

    } catch (IOException ioe )
    {
      System.out.println( "File Error:" );
      ioe.printStackTrace();
    }

    System.out.println( arg[0] );
  }
}
