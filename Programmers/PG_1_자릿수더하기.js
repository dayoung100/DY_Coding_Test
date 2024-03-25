function solution(n) {
  // var str = n.toString();
  // var sum = 0;
  // for(let i=0; i<str.length; i++) sum += str.charAt(i)-0;
  // return sum;
  return n
    .toString()
    .split("")
    .reduce((acc, curr) => acc + parseInt(curr), 0);
}
