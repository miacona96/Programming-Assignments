/*******************************************************************************
 * Name        : Knapsack.cpp
 * Author      : Michael Iacona
 * Date        : 12/6/17
 * Description : Knapsack 
 * Pledge      : I pledge my honor that I have abided by the Stevens honor system
 ******************************************************************************/
#include <algorithm>
#include <vector>
#include <string>
#include <fstream>
#include <iostream>
#include <sstream>


using namespace std;

struct Item {
	unsigned int item_number, weight, value;
	string description;
	explicit Item(const unsigned int item_number,
				const unsigned int weight,
				const unsigned int value,
				const string &description) :
						item_number(item_number),
						weight(weight),
						value(value),
						description(description) { }
	friend std::ostream& operator<<(std::ostream& os, const Item &item) {
		os << "Item " << item.item_number << ": " << item.description
			<< " (" << item.weight
			<< (item.weight == 1 ? " pound" : " pounds") << ", $"
			<< item.value << ")";
		os.flush();
		return os;
	}
};

vector<Item> knapsack(vector<Item> items, const int capacity) {

	const int size = items.size();
	const int cap = capacity + 1;

	vector< vector<int> > vec1;
	vector< vector< pair< pair< int, int >, bool> > > vec2;

	vec1.resize( size, vector<int>( cap, 0) );
	vec2.resize( size, vector<pair<pair<int, int>, bool>>( cap, make_pair( make_pair(-1, -1), false )));

	for ( unsigned int i = 0; i < ( (unsigned int)size); i++) {
		for (unsigned int j = 1; j < ((unsigned int)capacity + 1); j++) {
			if ( i > 0 ) {
				if( j >= items[i].weight ){
					vec1[i][j] = max( (signed int)items[i].value + vec1[i - 1][j - items[i].weight], vec1[i - 1][j] );
				}
				if( !(j >= items[i].weight) ){
					vec1[i][j] = vec1[i - 1][j];
				}
				if( (j >= items[i].weight) && (signed int)items[i].value + vec1[i - 1][j - items[i].weight] >= vec1[i - 1][j] ){
					vec2[i][j] = make_pair( make_pair( i-1, j - items[i].weight ), ( j >= items[i].weight && (signed int)items[i].value + vec1[i - 1][j - items[i].weight] >= vec1[i - 1][j] ) ); 
				}
				else{
					vec2[i][j] = make_pair( make_pair( i-1, j ), ( j >= items[i].weight && (signed int)items[i].value + vec1[i - 1][j - items[i].weight] >= vec1[i - 1][j] ) );
				}
			}
			else {
				vec2[i][j] = make_pair(make_pair(-1, -1), (j >= items[i].weight));
			}
		}
	}
	vector<Item> out;
	int s = size - 1;
	int c = capacity;

	while(true) {
		pair<pair<int, int>, bool> curr = vec2[s][c];

		if ( curr.second ) {
			out.push_back(items[s]);
		}

		s = curr.first.first;
		c = curr.first.second;

		if ( s == -1 || c == -1 ){
			break;
		} 
	}

	return out;
}

vector<string> split( const string &str, char delim ) {
	vector<string> out;
	istringstream iss(str);
	string ss;
	
	while ( getline(iss, ss, delim) ){
		out.push_back( ss );
	}
	return out;
}

int main( int argc, char *argv[] ) {
	
	if ( argc != 3 ) {
		cerr << "Usage: ./knapsack <capacity> <filename>" << endl;
		return 1;
	}

	if ( atoi(argv[1]) < 0 ) {
		cerr << "Error: Bad value '" << argv[1] << "' for capacity." << endl;
		return 1;
	}

	if( argv[1] >= 0 ){
		istringstream in( argv[1] );
		int i;
		if( in >> i && in.eof() ){}
		else{
			cerr << "Error: Bad value '" << argv[1] << "' for capacity." << endl;
			return 1;
		}
	}

	ifstream file( argv[2] ); 
	if ( !file.is_open() ) {
		cerr << "Error: Cannot open file '" << argv[2] << "'." << endl;
		return 1;
	}

	string input;
	int line = 1;
	vector<Item> items;

	while ( getline( file, input ) ) {
		vector<string> elems = split( input, ',' ); 

		if ( elems.size() != 3 ) {                        
			cerr << "Error: Line number " << line << " does not contain 3 fields." << endl;
			return 1;
		}

		else if ( atoi( elems[1].c_str() ) <= 0 ) {       
			cerr << "Error: Invalid weight '" << elems[1] << "' on line number " << line << "." << endl;
			return 1;
		}

		else if ( atoi( elems[2].c_str() ) <= 0 ) {      
			cerr << "Error: Invalid value '" << elems[2] << "' on line number " << line << "." << endl;
			return 1;
		}

		Item item( line, atoi( elems[1].c_str() ), atoi( elems[2].c_str() ), elems[0] );
		items.push_back( item );
		line++;
	}

	int capacity = atoi(argv[1]);
	int sum = 0;
	int total = 0;
	vector<Item> sack = knapsack( items, capacity );
	reverse(sack.begin(), sack.end());

	cout << "Candidate items to place in knapsack:" << endl;

	for ( unsigned int i = 0; i < items.size(); ++i ){
		cout << "   " << items[i] << endl;
	}

	cout << "Capacity: " << capacity << ( capacity == 1 ? " pound" : " pounds" ) << endl;
	cout << "Items to place in knapsack:" << ( sack.empty() ? " None" : "" ) << endl;

	for ( unsigned int i = 0; i < sack.size(); ++i ) {
		cout << "   " << sack[i] << endl;
		total += sack[i].weight;
		sum += sack[i].value;
	}

	cout << "Total weight: " << total << " pound" << ( total == 1 ? "" : "s" ) << endl;
	cout << "Total value : $" << sum << endl;

	return 0;
}
