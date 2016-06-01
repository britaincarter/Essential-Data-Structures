Super Soda : Uses dynamic programming to discover, given `n` the number of cans of soda you want, what is the minimum cost required to obtain n sodas?
 And 
Given `x`, the amount of money you have, what is the maximum number of cans of soda you can afford with x dollars?




For the problem above, it would be `int[] sodaSizes = new int[] {1, 6, 12, 25, 36}`. 

`double[] costs` is an array of `double`s representing the cost of each of those case sizes.
For the problem above, it would be `double[] costs = new double[] { 0.8, 4, 7.5, 14, 20 }`. However implementing the algorithm you can replace the arrays with different values for a variety of packages.	

System.out.println(minimalSodaCost(sodaSizes, costs, 105));  // prints 59.6
System.out.println(Arrays.toString(minimalSodaCostCombinations(sodaSizes, costs, 105))); // prints [2, 1, 0, 1, 2]
```

since `[2, 1, 0, 1, 2]` is the number of 1 packs, 6 packs, 12 packs, 25 packs, 36 packs. Respectively that resulted in the cost of 59.6.

If I solved this not use dynamic programming (and solved this using either a bruter-force or a recursive divide-and-conquer algorithm), my runs would definitely take much longer amount of time especially if you test with values larger than n = 100,
run time exponentially increases with N.