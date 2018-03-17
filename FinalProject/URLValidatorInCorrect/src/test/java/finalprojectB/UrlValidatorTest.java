
package finalprojectB;

import finalprojectB.UrlValidator;
import junit.framework.TestCase;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest01()
   {
	   /*Commented out code can be run once the following bugs have been fixed:
	    * Bug 1 (Found and resolved here): regexs != null --> regexs == null [line 120 RegexValidator.java]
	    * Bug 2 (Found and resolved here): regexs.length-1; --> regexs.length; [line 125 RegexValidator.java]
	    * Bug 3 (Found and resolved here): return null; --> return VALIDATOR; [line 68 InetAddressValidator.java]
	    */
	   System.out.println("Test All Schemes:");
	   UrlValidator urlValidator = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   System.out.println("http://www.google.com/" + "   Expected: true   Actual: " + urlValidator.isValid("http://www.google.com/"));
	   //System.out.println("https://www.google.com/" + "   Expected: true   Actual: " + urlValidator.isValid("https://www.google.com/")); //Error
	   //System.out.println("ftp://www.google.com/" + "   Expected: true   Actual: " + urlValidator.isValid("ftp://www.google.com/")); //Error
	   //3 Bugs found after extensive debugging!
	   System.out.println("www.google.com/" + "   Expected: false   Actual: " + urlValidator.isValid("www.google.com/"));
	   System.out.println();
	   //Error: only http or no url scheme is allowed for ALLOW_ALL_SCHEMES
	   //Bugs found so far(3): RegexValidator.java(lines 120 and 125), InetAddressValidator.java(line 68)
	   //Changes were made in the code for future tests
   }
   
   public void testManualTest02()
   {
	   /*Commented out code can be run once the following bugs have been fixed:
	    * Bug 1 (Found and resolved in testManualTest01()): regexs != null --> regexs == null [line 120 RegexValidator.java]
	    * Bug 2 (Found and resolved in testManualTest01()): regexs.length-1; --> regexs.length; [line 125 RegexValidator.java]
	    * Bug 3 (Found and resolved in testManualTest01()): return null; --> return VALIDATOR; [line 68 InetAddressValidator.java]
	    * Bug 4: schemes[i].toUpperCase --> schemes[i].toLowerCase [line 282 UrlValidator.java]
	    * Bug 5 (Found and resolved here): return !isValidTld(groups[0]); --> return isValidTld(groups[0]); [line 166 DomainValidator.java]
	    */
	   System.out.println("Test Default Schemes:");
	   UrlValidator urlDefault = new UrlValidator();
	   System.out.println("http://www.google.com/" + "   Expected: true   Actual: " + urlDefault.isValid("http://www.google.com/")); //returned false
	   //Bug: Default Schemes do not return true
	   //Partner resolved bug in UrlValidator.java(line 282)
	   System.out.println("http://www.google.com/" + "   Expected: true   Actual: " + urlDefault.isValid("http://www.google.com/")); //returned true after change
	   //System.out.println("ftp://www.google.com/" + "   Expected: true   Actual: " + urlDefault.isValid("ftp://www.google.com/")); //Error
	   //Bug found and corrected: DomainValidator.java(line 166)
	   //System.out.println("ftp://www.google.com/" + "   Expected: true   Actual: " + urlDefault.isValid("ftp://www.google.com/")); //returned true after change
   }
   
   
   public void testAuthorityPartition()
   {
	   /*In order to run this test, these bugs must be changed in the code(bugs were discovered from manual testing):
	    * Bug 1 (Found and resolved in testManualTest01()): regexs != null --> regexs == null [line 120 RegexValidator.java]
	    * Bug 2 (Found and resolved in testManualTest01()): regexs.length-1; --> regexs.length; [line 125 RegexValidator.java]
	    * Bug 3 (Found and resolved in testManualTest01()): return null; --> return VALIDATOR; [line 68 InetAddressValidator.java]
	    * Bug 4: schemes[i].toUpperCase --> schemes[i].toLowerCase [line 282 UrlValidator.java]
	    * Bug 5 (Found and resolved in testManualTest02()): return !isValidTld(groups[0]); --> return isValidTld(groups[0]); [line 166 DomainValidator.java]
	    */
	   
	   String[] authorityUrlString = {"www.google.com", 
	              "go.com", 
	              "go.au", 
	              "0.0.0.0", 
	              "255.255.255.255", 
	              "256.256.256.256", 
	              "255.com", 
	              "1.2.3.4.5", 
	              "1.2.3.4.", 
	              "1.2.3", 
	              ".1.2.3.4", 
	              "go.a", 
	              "go.a1a", 
	              "go.1aa", 
	              "aaa.", 
	              ".aaa", 
	              "aaa", 
	              ""}; 
	   System.out.println("\nTesting Authority Partition:");
	   UrlValidator urlAuthority = new UrlValidator(authorityUrlString, 0);
	   for (int i = 0; i < authorityUrlString.length; i++) {
		      String curAuthority = authorityUrlString[i];
		      System.out.println(curAuthority + " " + urlAuthority.isValidAuthority(curAuthority)); //Bug found!
	   }
	   //Bug 6 (Found and resolved here): if (groups != null) --> if (groups == null) [line 88 InetAddressValidator.java]
	   //Implementing this change will prevent any errors from occurring when running the test
   }

   
   public void testIsValidOptions()
   {
	   /*In order to run this test, these bugs must be changed in the code(bugs were discovered from manual and partition testing):
	    * Bug 1 (Found and resolved in testManualTest01()): regexs != null --> regexs == null [line 120 RegexValidator.java]
	    * Bug 2 (Found and resolved in testManualTest01()): regexs.length-1; --> regexs.length; [line 125 RegexValidator.java]
	    * Bug 3 (Found and resolved in testManualTest01()): return null; --> return VALIDATOR; [line 68 InetAddressValidator.java]
	    * Bug 4: schemes[i].toUpperCase --> schemes[i].toLowerCase [line 282 UrlValidator.java]
	    * Bug 5 (Found and resolved in testManualTest02()): return !isValidTld(groups[0]); --> return isValidTld(groups[0]); [line 166 DomainValidator.java]
	    * Bug 6 (Found and resolved in testAuthorityPartition()): if (groups != null) --> if (groups == null) [line 88 InetAddressValidator.java]
	    */
	   
	   String[] schemeUrlString = {"http://",
			 "ftp://",
	         "h3t://",
	         "3ht://",
	         "http:/",
	         "http:",
	         "http/",
	         "://",
	         ""};

	   String[] authorityUrlString = {"www.google.com",
			"go.com",
			"go.au",
	        "0.0.0.0",
	        "255.255.255.255",
	        "256.256.256.256",
	        "255.com",
	        "1.2.3.4.5",
	        "1.2.3.4.",
	        "1.2.3",
	        ".1.2.3.4",
	        ""};

	   String[] portUrlString = {":80",
	        ":65535",
	        ":0",
	        "",
	        ":-1",
	        ":65636",
	        ":65a"};

	   String[] pathUrlString = {"/test1",
			"/t123",
			"/$23",
			"/..",
			"/../",
			"/test1/",
			"",
			"/test1/file",
			"/..//file",
	   		"/test1//file"};

	   //Test allow2slash, noFragment
	   String[] pathOptionsUrlString = {"/test1",
	        "/t123",
	        "/$23",
	        "/..",
	        "/../", 
	        "/test1/",
	        "/#", 
	        "", 
	        "/test1/file", 
	        "/t123/file", 
	        "/$23/file", 
	        "/../file", 
	        "/..//file", 
	        "/test1//file", 
	        "/#/file"};
	   
	   String[] queryUrlString = {"?action=view",
	        "?action=edit&mode=up",
	        ""};
	   
	   System.out.println("\nProgramming Testing: ");
	   int validCount = 0;
	   int invalidCount = 0;
	   int runs = 1000;
	   for(int i = 0; i < runs; i++) {
		   int schemeInt = (int)(Math.random() * schemeUrlString.length);
		   int authorityInt = (int)(Math.random() * authorityUrlString.length);
		   int portInt = (int)(Math.random() * portUrlString.length);
		   int pathInt = (int)(Math.random() * pathUrlString.length);
		   int optionsInt = (int)(Math.random() * pathOptionsUrlString.length);
		   int queriesInt = (int)(Math.random() * queryUrlString.length);
		   String url = schemeUrlString[schemeInt] + authorityUrlString[authorityInt] + portUrlString[portInt] + pathUrlString[pathInt] 
				   + pathOptionsUrlString[optionsInt] + queryUrlString[queriesInt];
		   UrlValidator validator = new UrlValidator(UrlValidator.ALLOW_2_SLASHES + UrlValidator.ALLOW_ALL_SCHEMES + UrlValidator.NO_FRAGMENTS);
		   
		   boolean valid = validator.isValid(url);
		   if(valid == true) {
			   //System.out.println("+++Valid+++:  " + url);
		       validCount++;
		   } 
		   else {
			   //System.out.println("---Invalid---:  " + url);
			   invalidCount++;
		   }
	   }
	   System.out.println("Number of Valids: " + validCount);
	   System.out.println("Number of Invalids: " + invalidCount);
   }
   
   

}
