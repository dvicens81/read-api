# read-api

Spring boot project

This project run in port 8586 and get the information from https://musicbrainz.org/doc/Development/XML_Web_Service/Version_2

Pre-requisites:

 * Maven installed.
 * SonarQube installed (optional)
 
Create jar:

 * Open a commandline
 * <root_project_path>: mvn clean install
 * If SonarQube is installed: <root_project_path>: mvn sonar:sonar
   * Analyze results.
 * Run the jar file: 
   * <root_project_path>/target: java -jar <name_jar_file>
   

To test it.

  * http://<<url>>:8586/api/read/artist/<id_artist>?type=album&limit=50&offset=1
