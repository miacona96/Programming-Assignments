//Michael Iacona
//2/11/18

function simplify(text) {
    if (typeof text !== 'string'){
        throw TypeError('Error: input must be a string');
    }
    return text.toLowerCase().replace(/[0-9]/g, '').replace(/([^\w\d\s])/g, '').replace(/\s+/g, ' ').trim();
}

function createMetrics(text) {
    
    const T = simplify(text);
    const wordList = T.split(' ');
    const totalLetters = T.replace(/\s/g,'').length;
    const totalWords = wordList.length;
    const longWords = wordList.filter(word => word.length > 5).length;
    
    const averageWordLength = totalLetters / totalWords;

    var wordOccur = [];
    var unique = function(wordList){
        return wordList.filter(function(x,i){
            return wordList.indexOf(x) == i
        })
    }
    const uniqueWords = unique(wordList).length;

    var occur = [];
    var out = {};

    for (var i = 0; i < wordList.length; i++) {
        occur.push(1);
        if (!uniqueWords[i]) {
            for (var j = i + 1; j < wordList.length; j++) {
                if (wordList[i] == wordList[j]) {
                occur[i]++;
                wordList.splice(j, 1);
                j--;
            }
        } out[wordList[i]] = occur[i];
    }
    else {
        occur[i] = 1;
        out[wordList[i]] = 1;
    }
}
    const wordOccurrences = out;
   
    
    return {totalLetters, //total number of letter characters in the simplified text,
            totalWords, //total number of words in the simplified text,
            uniqueWords, //total number of unique words that appear in the simplified text,
            longWords, //number of words in the text that are 6 or more letters;(individual words)
            averageWordLength, //Avhg number of letters in a word from the text,(individual words)
            wordOccurrences //A dict of each word and how many times each eappears in the text
        };
}

module.exports = {
    simplify,
    createMetrics
};