#include <stdio.h>
void main( )
{
    int x, i=2;
    scanf("%d", &x);
    while(x>1)
    {
        if(x%i == 0)
        {
            printf("%d ",i);
            x=x/i;
        }
        else i++;
    }
}

