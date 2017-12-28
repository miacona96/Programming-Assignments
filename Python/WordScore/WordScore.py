#Michael Iacona
#I pledge my honor that i have abided by the Steven's honor code
#9/23/15
#Homework 2
from cs import *
import sys
from bigdict import *
 
sys.setrecursionlimit(10000)


scrabbleScores = [ ["a", 1], ["b", 3], ["c", 3], ["d", 2], ["e", 1], ["f", 4], ["g", 2], ["h", 4], ["i", 1], ["j", 8], ["k", 5], ["l", 1], ["m", 3], ["n", 1], ["o", 1], ["p", 3], ["q", 10], ["r", 1], ["s", 1], ["t", 1], ["u", 1], ["v", 4], ["w", 4], ["x", 8], ["y", 4], ["z", 10] ]

def letterScore( letter, score):
    """checks score value of given lower case letter"""
    if (score[0][0] == letter):
        return score[0][1]
    else:
        return letterScore(letter, score[1:])

def wordScore(S, score):
    """gives score of given word"""
    if S=='':
        return 0
    return letterScore(S[0],score) + wordScore(S[1:], score)

Dictionary = ["a", "am", "at", "apple", "bat", "bar", "babble", "can", "foo", "spam", "spammy", "zzyzva"]
    

def listPosition(letter,List):
    """Gives index of letter in list"""
    if letter == List[0]:
        return 0
    return 1+ listPosition(letter , List[1:])

def wordCheck(word, Rack):
    """checks if word can be made from list of letters"""
    if Rack==[]:
        return False
    if word=='':
        return True
    if word[0] in Rack:
        return wordCheck(word[1:],(Rack[0:listPosition(word[0],Rack)] + Rack[listPosition(word[0],Rack):]))
    else:
        return False

def createList(L):
    """Builds list containing words and their corresponding scores from a list of words"""
    if(L==[]):
        return[]
    return [[L[0], wordScore( L[0], scrabbleScores)]] + createList(L[1:])

def scoreList(Rack):
    """gives every possible word and its score from a list (Rack)"""
    return createList( list( filter( ( lambda w: wordCheck(w, Rack)), Dictionary)))

def myMax( x, y ):
    '''Takes two lists and returns the list with the bigger second value'''
    if(max(x[1],y[1])==x[1]):
        return x
    else:
        return y

def bestWord(Rack):
    """Takes a list of letters and returns the word with the highest value"""
    return reduce( myMax,scoreList(Rack))
