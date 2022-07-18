#include <string>
#include <vector>

using namespace std;

int solution(vector<vector<int>> board, vector<int> moves) {
    int answer = 0;
    
    vector<int> stack;
    
    vector<vector<int>> board2;
    for(int i=0; i<board.size(); i++){
        vector<int> col;
        for(int j=0; j<board.size(); j++){
            col.push_back(board[j][i]);
        }
        board2.push_back(col);
    }
    
    for(int m=0; m<moves.size(); m++){
        int idx = moves[m]-1;
        for(int n=0; n<board2[idx].size(); n++){
            if(board2[idx][n]!=0){
                if(stack.size() > 0 && board2[idx][n] == stack.back()){
                    stack.pop_back();
                    answer += 2;
                }
                else{
                    stack.push_back(board2[idx][n]);
                }
                board2[idx][n] = 0;
                break;
            }
        }
    }
    
    return answer;
}