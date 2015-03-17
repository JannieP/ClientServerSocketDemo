ClientServerSocketDemo
======================

Client\Server Socket Demo

Design Details
~~~~~~~~~~~~~~

General:
 - Used Maven for build and dependency management
 - Used JUnit with Mokito for code unit testing
 - Although the requirement clearly states that the Client should 
   output to the console using "System.out" and in the code, I replaced the 
   default System.out with a local Printstream which redirects two of the 
   methods of System.out to log to log4j.  I strongly feel that intercepting the 
   System.out as mentioned above is not best practice. Therefore I would opt for 
   all-round use of a logging framework like log4j which offers many advantages over 
   intercepting System.out
 - Each jar contains its own log4j.properties file, however can be overwritten with a JVM parameter
   as can be seen in the batch files. log4j.XXX.properties files are included in the root folder 
 - I have included batch files for aiding start-up of the Client and Server, however it is not 
   compulsory to use them
 - Server Address, Port and FilePath is configures in server.properties in the root path

Included additionally in the root folder is: 
#logs/ 
folder for the currently generated log files 

#testFiles/
folder with files used for testing Client/Server

#server.properties 
server.port=6006
server.address=localhost
server.filePath=c:/dhscs/testFiles/testFile2.txt

#log4j.client.properties
log4j.rootLogger = INFO, Console, File
 
#log4j.server.properties
log4j.rootLogger = DEBUG, Console, File 

#runServer.bat
Starts Server

#runClient.NETWORK.bat
Starts Client in NETWORK Mode

#runClient.STANDALONE.bat
Starts Client in STANDALONE mode




Server:
 - The Server class itself is a Thread class and uses threading for the networking in order to 
   avoid locking up the JVM is a connection hangs   
 - Using the standalone option will not invoke a new thread
 - Used a Builder pattern to build up the Server instance object 
 - ServerBuilder uses PropertiesHelper to get the ConnectionDto for the port information
 - Server uses FileHelper to read the content of the specified file

Client:
 - Start with one of two parameters NETWORK or STANDALONE
 - Uses and Enum instead of constants to define and validate the parameter values
 - uses PropertiesHelper to get the ConnectionDto  for the Server address and port information
 - uses PropertiesHelper to get the file path
 - Client is defined by an interface ICLient
 - AbstractClient defines the common methods of a Client
 - NetworkClient / StandaloneClient respectively specialise their own needed functionality
 - NetworkClient links to network
 - StandaloneClient instantiates Server directly  

Common:
 - ConnectionDto, is a connection data transfer object between common, client and server
 - Filehelper reads a specified file and returns the contents.
 - Sever is defined by the interface iServer, this was placed in the common project in order 
   to enhance the Server project loosely coupled to the Client  
 - OutputStream is an interceptor used to rewire the System.out in the Client
 - PropertiesHelper, uses a singleton pattern to load properties from a file and make 
   them available through select methods
 - NumbersUtil, follows a static utility pattern which only performs actions 
   and has no need to be instantiated


