all:
	javac Diffuseur/*.java
	javac Client/*.java
	javac Messages/*.java

clean:
	rm Diffuseur/*.class
	rm Client/*.class

diff:
	javac Diffuseur/*.java
	java Diffuseur.Diffuseur Configs/diff-1.txt 

cli:
	javac Client/*.java
	java Client.Client Configs/client-1.txt 