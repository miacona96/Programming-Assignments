#Michael Iaocna
#I pledge my honor that I have abided by the Stevens's honor system
#12/11/15

def hostGame(self):
        """Creates interface """
        turn = False
        while True:
            print(self)
            print("")
            
            Input = input( ("O" if turn else "X") + "'s choice: " )
            if not Input.isdigit():
                print( "Must be a number in range fam\n" )
                continue
            Input = int(Input)
            if not self.Allow(Input ):
                print( "Piece cannot be placed there fam\n" )
                continue
                       
            self.addMove( Inpput, "O" if turn else "X" )
            if self.checkWin( "O" if turn else "X" ):
                print(("O" if turn else "X"), "Winner!")
                print(self)
                break
            
            turn = not turn
            print("")
            
class Board:
    def __init__( self, width=7, height=6 ):
        self.height = height
        self.width = width
        self.board = [[]]
        for i in range(height):
            self.board += [[]]
            for j in range(width):
                self.board[i] += [""]

    def __String__( self ):
        String = ""
        for i in range(self.height):
            String += "|"
            for j in range( self.width ):
                String += self.board[i][j]+"|"
            
        String += "\n"
        String += "-" * (self.width * 2 + 1) + "\n"
        String += " " + " ".join(map(lambda x : str(x), list(range(self.width)))) 
        return String
    
    def Allow( self, column ):
        """Return True if piece can be placed in a column """
        if column < 0 or column >= self.width:
            return False
        
        return self.board[0][column] == ""            

    def addMove( self, column, player ):
        """Add  piece in column ,  can be  'X' or 'O' """
        if not self.Allow(column) or not (player == "X" or player == "O"):
            return
        for i in range(self.height):
            if self.board[i][col] != "":
                self.board[i-1][col] = player
                break   
            if i == self.height - 1:
                self.board[i][col] = player

    def setBoard(self, moveString):
        """ takes  string of columns and places alternating pieces in column """
        Next = 'X'   
        for colString in String:
            column = int(colString)
            if 0 <= column <= self.width:
                self.addMove(column, Next)
            if Next == 'X': Next = 'O'
            else: Next = 'X'

    def delete( self, column ):
        """Deletes the top piece of column """
        if column < 0 or column >= self.width:
            return
        for i in range( self.height ):
            if self.board[i][column] != " ":
                self.board[i][column] = " "
                break
                
    def checkWin(self, player):
        """Checks if piece has won """
   
        for i in range( self.height ):
            for j in range( self.width ):
                #Horizontal 
                if j < self.width - 3:  
                    count = 0
                    for z in range( 0, 4 ):
                        if self.board[i][j+z] ==player:
                            count += 1
                    if count == 4: return True

                #Vertical
                if i < self.height - 3:  
                    count = 0
                    for z in range( 0, 4 ):
                        if self.board[i+z][j] == player:
                            count += 1
                    if count == 4: return True

                
                #Diagonal /

                if i < self.height - 3 and j < self.width - 3: 
                    count = 0
                    for z in range( 0, 4 ):
                        if self.board[i+z][j+z] ==player:
                            count += 1
                    if count == 4: return True

                #Diagonal \
                if i > 2 and j < self.width - 3: 
                    count = 0
                    for z in range( 0, 4 ):
                        if self.board[i-z][j+z] == player:
                            count += 1
                    if count == 4: return True
                
                
        return False
            
    
