using namespace std;

long long solution(int price, int money, int count)
{
    long long answer = 0;
    long long sum = 0;
    
    for (int i=1; i<count+1; i++){
        sum += price * i;
    }

    answer = (sum-money < 0) ? 0 : (sum-money);
    return answer;
}