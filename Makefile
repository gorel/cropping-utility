all: CroppingUtility
	rm -f *~
	clear

CroppingUtility: 
	javac CroppingUtility.java

clean:
	rm -f *~ *.class
