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

vecNdTest :
	g++ $(CXXFLAGS) -c src/vecNdTest.cxx
	g++ $(CXXFLAGS) -c src/vecNd.cxx
	g++ $(LDFLAGS) vecNdTest.o vecNd.o -o bin/vecNdTest
	rm vecNdTest.o vecNd.o

clean :
	rm -f *.o 
	@echo "All cleaned-up :-)"


# DO NOT DELETE
