Triangle Authenticator
by: Justin Lyon

This program is developed to handle file io through the console. 

Menu:
Enter 1-3 in the console for your choice of operations. 

1) Enter input from the console -
Read from console will prompt for the input of 3 integers, one 
at a time. This method will check in real time for non-zero int 
input. If the values you entered together cannot form a 
triangle, you will return to the start of the input prompt. 

Upon valid entry, the values are analyzed to determine 
triangle type. 
This is output to the console and appended to the file, Triangle.txt.

2) Read from a file - 
This choice begins a method, readFile(), which reads, and processes 
the file input. Immediately following this, the data is passed via
array of int to consoleOut(), and via ArrayList to the output 
method fileOut().

Triangle.txt by default exists in the project directory. This 
menu choice will read input from Triangle.txt. Input is read
and stored into an ArrayList. 

The file is delimited in two ways. By default a " " SPACE and 
a new line mark the data. A SPACE delimits the int values per line. 
The new line delimits objects in the ArrayList. 
Effectively, one line is parsed into a Triangle object as three 
distinct sides, instance variables of Triangle class a, b, and c. 

Choosing to ignore the standard delimiters will break the program. 

Public static readFile() will read the file according to default delimiters
into the ArrayList. In the ArrayList the data is processed. Any zero value 
int will be removed with ArrayList.remove(index). This method removes the 
index, shunts remaining indices left, and trims the ArrayList size.

As data is stored in the ArrayList, each line is sorted in ascending order
before passing to it's corresponding ArrayList index of Triangle object.

After reading and processing, the data is printed to the console then 
is printed again to a fresh Triangle.txt.

3) Exit - 
Changes the variable value of done to true. Exiting the program.  