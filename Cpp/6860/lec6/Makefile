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

circleTest :
	g++ $(CXXFLAGS) -c src/circle.cxx
	g++ $(CXXFLAGS) -c src/cylinder.cxx
	g++ $(CXXFLAGS) -c src/sphere.cxx
	g++ $(CXXFLAGS) -c src/circleTest.cxx
	g++ $(LDFLAGS) circle.o cylinder.o sphere.o circleTest.o -o bin/circleTest
	rm cylinder.o circle.o sphere.o circleTest.o

clean :
	rm -f *.o 
	@echo "All cleaned-up :-)"


# DO NOT DELETE
