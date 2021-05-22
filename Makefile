all:
	javac Diffuseur/*.java
	javac Client/*.java
	javac Messages/*.java

clean:
	rm Diffuseur/*.class
	rm Client/*.class

diff1:
	javac Diffuseur/*.java
	java Diffuseur.Diffuseur Configs/Diffuseur/diff-1.txt 

diff2:
	javac Diffuseur/*.java
	java Diffuseur.Diffuseur Configs/Diffuseur/diff-2.txt 

cli1:
	javac Client/*.java
	java Client.Client Configs/Client/client-1.txt 

cli2:
	javac Client/*.java
	java Client.Client Configs/Client/client-2.txt 

