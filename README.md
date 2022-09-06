# Data-Science-Portfolio
Brief overview of my skills in data science and software development. 

Skills and projects are separated by language. Python being the most in-depth due to a combination of experience and relevance:
1. Python
    * Machine Learning (sklearn, numpy, PyTorch)
        * Supervised
            * Regression: Linear Regression, Decision Trees, Random Forests, Neural Networks
            * Classification: Logisitic Regression, Support Vector Machines, K-Nearest Neighbors, Decision Trees, Random Forests, Neural Networks
            * Deep Learning: Simple NNs, CNNs, RNN
        * Unsupervised
            * Clustering: K-means, Heirarchical, Mean shift, Density-based
            * Dimensionality Reduction: Principal Component Analysis (feature elimination and extraction)
    * Statistics (numpy, pandas, scipy)
        * Hypothesis Testing: t-test, ANOVA, Chi-Squared
        * Other topics: distributions, moments
    * Webscraping (requests, beautifulSoup)
    * Unit Testing (pytest)
    * GUIs (pyqt5)
2. SQL
3. Latex
4. Java
5. C++
6. Mathematica
7. Other Skills/Languages:
    * MATLAB, Excel, AWS, Quantum Machine Learning, Command Prompt



## Python
Python was self-taught along side simple neural network developement in 2017. My development experience with Python is predominately my personal projects and one of my research projects during my PhD, some of which can be found in this and other repositories found on my Github.

There is a single respository, [Valorant Data Project](https://github.com/cgrant093/Valorant-Data-Project), that covers many of my skills in python: webscraping with requests, organizing/cleaning data in pandas dataframes, aggregating and graphing data, feature selection for supervised model training. The future improvements will involve some combination of the following: classical or deep learning model training, hypothesis testing, and unit testing. A more detailed description can be found in its [repository](https://github.com/cgrant093/Valorant-Data-Project).


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

I also have a [published paper](https://inspirehep.net/literature/1771848) from my physics PhD research, which utilizes neural networks (numpy) as a non-linear regression model. This paper also utilizes a Monte Carlo algorithm to generate our pseudo-data from experimental measurements.

#### Unsupervised Learning
Unsupervised machine learning is when there are no known output values. Two main concepts are clustering and dimensionality reduction. Clustering is similar to classification, but will group the data itself. Dimesionality reduction is simply to reduce the number of features in the data set, which can be due to one feature being dependent on one or more other features, or the feature doesn't change across inputs and could be seen as 'unhelpful'. 

Common clustering algorithms are: K-means, Heirarchical, Mean shift, and Density-based. 

Principal Component Analysis (PCA) is very popular for dimesionality reduction.


#### Reinforced Learning
Similar to unsupervised learning in that there are not input/output pairs, but there is a reward system in place. This reward system tells the algorithm how 'good' this set of parameters is. This type of machine learning is primarily used for computer played video games, or running simulations of evolution. Usually, in one epoch or generation, there are multiple, slightly different, sets of parameters. The best one is found via the reward system, and that one has its parameters updated with some set of small deviations, and then all of those are ran for the next generation. This process is iterated until the desired outcome is reached.


### Statistics

Mean, median, mode, range, IQR, moments, common distributions

#### Hypothesis testing

##### t-test
##### ANOVA
##### Chi-squared

### Webscraping
### Unit testing
### GUIs



## SQL



## Latex



## Java



## C++



## Mathematica



## Other Skills/Languages:


### MATLAB, Excel, AWS, Quantum Machine Learning, Command Prompt




