#include <stdio.h>

/* count digits, white space, others */
/* to run, try:    gcc THIS_FILE.c ; cat ANY_FILE | ./a.out */

main()
{
    int i, j, k, nwhite, nother;
    int ndigit[10];
	j = 0;
    nwhite = nother = 0;
    char x1[100] = 
    "The 25 quick brown foxes jumped over the 27 lazy dogs 17 times.";
    char *x1ptr = x1;
    int *ndptr = ndigit;
    
    for (i = 0; i < 10; ++i)
        *(ndptr + i) = 0;
        
    while (*(x1ptr + j) != '\0') {
        if (*(x1ptr + j) >= '0' && *(x1ptr + j) <= '9') {
        	k = *(x1ptr + j) - '0';
        	++*(ndptr + k);
        }  
        else if (*(x1ptr + j) == ' ' || *(x1ptr + j) == '\n' 
        || *(x1ptr + j) == '\t') {
    		++nwhite;
        }
        else {
        	++nother;
        }
            
        j++;
    }
    
    printf("digits =");
    for (i = 0; i < 10; ++i)
        printf(" %d", *(ndptr + i));
    printf(", white space = %d, other = %d\n",
        nwhite, nother);
    return 0;
}