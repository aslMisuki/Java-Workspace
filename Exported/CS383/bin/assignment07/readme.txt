Nam Phan
This is the readme for A7

So for this project, I am going to implement classes:

NaiveBayesClassifier.java
Query.java
TestData.java

TestData.java
	- takes the test.data file and stores it as a list of Query obj

NaiveBayesClassifier.java
	- because the domain of each RV is relatively short, I decided to make 4 arrays storing occurances of 'y' and 'n' along with 2 doubles to keep track of total democrats and republicans
		The incrementation occurs during the parsing and storing of training.data
		- logic for this is to avoid iterating over TrainingData for each Xi
	- this takes Training.data and creates a model so we can classify queries in TestData.java with its probability
	- parseTrainingData is good
	- parseTestData is good
	- smoothCount is good
	- countValues() seems to me working, did some testing to make sure it incremented correctly
	- calcTestData() is working as it should, i just calls calcQuery() on all queries in TestData and prints out the result
	- calcQuery() is sorta working now. I'm not sure if I am supposed to get any values with e in it after using logs


Query.java
	a data structure for the information for queries
