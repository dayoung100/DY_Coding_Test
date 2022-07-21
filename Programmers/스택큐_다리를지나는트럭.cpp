#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(int bridge_length, int weight, vector<int> truck_weights) {
    int time = 0; //걸리는 시간
    int idx = 0; //트럭배열의 인덱스
    
    int t_num = 0; //다리에 올라간 트럭 수
    queue<int> weight_q; //다리에 올라간 트럭 무게
    int weight_i = 0; //다리에 올라간 트럭 무게 합
    
    //모든 트럭들이 다리에 올라타는 시간
    while(idx < truck_weights.size()){
        //무게, 길이 조건 충족 시 트럭 올리기
        if(weight_i + truck_weights[idx] <= weight && t_num+1 <= bridge_length){
            t_num++; //트럭수 up
            weight_q.push(truck_weights[idx]); //트럭 무게 푸시
            weight_i += truck_weights[idx]; //트럭 무게합 계산
            idx++; //인덱스 변경
        }
        //무게, 길이 조건 충족 x
        else{
            weight_q.push(0);
        }
        //weight와 num 갱신
        if(weight_q.size() >= bridge_length){
            int w = weight_q.front();
            weight_q.pop();
            weight_i -= w;
            t_num--;
        }
        time++;
        
    }
    
    //마지막 트럭이 빠져나오는 시간
    time += bridge_length;
    
    return time;
}