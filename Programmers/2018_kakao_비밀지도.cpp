#include <string>
#include <vector>

using namespace std;

vector<string> solution(int n, vector<int> arr1, vector<int> arr2) {
    vector<string> answer;
    
    vector<string> ar1;
    vector<string> ar2;
    
    for(int a=0; a<n; a++){
        int num1 = arr1[a];
        int num2 = arr2[a];
        string result1 = "";
        string result2 = "";
        for(int i=0; i<n; i++){
            result1 = to_string(num1%2) + result1;
            result2 = to_string(num2%2) + result2;
            num1 /= 2;
            num2 /= 2;
        }
        ar1.push_back(result1);
        ar2.push_back(result2);
    }
   
    for(int x=0; x<n; x++){
        string result = "";
        for(int y=0; y<n; y++){
            if(ar1[x][y]=='0' && ar2[x][y]=='0'){
                result += " ";
            }
            else{
                result += "#";
            }
        }
        answer.push_back(result);
    }
    
    
    return answer;
}