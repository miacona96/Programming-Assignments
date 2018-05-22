byte nr_leaders = 0;  /* number of leaders */

proctype Node( chan channel_in, channel_out ) {
    
    byte token;
    
    channel_out ! _pid;
    
    end:

    do
    ::  channel_in ? token ->
            if
            ::  token <  _pid -> 
                    skip                  
            ::  token >  _pid -> 
                    channel_out ! token       
               
            ::  token == _pid -> 
                    nr_leaders = nr_leaders + 1;  
                    break
            fi
    od
}

init {
    
    bool Flag[5] = true;
    
    chan a = [1] of { byte };
    chan b = [1] of { byte };
    chan c = [1] of { byte };
    chan d = [1] of { byte };
    chan e = [1] of { byte };
    
    
    do
    ::  Flag[0] -> 
                atomic { run Node( e, a ); Flag[0] = false }
    ::  Flag[1] -> 
                atomic { run Node( a, b ); Flag[1] = false }
    ::  Flag[2] -> 
                atomic { run Node( b, c ); Flag[2] = false }
    ::  Flag[3] -> 
                atomic { run Node( c, d ); Flag[3] = false }
    ::  Flag[4] -> 
                atomic { run Node( d, e ); Flag[4] = false }
    ::  else -> 
                break
    od
}