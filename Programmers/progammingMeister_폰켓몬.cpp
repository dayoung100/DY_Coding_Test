#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<int> nums)
{
    int answer = 0;
    
    int n = nums.size() / 2;
    sort(nums.begin(), nums.end());
    
    int cnt = 0;
    int pivot = 0;
    
    for(int n : nums){
        if(pivot != n){
            cnt++;
            pivot = n;
        }
    }
    
    answer = cnt > n ? n : cnt;
    
    return answer;
}