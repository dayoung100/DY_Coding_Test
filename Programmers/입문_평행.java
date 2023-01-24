class Solution {
    public int solution(int[][] dots) {
        int x1 = dots[3][0];
        int y1 = dots[3][1];
        
        for(int i=0; i<3; i++){
       
            if((double)Math.abs(x1-dots[i][0]) / Math.abs(y1-dots[i][1])
               == (double)Math.abs(dots[(i+1)%3][0] - dots[(i+2)%3][0])
               / Math.abs(dots[(i+1)%3][1] - dots[(i+2)%3][1])) 
            	return 1;
        }
        
        return 0;
    }
}