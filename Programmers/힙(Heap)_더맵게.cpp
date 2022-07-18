#include <string>
#include <vector>
#include <queue>

using namespace std;
int solution(vector<int> scoville, int K) {
    int answer = 0;
    
    priority_queue<int, vector<int>, greater<int>> pq;
    for(int s: scoville){
        pq.push(s);
    }
    
    int pq_size = pq.size();
    int flag = 0;  //기본 값
    while(pq_size > 0){
        if(flag == 0 && pq.size() < 2){
            flag = 4; //불가능
            break;
        }
        if(pq.top() >= K) {
            flag = 3;  //정상 종료
            break;
        }
        if(pq.size() >= 2){
            int a = pq.top();
            pq.pop();
            int b = pq.top();
            pq.pop();
            pq.push(a+b*2);
            answer += 1;
            flag = 1; //진행중
        }
        pq_size--;
        
    }
    answer = flag==3 ? answer : -1;
    
    return answer;
}