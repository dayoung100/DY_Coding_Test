function solution(s) {
  // var answer = '';
  // s.split(' ').forEach((word) => {
  //     let firstChar = word.charAt(0);
  //     if(isNaN(firstChar)) answer += firstChar.toUpperCase() + word.substr(1).toLowerCase();
  //     else answer += word.toLowerCase();
  //     answer += " ";
  // })
  // return answer.substr(0, answer.length-1);
  return s
    .split(" ")
    .map(word => word.charAt(0).toUpperCase() + word.substr(1).toLowerCase())
    .join(" ");
}
