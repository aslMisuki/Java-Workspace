Nam Phan
This is the readme for A7

So for this project, I am going to implement classes:

BinaryRV.java
NaiveBayesClassifier.java
Query.java
TestData.java

TestData 
	- takes the test.data file and stores it as a list of Query

NaiveBayesClassifier.java
	- this takes Training.data and creates a model so we can classify queries in TestData.java with its probability
	- parseTrainingData is good
	- parseTestData is good
	- smoothCount is good
	- countValues() seems to me working, did some testing to make sure it incremented correctly
	

Query.java
	the query representation of the information inside TestData

TestData.java