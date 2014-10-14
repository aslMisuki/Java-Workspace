Nam Phan
my Optimization Strategy: 
I am using a greedy first search to optimize my wall hangings. 
The coverage method will use h() = paintings with the biggest area be placed first.
My program will place paintings from left to right, then top down, and then reiterating through the algorithm again to fill in blanks

The bigger is better method will use h() = Max(paintHeight, paintWidth)^2 and using this to sort and place paintings with priorites given to the biggest dimensions.

My algorithm also keeps track of the first index that it cannot hang a wall on so that it may revisit with a "skipped" list for more completeness.
The number of revisits to fill is set to 2, and can be increased for more completeness for more computing time.


working:
coverageMethod and coverSort are working, placing paintings onto the board methods are working as well
BiggerSort() is now working, and will not sort paintings by Max(dimM,dimN)

partially working:

not working:
