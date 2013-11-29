TEAM MEMBERS
	Bruno Boivin (260380817) <bruno.boivin@mail.mcgill.ca>
	Salman Hashmi (260369764) <salman.hashmi2@mail.mcgill.ca>
	Anita Szilagyi (260366272) <anita.szilagyi@mail.mcgill.ca>
	Shahrzad Tighnavardmollasaraei (260413622) <shahrzad.tighnavardmollasaraei@mail.mcgill.ca>
	Kaichen Wang (260480833) <kaichen.wang@mail.mcgill.ca>

COPYRIGHT NOTICE
	All rights reserved © Bruno Boivin, 
						  Salman Hashmi, 
						  Anita Szilagyi, 
						  Shahrzad Tighnavardmollasaraei, 
						  Kaichen Wang.

URL OF GIT REPOSITORY
	https://github.com/brunoboivin/LightRacerProject

LIST OF DEPENDENCIES
	Programming Language(s): Java
	Required to run project: Java Runtime Environment (JRE)

PLATFORMS
	Operating Systems: Windows 7, Windows 8, Mac OS X (10.9)
	Compiler version:  JDK1.7.0, which includes JRE 1.7.0 
	IDE: Eclipse

LIST OF THIRD-PARTY LIBRARIES
	1. WindowBuilder Pro: Used to design panels and frames; used to develop Java graphical user interfaces
		- Home page: https://developers.google.com/java-dev-tools/wbpro/
		- Version used: 4.3
		- Installation instructions:
			a. In Eclipse, click on Help -> Install New Software -> Add
			b.  Name: WindowBuilder Pro
				Location: http://download.eclipse.org/windowbuilder/WB/release/R201309271200/4.3/
	
	2. Class StringUtils (org.apache.commons.lang3.StringUtils): Used to simplify password validation, i.e. validation of strings
		- Home page: http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html
		- Version: 3.1
		- Installation instructions:
			a. Download the following file: http://apache.mirror.vexxhost.com//commons/lang/binaries/commons-lang3-3.1-bin.zip
			b. Add the jar file to the .classpath file of the project
	
	3. StarWars font
		- Home page: http://www.fonts2u.com/starwars.font
		- Version: 
		- Installation instructions:
			a. Download the following file: http://www.fonts2u.com/download/starwars.font
			b. Open the README file and follow the installation instructions based on your operating system
	
	4. MigLayout 
		- Home page: https://developers.google.com/java-dev-tools/wbpro/layoutmanagers/swing/miglayout
		- Version: 15-swing
		- Installation instructions: Installed automatically when used for the first time in WindowBuilder Pro
	
	5. FormLayout 
		- Home page: https://developers.google.com/java-dev-tools/wbpro/layoutmanagers/swt/formlayout
		- Version: 1.3.0
		- Installation instructions: Installed automatically when used for the first time in WindowBuilder Pro

INSTRUCTIONS FOR COMPILING AND RUNNING THE CODE
	Step 1: Run build.xml (as an Ant build if inside Eclipse)
	Step 2: Launch the main method of MainFrame.java, which is located inside the GUIPkg.
			Or alternatively, simply launch the executable Tron.jar file.