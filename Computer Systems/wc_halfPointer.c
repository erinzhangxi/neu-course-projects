#include<stdio.h>

#define IN 1 /* inside a word */
#define OUT 0  /* outside a word */

int main() {
	int nl, nw, nc, state;
	nw = nc = 0;
	nl = 1;
	state = OUT;
	
	char x1[100] = "The quick brown fox jumped over the lazy dog.";
	char *x1ptr = x1;
	
	int i = 0;
	while (*(x1ptr + i) != '\0') {
		++nc;
		
		if (*(x1ptr + i) == '\n') {
			++nl;
		}
		if (*(x1ptr + i) == ' ' || *(x1ptr + i) == '\n' || *(x1ptr + i) == '\t') {
			state = OUT;
		}
		else if (state == OUT) {
			state = IN;
			++nw;
		}
		i++;
	}
	printf("%d %d %d\n", nl, nw, nc);
    return 0;
}