/*******************************************************************************
 * Name        : unique.cpp
 * Author      : Michael Iacona
 * Date        : 9/26/17
 * Description : Determining uniqueness of chars with int as bit vector.
 * Pledge      : I pledge my honor that I have abided by the Stevens honor system
 ******************************************************************************/
#include <iostream>
#include <cctype>

using namespace std;

bool is_all_lowercase( const string &s ) {
	// TODO: returns true if all characters in string are lowercase
	// letters in the English alphabet; false otherwise.

	for ( unsigned int i = 0; i < s.length(); i++ ){
		if (( isupper( s[i] ) || !isalpha( s[i] ) )){
			return false;
		}
	}
	 return true; 
}


bool all_unique_letters( const string &s ) {
    // TODO: returns true if all letters in string are unique, that is
    // no duplicates are found; false otherwise.
    // You may use only a single int for storage and work with bitwise
    // and bitshifting operators.
    // No credit will be given for other solutions.
    unsigned int val = 0;

	for( unsigned int i = 0; i < s.length(); i++ ){

		if(( (val) & ( 1 << (s[i] - 'a') )) > 0 ){
			return false;
		}
		val |= ( 1 << ( s[i] - 'a' ));
	}
	return true;
}

int main( int argc, char * const argv[] ) {
    // TODO: reads and parses command line arguments.
    // Calls other functions to produce correct output.

    if( argc != 2 ){
    	cout << "Usage: " << argv[0] << " <string>" << endl;
    	return 1;
    } 

    if( !all_unique_letters( argv[1] )){
		cerr << "Duplicate letters found." << endl;
		return 1;
	}

    if( !is_all_lowercase( argv[1] ) ){
    	cout << "Error: String must contain only lowercase letters." << endl;
    	return 1;
    }

    if( all_unique_letters( argv[1] )){
    	cout << "All letters are unique." << endl;
	}
	return 0;
}
