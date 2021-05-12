#include <unistd.h>
#include <stdio.h>
#include "DiffuseurChecker.h"
#include "DiffuseurList.h"


void *check_diffuseur(void * arg){
  struct DiffuseurList * head = arg;
    printf("%s\n","i'm here");
    while(1){
    print_list(head->next);
    sleep(2);
    }

}