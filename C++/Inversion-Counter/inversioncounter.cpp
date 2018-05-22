/*******************************************************************************
 * Name        : inversioncounter.cpp
 * Author      : Michael Iacona
 * Version     : 1.0
 * Date        :11/3/2017
 * Description : Counts the number of inversions in an array.
 * Pledge      : I pledge my honor that I have abided by the Stevens honor system
 ******************************************************************************/
#include <iostream>
#include <algorithm>
#include <sstream>
#include <vector>
#include <cstdio>
#include <cctype>
#include <cstring>

using namespace std;

// Function prototype.
static long mergesort(int array[], int scratch[], int low, int high);

/**
 * Counts the number of inversions in an array in Θ(n2) time.
 */
long count_inversions_slow(int array[], int length) {
    
    long count = 0;
    for ( int i = 0; i < length; ++i ) {
        for ( int j = i; j < length; ++j ) {   
            if ( array[i] > array[j] ) {
                ++count;
            }
        }
    }

    return count;
}

/**
 * Counts the number of inversions in an array in Θ(n lg n) time.
 */
long count_inversions_fast( int array[], int length ) {
	int *i = new int[length];
	long count = mergesort( array, i, 0, length - 1 );
	delete[] i;
	return count;
}

static long mergesort( int array[], int scratch[], int low, int high ) {
    // TODO

    long count = 0;

    if ( low < high ) {
        int mid = ( low + high ) / 2;
        int Low = low;
        int High = mid + 1;
        count += mergesort( array, scratch, low, mid );
        count += mergesort( array, scratch, High, high );

        for ( int i = low; i <= high; ++i ) {
            if ( Low <= mid &&  ( High > high || array[Low] <= array[High] )) {
                scratch[i] = array[Low++];
            }

            else {
                scratch[i] = array[High++];
                count += (mid - Low + 1);
            }
        }

        for ( int i = low; i <= high; ++i ) {
            array[i] = scratch[i];
        }

        return count;
    }

    return 0;
}

int main(int argc, char *argv[]) {
    // TODO: parse command-line argument
    //check for errors and handle inputs

    if (argc < 1 || argc > 2) {
        cerr << "Usage: " << argv[0] << " [slow]" << endl;
        return 1;
    }
    if ( argc != 1 && strcmp( argv[1], "slow" ) != 0 ) {
        cerr << "Error: Unrecognized option '" << argv[1] << "'." << endl;
        return 1;
    }

    cout << "Enter sequence of integers, each followed by a space: " << flush;

    istringstream iss;
    int value, index = 0;
    vector<int> values;
    string str;
    str.reserve(11);
    char c;
    while (true) {
        c = getchar();
        const bool eoln = c == '\r' || c == '\n';
        if (isspace(c) || eoln) {
            if (str.length() > 0) {
                iss.str(str);
                if (iss >> value) {
                    values.push_back(value);
                } else {
                    cerr << "Error: Non-integer value '" << str
                         << "' received at index " << index << "." << endl;
                    return 1;
                }
                iss.clear();
                ++index;
            }
            if (eoln) {
                break;
            }
            str.clear();
        } else {
            str += c;
        }
    }

    // TODO: produce output
    if ( values.empty() ) {
        cerr << "Error: Sequence of integers not received." << endl;
        return 1;
    }
    if ( argc == 2 && strcmp(argv[1], "slow") ) {
		cout << "Number of inversions: " << count_inversions_slow( &values[0], values.size() ) << endl;
        return 0;
	}
	else {
        cout << "Number of inversions: " << count_inversions_fast(&values[0], values.size()) << endl;
        return 0;
    }
    return 0;
}
