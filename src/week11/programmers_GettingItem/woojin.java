package week11.programmers_GettingItem;

import java.util.Arrays;

import java.util.LinkedList;
import java.util.Queue;



class Solution {
    static int graph[][];
	static int visit[][];
	static class go{
		int x,y,z;

		public go(int x, int y,int z) {
			super();
			this.x = x;
			this.y = y;
			this.z=z;
		}
		
	}
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        graph=new int [102][102];
		visit=new int [102][102];
		int len=rectangle.length;
		for(int i=0;i<len;i++)
		{
			int [] temp=rectangle[i];
			int sx,sy,fx,fy;
			sx=temp[0]*2;
			sy=temp[1]*2;
			fx=temp[2]*2;
			fy=temp[3]*2;
			for(int k=sy;k<=fy;k++)
			{
				for(int j=sx;j<=fx;j++)
				{
                    if(k>sy&&k<fy&&j>sx&&j<fx)
                        graph[j][k]=4;
					else 
                        if(graph[j][k]==0)
						graph[j][k]=1;
				} 
				
			}

		}
		
		int dx[]= {-1,0,1,0};
		int dy[]= {0,1,0,-1};
		int sx=characterX*2; 
		int sy= characterY*2;
		int fx=itemX*2;
		int fy=itemY*2;
		
		
		visit[sx][sy]=1;
		Queue<go> q=new LinkedList<>();
		q.add(new go(sx,sy,1));
		while(!q.isEmpty())
		{
			go a=q.poll();
			for(int i=0;i<4;i++)
			{
				int zx=a.x+dx[i];
				int zy=a.y+dy[i];
				if(0<=zx&&zx<102&&0<=zy&&zy<102)
				{
					if(visit[zx][zy]==0&&graph[zx][zy]==1)
					{
						if(zx==fx&&zy==fy)
						{
							System.out.println((a.z+1)/2);
							return (a.z+1)/2;
						}
						
						q.add(new go(zx,zy,a.z+1));
						visit[zx][zy]=1;
					}
				}
				
			}
			
			
		}
		return answer;

    }
}