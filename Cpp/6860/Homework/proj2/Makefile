######################

CXX           = g++
LD            = g++

#############################

os = $(shell uname -s)

#if more debug needed use -g option for CXXFLAGS in addition

ifeq ($(os),Linux)
CXXFLAGS      = -O -Wall
else
CXXFLAGS      = -O -fPIC -pipe -Wall -dynamiclib 
endif

ifeq ($(os),Linux)
LDFLAGS       = -O 
else
LDFLAGS       = -O -Xlinker -bind_at_load -flat_namespace
endif

LinAlTest :
	g++ $(CXXFLAGS) -c src/LinAlTest.cxx
	g++ $(CXXFLAGS) -c src/vecNd.cxx
	g++ $(CXXFLAGS) -c src/matrixNM.cxx
	g++ $(LDFLAGS) LinAlTest.o vecNd.o matrixNM.o -o bin/LinAlTest
	rm LinAlTest.o vecNd.o matrixNM.o

clean :
	rm -f *.o 
	@echo "All cleaned-up :-)"


# DO NOT DELETE
