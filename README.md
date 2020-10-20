# Nqueens
Place N queens on an NxN chess board so that none of them attack each other (the classic n-queens problem). Additionally, please make sure that no three queens are in a straight line at ANY angle, so queens on A1, C2 and E3, despite not attacking each other, form a straight line at some angle.

# Solution to the classic nqueens
The classic nqueens problem can be solved by recursively trying every cell in the chess board and building a search space around it. The search space is constructed with our constraints in mind (excluding 3 in a straight line for now). The chessboard is modeled as an array, the index of the array serves as the row numbers and the value at the index serves as the column at which we are placing our queen. For example, our first try will be to place our queen in the first column of the first row and this will look like `[0]`. This means we have placed a single queen so far at row 0 and column 0. Therefore,
- number of queens placed so far = size of the array
- index of the array = row at which we could place a queen
- value at an index = column at which we placed the queen for that row (index)

If we find that a queen cannot be placed in the next row due to our constraints, we will go back one step and try another column. This is much better than trying to enumerate all possible positions the queen can be in since we take an early exit.

# No 3 queens in a straight line
Three points are said to be in a straight line (collinear) if the triangle they form has an area of zero.

We will add this constraint by going through all our placements so far in pairs of 2 and applying the following formula to determine if any of the columns in the next row can fall in a straight line with the 2 queens we placed earlier. If so we remove that column from the list of columns where we could place a queen in that row.

Formula to find area of a triangle for 3 points named

A(x,y)

B(x,y)

C(x,y)

```
Area = | (Ax(By - Cy) + Bx(Cy - Ay) + Cx(Ay - By)) / 2 |
```

# References
- https://en.wikipedia.org/wiki/Eight_queens_puzzle
- https://www.youtube.com/watch?v=xFv_Hl4B83A
- https://math.stackexchange.com/questions/405966/if-i-have-three-points-is-there-an-easy-way-to-tell-if-they-are-collinear
 
