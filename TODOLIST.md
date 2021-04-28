- Un fichier contenant une liste de message à diffuser
- Les fichiers clients/server config comme dans la demo du prof -> Actuellement hardcoder
- Plusieurs clients -> Crée plusieurs thread

- Vérifier que la forme des messages est bien respectée 

<!--  -->

— num-mess sera codé sur 4 octets et contiendra la chaîne de caractères correspondant au numéro du
message. Par exemple, pour le message 120, num-mess vaudra 0120.
— id sera codé sur 8 octets et contiendra une chaîne de caractères. Si l’identifiant contient moins de 8
caractères, alors on complètera la fin de la chaîne avec des caractères #. Par exemple si l’identifiant
est RADIO, alors id vaudra RADIO###.
— mess sera codé sur 140 octets et contiendra une chaîne de caractères. Si le message contient moins
de 140 caractères, mess sera complété comme id avec des caractères # à la fin.
— nb-mess sera codé sur 3 octets et contiendra la chaîne de caractères correspondant au nombre de
messages. Si la chaîne de caractères contient moins de 3 caractères, on la complètera avec des 0 au
début comme pour num-mess.
— ip1 et ip2 seront codés sur 15 octets et contiendront la chaîne de caractères correspondant à
l’adresse IPv4. Par exemple, si l’adresse IP est 127.0.0.1, alors ip1 contiendra 127.000.000.001
.
— port1 et port2 seront codés sur 4 octets et contiendront la chaîne de caractères correspondant au
numéro de port.
— num-diff sera codé sur 2 octets et contiendra la chaîne de caractères correspondant au nombre de
diffuseurs. Si la chaîne de caractères contient moins de 2 caractères, on la complètera avec des 0 au
début comme pour num-mess.
