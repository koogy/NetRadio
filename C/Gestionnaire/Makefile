CC=gcc
CFLAGS = -g -Wall -std=c11

SRC :=   $(wildcard core/*.c) $(wildcard utils/*.c) $(wildcard commands/*.c) $(wildcard *.c)

OBJECTS = $(SRC:.c=.o) 

main : $(OBJECTS)
	gcc -o $@ $^

start:
	./main

clean:
	rm -rf $(OBJECTS)
	rm star
