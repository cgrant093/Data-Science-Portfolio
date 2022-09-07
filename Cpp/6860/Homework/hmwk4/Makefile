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

complexTest :
	g++ $(CXXFLAGS) -c src/complexTest.cxx
	g++ $(CXXFLAGS) -c src/complex.cxx
	g++ $(LDFLAGS) complexTest.o complex.o -o bin/complexTest
	rm complexTest.o complex.o

clean :
	rm -f *.o 
	@echo "All cleaned-up :-)"


# DO NOT DELETE
