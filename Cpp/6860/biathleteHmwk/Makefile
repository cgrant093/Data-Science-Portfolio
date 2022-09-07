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

biathleteTest :
	g++ $(CXXFLAGS) -c src/shooter.cxx
	g++ $(CXXFLAGS) -c src/skier.cxx
	g++ $(CXXFLAGS) -c src/biathlete.cxx
	g++ $(CXXFLAGS) -c src/biathleteTest.cxx
	g++ $(LDFLAGS) shooter.o skier.o biathlete.o biathleteTest.o -o bin/biathleteTest
	rm skier.o shooter.o biathlete.o biathleteTest.o

clean :
	rm -f *.o 
	@echo "All cleaned-up :-)"


# DO NOT DELETE
