/*******************************************************************************
 * Name        : sieve.cpp
 * Author      : Michal Iacona
 * Date        : 9/17/2017
 * Description : Sieve of Eratosthenes
 * Pledge      : I pledge my honor that I have abided by the Stevens honor system
 ******************************************************************************/
#include <cmath>
#include <iomanip>
#include <iostream>
#include <sstream>


using namespace std;

class PrimesSieve {

public:

    PrimesSieve( int limit ) : 

    is_prime_( new bool[limit + 1] ), 
    limit_( limit ) {
        
        for( int i=0; i <= 1; i++ ){
            is_prime_[i] = false;
        }

        for( int i=2; i <= limit_; i++ ){  
            is_prime_[i] = true; 
        }

        sieve();

        for ( int i = 0; i <= limit_; ++i ) { 
            if ( is_prime_[i] ) 
                max_prime_ = i; 
        }

            num_primes_ = count_num_primes();
    }

    ~PrimesSieve() {
        delete [] is_prime_;
    }

    inline int num_primes() const {
        return num_primes_;
    }

    void display_primes() const {
        // TODO: write code to display the primes in the format specified in the
        // requirements document.

        const int max_prime_width = num_digits( max_prime_ ),
        primes_per_row = 80 / ( max_prime_width + 1 );

        cout << endl << "Number of primes found: " << num_primes_ << endl;
        cout << "Primes up to " << limit_ << ":" << endl;

        int r = 0;
        int counter = 0;

        for( int i = 0; i <= limit_; i++ ){
            
            if( is_prime_[i] ) {
                
                if( max_prime_width == 2 ) {
                    counter++;
                   
                    if( counter < num_primes_ ) {
                        cout << i << " ";
                    }
                    
                    else{
                        cout << i;
                    }
                }
                
                else if( counter == 0 ) {
                    cout << setw( max_prime_width ) << i;
                    counter++;
                }
                
                else if( counter < primes_per_row ) {
                    cout << setw( max_prime_width + 1 ) << i;
                    counter++;
                }
                
                else{
                    cout << endl << setw( max_prime_width ) << i;
                    counter = 1;
                    r++;
                }
            }
        }
    }

private:

    bool * const is_prime_;
    const int limit_;
    int num_primes_, max_prime_;

    int count_num_primes() const {

        // counts the number of primes found

        int count = 0;

        for ( int i = 0; i <= limit_; ++i ){
            if ( is_prime_[i] ){
                count++;
            }

        }

        return count;

    }

    int num_digits( int num ) const {

        // TODO: write code to determine how many digits are in an integer
        // Hint: No strings are needed. Keep dividing by 10

        int i = 1;

        while( num >= 10 ){
            num = num / 10;
            ++i;
        }

        return i;

    }

    void sieve() {
        //The sieve algorithm
        for ( unsigned int i = 2; i <= sqrt(limit_); ++i ) {
            if ( is_prime_[i] ) {
                for ( int j = i * i; j <= limit_; j = j + i )
                    is_prime_[j] = false;
               }
           }
       }
   };

int main( void ) {

    cout << "**************************** " <<  "Sieve of Eratosthenes" <<
            " ****************************" << endl;
    cout << "Search for primes up to: ";
    string limit_str;
    cin >> limit_str;
    int limit;

    // Use stringstream for conversion. Don't forget to #include <sstream>
    istringstream iss( limit_str );

    // Check for error.
    if ( !(iss >> limit) ) {
        cerr << "Error: Input is not an integer." << endl;
        return 1;
    }
    if ( limit < 2 ) {
        cerr << "Error: Input must be an integer >= 2." << endl;
        return 1;
    }

    // TODO: write code that uses your class to produce the desired output.

    PrimesSieve sieve( limit );
    sieve.display_primes();

    return 0;
}


