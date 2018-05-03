# prints the diamond shape
def diamond(numRows, char):
    # top half of diamond
    n = 0
    for i in range (1, numRows+1):
        # prints leading spaces
        for j in range (1, (numRows-i)+1):
            print(end=' ')

        # prints character
        while n != (2*i-1):
            print(char, end='')
            n = n+1
        n = 0
        
        # line break
        print() 

    # bottom half of diamond
    k = 1
    n = 1
    for i in range(1, numRows):
        # prints leading spaces
        for j in range (1, k+1):
             print(end=' ')
        k = k+1
         
        # prints character
        while n <= (2*(numRows-i)-1):
            print(char, end='')
            n = n+1
        n= 1
        print()
# end function

# driver
choice=""
while choice != "no":
	# reset choice if not first run of loop
        choice=""
        inputString=input("Enter a character to make the diamond with: ")
        # takes only the first character, even if more are input
        inputChar=inputString[0]
        inputRows=int(input("Enter the row size of the diamond: "))
        # calls the function
        diamond(inputRows,inputChar)
        while choice not in ["yes", "no"]:
            choice=input("Would you like to make another diamond? (type yes/no): ")
            choice= choice.lower()
print("Goodbye.")
# end
