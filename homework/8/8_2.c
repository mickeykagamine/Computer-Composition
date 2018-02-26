#include<stdio.h>

int flag[120];
int graph[120][120]={0},weigh[120][120]={0},result[120][120]={0};
int N,E,i,j,id=0,vi=0,vj=0,weight=0,last=0,m=0,fin[120]={0};

void exchange(int *a,int *b){
    int temp=*a;
    *a=*b;
    *b=temp;
}

void sort(int ORG[120]){
    int i=0,j=0;
    while(j<120){
        for(i=0;i<119;i++){
            if(ORG[i]>ORG[i+1]&&ORG[i+1]!=0){
                exchange(&ORG[i], &ORG[i+1]);
            }
        }
        j++;
    }
}

int findmin(int G[][120],char p){
    int i=0,j=0,MIN=0,min=weight;
    if(p=='i'){
        while(i<100){
            if(flag[i]==1){
                j=0;
                while(j<100){
                    if(G[j][i]<=min&&G[j][i]!=0&&(flag[j]!=1&&flag[i]==1)){
                        min=G[j][i];
                        MIN=i;
                    }
                    j++;
                }
            }
            i++;
        }
    }else if(p=='j'){
        while(i<120){
            if(flag[i]){
                j=0;
                while(j<120){
                    if(G[j][i]<=min&&G[j][i]!=0&&(flag[j]!=1&&flag[i]==1)){
                        min=G[j][i];
                        MIN=j;
                    }
                    j++;
                }
                i++;
            }
        }
    }
    return MIN;
}

void ZERO(){
    int i;
    for(i=0;i<100;i++){
        flag[i]=0;
    }
}


void input(int E){
    int i=0;
    while(i<E){
        scanf("%d%d%d%d",&id,&vi,&vj,&weight);
        graph[vj][vi]=id;
        graph[vi][vj]=id;
        weigh[vj][vi]=weight;
        weigh[vi][vj]=weight;
        i++;
    }
}




int main(){
    int i1,j1;
    ZERO();
    scanf("%d%d",&N,&E);
    input(E);
    flag[vi]++;
    for(i=0;i<N-1;i++){
        i1=findmin(weigh,'i');
        j1=findmin(weigh,'j');
        result[j1][i1]=weigh[j1][i1];
        weigh[j1][i1]=0;
        weigh[i1][j1]=0;
        if(flag[i1]==0){
            flag[i1]++;
        }
        if(flag[j1]==0){
            flag[j1]++;
        }
    }
    for(i=0;i<120;i++){
        for(j=0;j<120;j++){
            if(result[i][j]!=0){
                last=last+result[i][j];
                fin[m++]=graph[i][j];
            }
        }
    }
    printf("%d\n",last);
    sort(fin);
    for(i=0;i<120&&fin[i]!=0;i++){
        printf("%d ",fin[i]);
    }
}

