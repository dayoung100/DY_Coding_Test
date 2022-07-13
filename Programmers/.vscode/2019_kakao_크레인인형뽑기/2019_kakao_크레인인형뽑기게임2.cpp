#include <string>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> board, vector<int> moves) {
    int answer = 0;
    
    stack<int> basket;
    
    vector<vector<int>> board2;
    for(int i=0; i<board.size(); i++){
        vector<int> col;
        for(int j=0; j<board.size(); j++){
            col.push_back(board[j][i]);
        }
        reverse(col.begin(), col.end());
        board2.push_back(col);
    }
    
    for(int m=0; m<moves.size(); m++){
        int idx = moves[m]-1;
        while(!board2[idx].empty()){
            int x = board2[idx].back();
            board2[idx].pop_back();
            if(x == 0) continue;
            if(basket.empty()) basket.push(x);
            else{
                if(basket.top() == x){
                    basket.pop();
                    answer += 2;
                }
                else basket.push(x);
            }
            break;
        }
    }
    
    return answer;
}