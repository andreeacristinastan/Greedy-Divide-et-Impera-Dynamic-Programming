build: Walsh.java Statistics.java
	javac Walsh.java
	javac Statistics.java
	javac Prinel.java
	javac Crypto.java

run-p1:
	java Walsh

run-p2:
	java Statistics

run-p3:
	java Prinel

run-p4:
	java Crypto

clean:
	rm -f *.class
	
.PHONY: build clean