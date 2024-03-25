function solution(x, n) {
  var answer = Array.from({ length: n }, (value, index) => x + x * index);
  return answer;
}
