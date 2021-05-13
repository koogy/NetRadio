#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char **split_message(char *messsage){

    char **message_split = (char **) malloc(sizeof(char *) * ((5) ));
    char *copy = (char *) malloc(sizeof(char) * strlen(messsage)+1);
    memmove(copy, messsage, strlen(messsage)+1);

    char *p  = strtok(copy, " ");
    int i = 0;
    while(p != NULL) {
        message_split[i++] = p;
        p = strtok(NULL, " ");
    }
    
    return message_split;
}


int main(){

    char ** mess = split_message("REGI IDENTIFIANT ADR LOCALHOST PORT");
    printf("%s",mess[3]);
    return 0;
}