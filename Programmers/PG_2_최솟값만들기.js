function solution(A, B) {
  // A.sort();
  // B.sort().reverse();
  A.sort((a, b) => a - b);
  B.sort((a, b) => b - a);
  // var sum = 0;
  // for(let i=0; i<A.length; i++) sum+=A[i]*B[i];
  // return sum;
  return A.reduce((acc, a, index) => acc + a * B[index], 0);
}
