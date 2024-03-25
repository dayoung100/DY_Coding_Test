function solution(n) {
  var answer = 1;

  for (let i = 1; i <= n / 2; i++) {
    let sum = 0;
    let cnt = 0;
    while (sum < n) {
      sum += i + cnt;
      cnt++;
    }
    if (sum == n) answer++;
  }
  return answer;
}
