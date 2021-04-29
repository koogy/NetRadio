all:
	javac Diffuseur/*.java
	javac Client/*.java
	javac Messages/*.java

clean:
	rm Diffuseur/*.class
	rm Client/*.class

diff:
	javac Diffuseur/*.java
	java Diffuseur.Diffuseur Configs/Diffuseur/diff-1.txt 

cli:
	javac Client/*.java
	java Client.Client Configs/Client/client-1.txt 

ges:
	javac Gestionnaire/*.java
	java Gestionnaire.Gestionnaire