#include <ctype.h>
#include <iostream>
#include <stdio.h>
#include <algorithm>
#include <sstream>
using namespace std;
//I pledge my honor that i have abided by the Steven's Honor system - MI


int gcdRecur(int a, int b) {
    if(a == 0 || b == 0) return a == 0 ? b : a;

    int R = a%b;
    if(R == 0) {
    	return b;
    }
    else {
    	return gcdRecur(b, R);
    }
}





int gcdLoop(int a, int b) {
	if(a == 0 || b == 0) return a == 0 ? b : a;

    int R = 1;
    while(true) {
        R = a%b;
        a = b;
        if(R == 0) {
        	break;
        }
        b = R;
    }
    return b;
}



int main(int argc, char *argv[]) {
    if(argc != 3) {
        cerr << "Usage: ./gcd <integer m> <integer n>" << endl;
        return 1;
    }
    else {
        bool isnumber1 = true;
        bool isnumber2 = true;

        for(unsigned int n = 0; n < sizeof(*argv[1]); ++n) {
        	if(!isdigit(argv[1][n]))
        		isnumber1 = false;

        }
        for(unsigned int n = 0; n < sizeof(*argv[2]); ++n) {
        	if(!isdigit(argv[2][n]))
        		isnumber2 = false;
        }

        if(isnumber1 == false) { cerr << "Error: The first argument is not a valid integer." << endl; }
        else if(isnumber2 == false) { cerr << "Error: The second argument is not a valid integer." << endl; }
        else {
            unsigned int a;
            unsigned int b;
            stringstream s1;
            stringstream s2;

            s1 << argv[1]; s1 >> a;
            s2 << argv[2]; s2 >> b;

            cout << "Iterative: gcd(" << a << ", " << b << ") = " << gcdLoop(a, b) << "\nRecursive: gcd(" << a << ", " << b << ") = " << gcdRecur(a, b) << endl;
        }
    }
    return 0;
}
