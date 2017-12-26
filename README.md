# WalmartAssignment
Walmart  assignment

Use case:
Implement a simple ticket service that facilitates the discovery, temporary hold, and final reservation of seats within a high-demand performance venue.
For example, see the seating arrangement below.
 
        ----------[[  STAGE  ]]----------
        ---------------------------------
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
        sssssssssssssssssssssssssssssssss
Your homework assignment is to design and write a Ticket Service that provides the following functions
•	Find the number of seats available within the venue
Note: available seats are seats that are neither held nor reserved.
•	Find and hold the best available seats on behalf of a customer
Note: each ticket hold should expire within a set number of seconds. 
•	Reserve and commit a specific group of held seats for a customer
Requirements
•	Use a programming language that you are comfortable with. We work in Java, but we are more interested in understanding how you think than in language specifics.
•	The solution and tests should build and execute entirely via the command line.
•	A README file should be included in your submission that documents your assumptions and includes instructions for building the solution and executing the tests
•	Implementation mechanisms such as disk-based storage, a REST API, and a front-end GUI are not strictly required
 
Your solution will be reviewed by engineers that you will be working with if you join the Walmart Technology team. We are interested in seeing your software design, coding, and testing skill


Steps to Execute the project:

I have implemented five methods under TicketingService. 

1)	findNoOfSeatsAvilable()

This method returns information about seats availability which includes number of seats available, Number of seats currently, under hold and number of seats are reserved till now.

Sample output: 

No of Available seats: 47
No of Held seats: 0
No of Reserved seats: 23


2)	FindAndHoldSeats()

This method finds available seats at costumer preferred row if that row has enough seats it will automatically holds for the customer. Incase if that row doesn’t have enough seats it will find remaining seats at nearest row and ask the customer to confirm before holding them.

If customer doesn’t provide the row name then it will find the best available seats and holds for the customer.

If stage doesn’t have enough costumer required number of available seats then it will not hold any seats for costumer it returns message with number of available seats.

Sample out put

1)
Enter Preferred Row only Single Character between A to G
G
Enter required number of seats
5
Requested 5 tickets are in Hold for 100 seconds please reserve before hold time
Enter Y to reserve N to ignore
Y
 Requested 5 seats are reserved

2)
Enter Preferred Row Only Single Character between A to G
B
Enter required number of seats
4
Row :C Column :4
Row :C Column :5
Row :C Column :6
Row :C Column :7

Requested 4 tickets are not available in same row please review seat number and confirm Y/N
Y
Requested 4 seats are reserved

3)
Enter Preferred Row only Single Character between A  to G
D
Enter required number of seats
123
Currently, 38 seats are available so couldn’t book 123 tickets 


3)	holdSeats()

For this method, it’s customer responsibility to find the available seats in the seat map. For this method customer can specify the required number of seats then it will ask for the row name and column number for each method. Then this method try to hold the customer preferred specific seats if they are available

Sample outputs.
1)
Enter required number of seats
3
Enter preferred Row only Single Character between A  to G for Seat 1
A
Enter preferred column only Single integer between 1 to 10 for Seat 1
1
Enter preferred Row only Single Character between A  to G for Seat 2
B
Enter preferred column only Single integer between 1 to 10 for Seat 2
2
Enter preferred Row only Single Character between A  to G for Seat 3
C
Enter preferred column only Single integer between 1 to 10 for Seat 3
3
Requested 3 tickets are in Hold for 100 seconds please reserve before hold time
Enter Y to reserve N to ignore
Y
Requested 3 seats are reserved

2)

Enter required number of seats
1
Enter preferred Row only Single Character between A  to G for Seat 1
A
Enter preferred column only Single integer between 1 to 10 for Seat 1
1
Requested Seats are not available

4)reserveAndCommitSeats()

This method will be invoked after customer successfully holds the seats. This method takes customer held seats and check for the hold time. If given sets are still hold under the same customer it will reserve the seats and confirms the customer.

Sample putput.

Requested 3 tickets are in Hold for 100 seconds please reserve before hold time
Enter Y to reserve N to ignore
Y
Requested 3 seats are reserved

5)dislpySeatMap()

This method displays seat map.

Sample output.

	1	2	3	4	5	6	7	8	9	10
A	R	A	A	A	A	A	A	A	A	A
B	A	R	A	A	A	A	A	A	A	A
C	A	A	R	A	A	A	A	A	A	A
D	A	A	A	A	A	A	A	A	A	A
E	A	A	A	A	A	A	A	A	A	A
F	A	A	A	A	A	A	A	A	A	A
G	A	A	A	A	A	A	A	A	A	A
A - Available : 67
H - Hold : 0
A - Reserved : 3


To invoke all above methods I have developed an utility class 
This calls has switch case for each method.

1 - Display seat Map
2 - Find Available, Held and Reserved seats
3 - Find and Hold Seats with customer preferred row and Reserve held seats
4 - Hold seats with customer with customer Preferred row and column and Reserve held seats
5 -Find and Hold Seats with system preferred best available and Reserve held seats
6 - Exit
1
	1	2	3	4	5	6	7	8	9	10
A	A	A	A	A	A	A	A	A	A	A
B	A	A	A	A	A	A	A	A	A	A
C	A	A	A	A	A	A	A	A	A	A
D	A	A	A	A	A	A	A	A	A	A
E	A	A	A	A	A	A	A	A	A	A
F	A	A	A	A	A	A	A	A	A	A
G	A	A	A	A	A	A	A	A	A	A
A - Available : 70
H - Hold : 0
A - Reserved : 0

1 - Display seat Map
2 - Find Available, Held and Reserved seats
3 - Find and Hold Seats with customer preferred row and Reserve held seats
4 - Hold seats with customer with customer Preferred row and column and Reserve held seats
5 -Find and Hold Seats with system preferred best available and Reserve held seats
6 - Exit
2
No of Available seats: 70
No of Held seats: 0
No of Reserved seats: 0

1 - Display seat Map
2 - Find Available, Held and Reserved seats
3 - Find and Hold Seats with customer preferred row and Reserve held seats
4 - Hold seats with customer with customer Preferred row and column and Reserve held seats
5 -Find and Hold Seats with system preferred best available and Reserve held seats
6 - Exit
3
Enter Customer Name
Sreenivas
Enter Ph Number
1231231234
Enter Preferred Row only Single Character between A  to G
B
Enter required number of seats
4
Requested 4 seats are in Hold for 100 seconds please reserve before hold time
Enter Y to reserve N to ignore
Y
 Requested 4 seats are reserved

1 - Display seat Map
2 - Find Available, Held and Reserved seats
3 - Find and Hold Seats with customer preferred row and Reserve held seats
4 - Hold seats with customer with customer Preferred row and column and Reserve held seats
5 -Find and Hold Seats with system preferred best available and Reserve held seats
6 - Exit
4
Enter Customer Name 

Harry
Enter Ph Number 

2341233456
Enter required number of seats
2
Enter preferred Row only Single Character between A  to G for Seat 1
E
Enter preferred column only Single integer between 1 to 10 for Seat 1
2
Enter preferred Row only Single Character between A  to G for Seat 2
F
Enter preferred column only Single integer between 1 to 10 for Seat 2
5
Requested 2 tickets are in Hold for 100seconds please resorve before hold time
Enter Y to reserve N to ignore
Y
 Requested 2 seats are reserved

1 - Display seat Map
2 - Find Available, Held and Reserved seats
3 - Find and Hold Seats with customer preferred row and Reserve held seats
4 - Hold seats with customer with customer Preferred row and column and Reserve held seats
5 -Find and Hold Seats with system preferred best available and Reserve held seats
6 - Exit
5
Enter Customer Name 

Charly
Enter Ph Number 

4563452345
Enter required number of seats
5
Requested 5 seats are in Hold for 100seconds please reserve before hold time
Enter Y to reserve N to ignore
y
 Requested 5 seats are reserved

1 - Display seat Map
2 - Find Available, Held and Reserved seats
3 - Find and Hold Seats with customer preferred row and Reserve held seats
4 - Hold seats with customer with customer Preferred row and column and Reserve held seats
5 -Find and Hold Seats with system preferred best available and Reserve held seats
6 - Exit
6

