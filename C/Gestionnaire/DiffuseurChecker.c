#include <unistd.h>

#include <stdio.h>

#include "DiffuseurChecker.h"

#include "DiffuseurList.h"

#include <string.h>

#include <stdlib.h>

#include <stdio.h>

#include <stdlib.h>

#include <unistd.h>

#include <errno.h>

#include <string.h>

#include <netdb.h>

#include <sys/types.h>

#include <netinet/in.h>

#include <sys/socket.h>

#include <arpa/inet.h>

char ** split_message(char * messsage) {

  char ** message_split = (char ** ) malloc(sizeof(char * ) * ((5)));
  char * copy = (char * ) malloc(sizeof(char) * strlen(messsage) + 1);
  memmove(copy, messsage, strlen(messsage) + 1);

  char * p = strtok(copy, " ");
  int i = 0;
  while (p != NULL) {
    message_split[i++] = p;
    p = strtok(NULL, " ");
  }

  return message_split;
}

void * check_diffuseur(void * arg) {
  struct DiffuseurList * head = arg;
  struct DiffuseurList * head_copy = arg;
  int index = 0;
  while (1) {
    if (head -> next != NULL) {
      char ** message_information = split_message(head -> next -> diffuseur_information);

      int port = atoi(message_information[4]);
      struct sockaddr_in adress_sock;
      adress_sock.sin_family = AF_INET;
      adress_sock.sin_port = htons(port);
      inet_aton(message_information[3], & adress_sock.sin_addr);

      int descr = socket(PF_INET, SOCK_STREAM, 0);
      int r = connect(descr, (struct sockaddr * ) & adress_sock,
        sizeof(struct sockaddr_in));
   
        char * mess = "RUOK\r\n";
        send(descr, mess, strlen(mess), 0);

        struct timeval tv;
        fd_set initial;
        int ret = 0;
        tv.tv_sec = 2;
        tv.tv_usec = 500000;
        FD_ZERO( & initial);
        FD_SET(descr, & initial);
        while (1) {
          fd_set rfds;
          rfds = initial;
          ret = select(descr + 1, & rfds, NULL, NULL, & tv);
          if (FD_ISSET(descr, & rfds)) {
            char buff[100];
            int size_rec = recv(descr, buff, 99 * sizeof(char), 0);
            buff[size_rec] = '\0';
            break;
          } else {
            printf("Timed out, removing diffuseur:  %s .\n", head->next->diffuseur_information);
            remove_from_list(&head_copy->next,index);
            break;
          }
        }
        /* .... */
        
    
  if (head -> next != NULL) {
      head = head -> next;  

  }
      index = index +1;


    } else {
      head = arg;
      index = 0;
         sleep(3);
    }
 
  }

}