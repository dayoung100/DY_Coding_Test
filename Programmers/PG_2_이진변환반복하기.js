function solution(s) {
  var answer = [];
  let cnt = 0;
  let deletedZero = 0;
  let arr = [];
  let str = s;
  while (str != "1") {
    arr = str.split("");
    //0제거하기
    let newArr = arr.filter(char => {
      if (char == "0") {
        deletedZero++;
        return false;
      } else return true;
    });
    //길이 구하기
    let c = newArr.length;
    //길이를 2진법으로 표현하기
    str = c.toString(2);
    cnt++;
  }

  answer = [cnt, deletedZero];
  return answer;
}
