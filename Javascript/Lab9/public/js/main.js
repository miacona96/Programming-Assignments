function isPalindrome(text) {
    if (typeof text !== 'string') {
        throw "Input must be a string";
    }
    else {
        if (text.toLowerCase().replace(/[^\w]|_/g, "") === 
            text.replace(/[^\w]|_/g, "").toLowerCase().
            split('').reverse().join('')) {
            return true;
        }
        else {
            return false;
        }
    }
}

document.addEventListener('submit', function(e)
{
    e.preventDefault();
    var input = document.getElementById("phrase").value;

    if (input){
        var elem = document.createElement("li");
        if(isPalindrome(input)){
            elem.className = "is-palindrome";
        }
        else{
            elem.className = "not-palindrome";
        }
        elem.appendChild(document.createTextNode(input));
        document.getElementById("attempts").appendChild(elem);
    } 
    else {
        alert("Please provide a phrase or word to check.");
    }
    
    document.getElementById('phrase').value = "";
}); 
