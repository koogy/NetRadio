# GROUPE 32

- Alexandre LY
- Emma BOTTI 
- Nicolas NGAUV

# Compilation et éxecution

- cd projetpr6_netradio
- make : pour compiler tous les fichiers puis 
- Pour compiler et éxecuter le diffuseur 
    - make difX où X est le numéro du fichier config du diffuseur
- Pour compiler et éxecuter le client 
    - make cliX où X est le numéro du fichier config du client
- Pour éxecuter le gestionnaire
    - cd Gestionnaire 
    - make gesX où X est le numéro du fichier config du client

# Architecture du code 

- Java : Diffuseur et client

    - **Diffuseur.java** : Main
        - DiffuseurTCP.java : Permet de s'enregistrer auprès du gestionnaire
        - DiffuseurUDP.java : Diffusion des messages 
        - ReceptionTCP.java : Reception des messages particuliers venant du client, gestionnaire
    - **Client.java** : Main
        - MessageReceiver.java : Recoit les messages diffusés
        - MessageSender.java : Envoie des messages particulier au diffuseur/gestionnaire
    - **Classe helper** :
        - Message.java : Formate les messages 
        - MessageDiffuseur.java : ArrayList contenant les messages à diffuser
        - MessageType.java : Type des messages (ENUM)

- C : Gestionnaire
    - **Gestionnaire.c** : Reception des messages venant du client, diffuseur
        - DiffuseurList.c : implementation d'une linkedlist pour stocker les diffuseurs
        - DiffuseurChecker.c : Vérifie si les diffuseurs sont toujours catifs 
