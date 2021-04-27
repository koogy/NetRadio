all:
	javac Diffuseur/*.java
	javac Client/*.java

clean:
	rm Diffuseur/*.class
	rm Client/*.class

diff:
	javac Diffuseur/*.java
	java Diffuseur.Diffuseur

cli:
	javac Client/*.java
	java Client.Client