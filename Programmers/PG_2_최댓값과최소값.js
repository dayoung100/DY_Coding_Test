function solution(s) {
  var max = Number.MIN_SAFE_INTEGER;
  var min = Number.MAX_SAFE_INTEGER;
  s.split(" ").forEach(item => {
    max = Math.max(max, item);
    min = Math.min(min, item);
  });
  answer = min + " " + max;
  return answer;
}
