/********************************
Michael Iacona
Lab 1
1/25/18
*********************************/

const questionOne = function questionOne(arr) {
    // Implement question 1 here
    var result = 0;
    var i;
    for(var i = 0; i < arr.length; i++){
        result += arr[i]*arr[i]; 
    }
    return result;
}

const questionTwo = function questionTwo(num) { 
    // Implement question 2 here
    var x = 1, y = 0, temp;

    while (num > 0){
        temp = x;
        x = x + y;
        y = temp;
        num --;
    }
    return y;
}

const questionThree = function questionThree(text) {
    // Implement question 3 here
    var count = text.match(/[aeiou]/gi);
    if( count == null){
        return 0;
    }
    else{
        return count.length;
    }
}

const questionFour = function questionFour(num) {
    // Implement question 4 here
    var f = [];
    if( num < 0){
        return "NaN";
    }
    if (num == 0 || num == 1){
        return 1;
    }
    if(f[num] > 0){
        return f[num];
    }
    return f[num] = questionFour(num-1) * num;
}

module.exports = {
    firstName: "Michael", 
    lastName: "Iacona", 
    studentId: "10407612",
    questionOne,
    questionTwo,
    questionThree,
    questionFour
};