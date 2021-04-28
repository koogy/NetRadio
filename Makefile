all:
	javac Diffuseur/*.java
	javac Client/*.java
	javac Messages/*.java

clean:
	rm Diffuseur/*.class
	rm Client/*.class

diff:
	javac Diffuseur/*.java
	java Diffuseur.Diffuseur Configs/diff-config.txt 

cli:
	javac Client/*.java
	java Client.Client Configs/client-config.txt 