#include <stdio.h>

/* count digits, white space, others */
/* to run, try:    gcc THIS_FILE.c ; cat ANY_FILE | ./a.out */

main()
{
    int i, j, nwhite, nother;
    int ndigit[10];
	j = 0;
    nwhite = nother = 0;
    char x1[100] = 
    "The 25 quick brown foxes jumped over the 27 lazy dogs 17 times.";
    
    for (i = 0; i < 10; ++i)
        ndigit[i] = 0;
        
    while (x1[j] != '\0') {
        if (x1[j] >= '0' && x1[j] <= '9') {
        	++ndigit[x1[j]-'0'];
        }  
        else if (x1[j] == ' ' || x1[j] == '\n' || x1[j] == '\t') {
    		++nwhite;
        }
        else {
        	++nother;
        }
            
        j++;
    }
    
    printf("digits =");
    for (i = 0; i < 10; ++i)
        printf(" %d", ndigit[i]);
    printf(", white space = %d, other = %d\n",
        nwhite, nother);
    return 0;
}