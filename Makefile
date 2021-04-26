all:
	javac Diffuseur/*.java
	javac Client/*.java

clean:
	rm Diffuseur/*.class
	rm Client/*.class

diff:
	java Diffuseur.Diffuseur

cli:
	java Client.Client