# Data-Science-Portfolio
Brief overview of my knowledge and skills that are useful in data science and software development. 

Skills and projects are separated by language. Python being the most in-depth due to a combination of experience and relevance:
1. Python
    * Machine Learning (sklearn, numpy, PyTorch)
    * Statistical Tests (numpy, pandas, scipy, statsmodels)
    * Pandas DataFrame manipulation (pandas)
    * Webscraping (requests)
    * Unit Testing (pytest)
    * GUIs (pyqt5)
    * Quantum Computing and QML (Pennylane)
2. SQL
3. LaTeX
4. Java
5. C++
6. Mathematica
7. Other Skills/Languages:
    * MATLAB, Excel, AWS, Quantum Machine Learning, Command Prompt



## Python
Python was self-taught along side simple neural network developement in 2017. My development experience with Python is predominately my personal projects and one of my research projects during my PhD, some of which can be found in this and other repositories found on my Github.

One of my respositories, [Valorant Data Project](https://github.com/cgrant093/Valorant-Data-Project), covers many of my skills in python: webscraping with requests, organizing/cleaning data in pandas dataframes, aggregating and graphing data, and feature selection for supervised model training. The future developments will involve some combination of the following: classical or deep learning model training, hypothesis testing (ANOVA), and unit testing. A more detailed description can be found [here](https://github.com/cgrant093/Valorant-Data-Project).


### Machine Learning 
There are three generic types of machine learning algorithms which are supervised, unsupervised, and reinforced.

#### Supervised Learning
Supervised machine learning is when the training data has inputs associated with known, labeled outputs (or target values). After training is complete, the algorithm will then predict the outputs for unlabeled input values. These problems can be divided up into two subcategories: regression and classification. Regression algorithms are used for finding a relationship between the independent variable(s) and the dependent variable, and the output of these is continuous. The output for classification models is discrete, and it is trying to group data based on two or more groups found in the training data.

For notable projects using supervised machine learning algorithms in python, please check the following repositories:
* [Valorant Data Project](https://github.com/cgrant093/Valorant-Data-Project): classification_models.ipynb is studying which sklearn classification model is best for the specific features selected
* [Handwritten Recognition](https://github.com/cgrant093/Handwritten-Recognition): has two projects of note, 
   * Simple_NN uses numpy to create a handwritten digit reader. This was my first ML and python project back in 2017
   * Handwrote_Word_Reader uses PyTorch to create a handwritten word reader utilizing CNNs, bidirectional LSTM RNNs, and a CTC

In this repository, in the Python/[Machine Learning](https://github.com/cgrant093/Data-Science-Portfolio/tree/main/Python/Machine%20Learning) subfolder:
* regression_models: compares simple sklearn regression ML models on a randomly generated dataset
* classification_models: compares simple sklearn classification model on a randomly generated dataset
* midi_composer_classifier: trying to classify midi data to a known set of composers and there are three files that do not belong to our known list of composers.

I also have a [published paper](https://inspirehep.net/literature/1771848) from one of my PhD research projects. It utilizes neural networks (numpy) as unbiased, non-linear regressors. This project also generates the (training) pseudo-data from experimental measurements using a Monte Carlo algorithm.

#### Unsupervised Learning
Unsupervised machine learning is when there are no known output values. Two main concepts are clustering and dimensionality reduction. Clustering is similar to classification, but will group the data itself. Dimesionality reduction is simply to reduce the number of features in the data set, which can be due to one feature being dependent on one or more other features, or the feature doesn't change across inputs and could be seen as 'unhelpful'. 

Common clustering algorithms are: K-means, Heirarchical, Mean shift, and Density-based. 

Principal Component Analysis (PCA) is very popular for dimesionality reduction.

#### Reinforced Learning
Similar to unsupervised learning in that there are not input/output pairs, but there is a reward system in place. This reward system tells the algorithm how 'good' this set of parameters is. This type of machine learning is primarily used for computer played video games, or running simulations of evolution. Usually, in one epoch or generation, there are multiple, slightly different, sets of parameters. The best one is found via the reward system, and that one has its parameters updated with some set of small deviations, and then all of those are ran for the next generation. This process is iterated until the desired outcome is reached.


### Statistical tests
In the Python/[Statistical Methods](https://github.com/cgrant093/Data-Science-Portfolio/tree/main/Python/Statistical%20Methods) subfolder, the file [hypothesis_testing](https://github.com/cgrant093/Data-Science-Portfolio/blob/main/Python/Statistical%20Methods/hypothesis_testing.ipynb) uses a few different versions of t-tests, ANOVA tests, and chi-squared tests on a dataFrame I created. The file used to create the fake dataFrame and the csv file is located in the same subfolder.


### Pandas DataFrame manipulation
### Webscraping
### Unit testing
### GUIs
### Quantum Computing and QML



## SQL



## LaTeX
I have been using LaTeX during my entire grad school career. I have become very fluent in this 'language'.

I have helped write several research papers, whch can be found in my resume. Much of my grad school homework was written in LaTex; as well as, my dissertation and its defense slides, and my resume were all constructed using it. As well as any supplimentary PDFs found in the other folders. I have added my LaTex code for my resume and defense in the LaTeX folder. 


## Java
I primarily used Java in undergrad, specifically in classes CS101 and CS102, and it was the main language used for my undergraduate thesis.

My undergrad thesis has been added to the Java/[Undergrad_Thesis](https://github.com/cgrant093/Data-Science-Portfolio/tree/main/Java/Undergrad_Thesis) subfolder. The readme in this folder goes into detail, but I beg forgiveness from whomever looks at this code. It was early in my coding career and the inexperience shows.

The other project is a the final project I did for CS-102 back in 2016. There were 5 main projects for this class and all of them were a calendar application. Each time, the requirements for the application would become more strict/complex or change. The main addition for this final version was adding a GUI using JFrame as opposed to the user inferfacing with the command prompt. It was the first project were the whole coding process finally clicked for me.


## C++
My main experience with C++ comes from a class I took my first year of grad school, Computers in High Energy Physics, or something along those lines. The class started as if you didn't know and C++ and then quickly moved along to applications. I felt I was quick to learn C++ with my previous Java experience. This concept I've felt has been more true every language I pick up because it is more or less a difference in syntax, but I digress. 

The Cpp/[6860](https://github.com/cgrant093/Data-Science-Portfolio/tree/main/Cpp/6860) subfolder holds the small projects and coding notes taken for this class. The class also went into learning some terminal commands.

The final project, which I collaborated with 2 other students, can be found in this shared [repository](https://github.com/imooney/relativistic-resonance-decays).


## Mathematica

A large majority of my grad school research was performed using Mathematica.






