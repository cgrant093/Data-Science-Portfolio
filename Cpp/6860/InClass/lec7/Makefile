######################

CXX           = g++
LD            = g++

#############################

os = $(shell uname -s)

INSTALL_DIR=/usr/local
GSL=$(INSTALL_DIR)

ifeq ($(os),Linux)
CXXFLAGS      = -O -Wall
else
CXXFLAGS      = -O -fPIC -pipe -Wall
endif

CXXFLAGSGSL = $(CXXFLAGS)
CXXFLAGSGSL += -I$(GSL)/include 
CXXFLAGSROOT = -I$(ROOTSYS)/include -std=c++11

ifeq ($(os),Linux)
LDFLAGS       = -O -g
LDFLAGSS      = -g --shared
else
LDFLAGS       = -O -Xlinker -bind_at_load -flat_namespace
LDFLAGSS      = -dynamiclib -flat_namespace -undefined suppress
LDFLAGSSS     = -bundle
endif

ROOTLIBS      = $(shell root-config --libs)

LIBSROOT       = $(ROOTLIBS)
LIBSGSL	       = $(LIBS)
LIBSGSL       += -L$(GSL)/lib -lgsl -lgslcblas


gslTest :
	g++ $(CXXFLAGSGSL) $(CXXFLAGSROOT) -c src/gslTest.cxx
	g++ $(LDFLAGS) gslTest.o $(LIBSGSL) $(LIBSROOT) -o bin/gslTest
	rm gslTest.o

matrixDetInv :
	g++ $(CXXFLAGSGSL) -c src/matrixDetInv.cxx
	g++ $(LDFLAGS) matrixDetInv.o $(LIBSGSL) -o bin/matrixDetInv
	rm matrixDetInv.o

PredPrey :
	g++ $(CXXFLAGSGSL) $(CXXFLAGSROOT) -c src/PredPrey.cxx
	g++ $(LDFLAGS) PredPrey.o $(LIBSGSL) $(LIBSROOT) -o bin/PredPrey
	rm PredPrey.o

test :
	g++ $(CXXFLAGS) -c src/test.cxx
	g++ $(LDFLAGS) test.o $(LIBS) -o bin/test
	rm test.o

test_gsl :
	g++ $(CXXFLAGSGSL) -c src/test_gsl.cxx
	g++ $(LDFLAGS) test_gsl.o $(LIBSGSL) -o bin/test_gsl
	rm test_gsl.o

clean :
	rm -f *.o 
	@echo "All cleaned-up :-)"

# DO NOT DELETE
