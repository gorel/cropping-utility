all: CroppingUtility
	rm -f *~
	clear

CroppingUtility: TransparentPanel HeightAndWidthInputPane
	javac CroppingUtility.java

TransparentPanel: MovementListener
	javac TransparentPanel.java

HeightAndWidthInputPane:
	javac HeightAndWidthInputPane.java

MovementListener:
	javac MovementListener.java

clean:
	rm -f *~ *.class
