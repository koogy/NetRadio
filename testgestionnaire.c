
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
char *formatNumber(int n){
    char * num = malloc(3);
     memset(num, '\0', 3);
    if ( n < 10 ){
        sprintf(num,"0%d",n);
    } else {
         sprintf(num,"%d",n);
    }

    return num;
}

char *formatNumberThree(int n){
    char * num = malloc(4);
     memset(num, '\0', 4);
    if(n< 10){
        sprintf(num,"00%d",n);
    }  else  if ( n < 100 ){
        sprintf(num,"0%d",n);
    } 
    else  { 
         sprintf(num,"%d",n);
    }
    return num;
}

int main(int argc,char **argv){
    if(argc <2){
        printf("Gestionnaire - Config file missing");
        exit(0);
    }

    char * gestionnaire_address = NULL;
    char * port_temp = NULL;
    int gestionnaire_port = 0;
    size_t len = 0;
    FILE *fp = fopen(argv[1], "r"); 

     if (fp == NULL){
      perror("Error while opening the file.\n");
      exit(EXIT_FAILURE);}

    getline(&gestionnaire_address, &len, fp);
    getline(&port_temp, &len, fp);
    gestionnaire_port = atoi(port_temp);

    char *ip_adresse = malloc(16);
    memset(ip_adresse,'\0',16);
    char *ptr = strtok(gestionnaire_address, ".");

    int count = 0;
	while(ptr != NULL)
	{
        strcat(ip_adresse,formatNumberThree(atoi(ptr)));
        if(count < 3){
          strcat(ip_adresse,".");
        }
		ptr = strtok(NULL, ".");
        count = count+1;
	}

    printf("%s",ip_adresse);
    fclose(fp);
}