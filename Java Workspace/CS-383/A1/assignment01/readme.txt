Name: Nam Phan
Search Strategy:

Working:
Validity.java is fully operational, and works with everything except nx1 boards (which you said you won't be testing for)
All moves are operational
Board print is operational

Partially Working:

Broken:
At the moment have not implemented the solveRun(), which will contain the depth first search algorithm
The algorithm will move 1 number at a time, up, down or right, depending on where the number needs to go, for instance
if the number if 1, and it is in column 2, then it will move up instead of down. The tile moves to the right if the tile above it is already that number then moves up


Algorithm:

Endstate must include:
	1. all numbers in the same column must be decrementing sequentially until you reach rule 4
	2. all numbers in the same row must be decrementing sequentially until you reach rule 4
	3. all numbers in the same diagonal row must decrement sequentially until you reach rule 4
	4. to solve bottom to top and left to right rotations, the largest number in the row/column must be followed by the lowest number
	
Initial state cannot reach the End state if their rows or columns do not obey the above

Algorithms:

Swapping numbers up:
d1, r1, u1, l0 repeat m more times to replace row 1 with row 2

Row finding:
Using Depth first Search
start at upper left corner, then find to the right, then to the right of that