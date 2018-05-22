//Michael Iacona 
//I pledge my honor that I have abided by the Steven's honor system
//10/23/17
#include <iostream>
#include <sstream>

using namespace std;

unsigned long long russian_peasant_multiplication(
    unsigned long long m, unsigned long long n) {

    unsigned long long ans = 0;

    if ( m == 0 || n == 0 ) {
        return 0;
    }

    if ( m < n ){
        swap(m, n);
    }

    while ( m != 0 ) {
        if ( !(m % 2 == 0) ) {
            ans += n;
        }
        m = m >> 1;
        n = n << 1;
    }

    return ans;
}

int main(int argc, char const *argv[]) {

    unsigned long long m = 0;
    unsigned long long n = 0;
    istringstream iss;

    if (argc != 3) {
        cerr << "Usage: ./rpmult <integer m> <integer n>" << endl;
        return 1;
    }

    iss.str(argv[1]);
    if ( !(iss >> m) || m >= 2147483648 ) {
        cerr << "Error: The first argument is not a valid nonnegative integer." << endl;
        return 1;
    }
    iss.clear(); 

    iss.str(argv[2]);
    if ( !(iss >> n) || n >= 2147483648 ) {
        cerr << "Error: The second argument is not a valid nonnegative integer." << endl;
        return 1;
    }
    iss.clear();

    cout << m << " x " << n << " = " << russian_peasant_multiplication( m, n ) << endl;
    return 0;
}