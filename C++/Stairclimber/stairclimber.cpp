/*******************************************************************************
* Name        : Michael Iacona
* Author      :
* Date        : 10/3/16
* Description : Lists the number of ways to climb n stairs.
* Pledge      : i pledge my honor that i abided by the stevens honor system
******************************************************************************/
#include <iostream>
#include <vector>
#include <algorithm>
#include <sstream>
#include <iomanip>
#include <string>

using namespace std;

vector< vector<int> > get_ways( int num_stairs ) {

	// TODO: Return a vector of vectors of ints representing
	// the different combinations of ways to climb num_stairs
	// stairs, moving up either 1, 2, or 3 stairs at a time.

	if ( num_stairs == 1 ) {
       
        vector <int> in;
		vector <vector <int> > out;
        
		in.push_back( 1 );
        out.push_back( in );
        
		return out;
    }
    if ( num_stairs == 2 ) {
		
		vector <int> in1;
		vector <int> in2;
		vector <vector <int> > out;
        
		in1.push_back( 1 );
        in1.push_back( 1 );
        out.push_back( in1 );
        in2.push_back( 2 );
        out.push_back( in2 );
        
		return out;
    }
    if ( num_stairs == 3 ) {
        
		vector <int> in1, in2, in3, in4;
		vector <vector <int> > out;
        
        in1.push_back( 1 );
        in1.push_back( 1 );
        in1.push_back( 1 );
        
		out.push_back( in1 );
        
		in2.push_back( 1 );
        in2.push_back( 2 );
        
		out.push_back( in2 );
        
		in3.push_back( 2 );
        in3.push_back( 1 );

        out.push_back( in3 );
        in4.push_back( 3 );
        out.push_back( in4 );
        
		return out;
    }
    
	vector< vector<int> > path1;
	path1 = get_ways( num_stairs - 1 );

    for( std::vector<int>::size_type i = 0; i != path1.size(); i++ ) {
        path1[i].insert( path1[i].begin(), 1 );
    }
    
	vector< vector <int> > path2;
	path2 = get_ways( num_stairs - 2 );

    for( std::vector<int>::size_type i = 0; i != path2.size(); i++ ) {
        path2[i].insert( path2[i].begin(), 2 );
    }
    
    vector< vector<int> > path3;
	path3 = get_ways( num_stairs - 3 );

    for( std::vector<int>::size_type i = 0; i != path3.size(); i++ ) {
        path3[i].insert( path3[i].begin(), 3 );
    }
    
    vector< vector<int> > combination;
    
    combination.reserve( path1.size() + path2.size() + path3.size() );
    
	combination.insert( combination.end(), path1.begin(), path1.end() );
    combination.insert( combination.end(), path2.begin(), path2.end() );
    combination.insert( combination.end(), path3.begin(), path3.end() );
    
	return combination;
}

void display_ways( const vector< vector<int> > &ways ) {
    
	// TODO: Display the ways to climb stairs by iterating over
    // the vector of vectors and printing each combination.
    
	int counter = 1;
    
	for( vector<int>::size_type i = 0; i != ways.size(); i++ ) {
        
		vector<int> elem = ways[i];
        
		if ( ways.size() >= 10 ) {
            cout << setw(2) << counter++ << ". " << "[";
        } 

		else {
            cout << setw(1) <<counter++ << ". " << "[";
        }
        
		for( vector<int>::size_type i = 0; i != elem.size(); i++ ) {
            if ( i == 0 ) {
                cout << elem[i];
            } 
			else {
                cout << ", " << elem[i];
            }
        }
        
		cout << "]" << endl;
    
	}
}

int main( int argc, char * const argv[] ) {
   
	if ( argc != 2 ) {
        cerr << "Usage: " << argv[0] << " <number of stairs>" << endl;
        return 1;
    }

    istringstream iss_arg1;
    iss_arg1.str( argv[1] );
    int arg1;
    
    if ( !( iss_arg1 >> arg1 ) ) {
        cerr << "Error: Number of stairs must be a positive integer." << endl;
        return 1;
    }
    
    if ( arg1 <= 0 ) {
        cerr << "Error: Number of stairs must be a positive integer." << endl;
        return 1;
    }
    
    vector< vector<int> > combination = get_ways( arg1 );
   
	std::string ways = combination.size() == 1 ? " way" : " ways";
    std::string stairs = combination.size() == 1 ? " stair." : " stairs.";
    
	cout << combination.size() << ways << " to climb " << arg1 << stairs << endl;
    
	display_ways( combination );
}

