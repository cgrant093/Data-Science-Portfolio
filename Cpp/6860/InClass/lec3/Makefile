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

textfile :
	g++ $(CXXFLAGS) -c src/textfile.cxx
	g++ $(LDFLAGS) textfile.o -o bin/textfile
	rm textfile.o

textfile2 :
	g++ $(CXXFLAGS) -c src/textfile2.cxx
	g++ $(LDFLAGS) textfile2.o -o bin/textfile2
	rm textfile2.o

textfile3 :
	g++ $(CXXFLAGS) -c src/textfile3.cxx
	g++ $(LDFLAGS) textfile3.o -o bin/textfile3
	rm textfile3.o

tempcheck :
	g++ $(CXXFLAGS) -c src/tempcheck.cxx
	g++ $(LDFLAGS) tempcheck.o -o bin/tempcheck
	rm tempcheck.o

signcheck :
	g++ $(CXXFLAGS) -c src/signcheck.cxx
	g++ $(LDFLAGS) signcheck.o -o bin/signcheck
	rm signcheck.o

forloopsum :
	g++ $(CXXFLAGS) -c src/forloopsum.cxx
	g++ $(LDFLAGS) forloopsum.o -o bin/forloopsum
	rm forloopsum.o

whileloopsum :
	g++ $(CXXFLAGS) -c src/whileloopsum.cxx
	g++ $(LDFLAGS) whileloopsum.o -o bin/whileloopsum
	rm whileloopsum.o

nestedfor :
	g++ $(CXXFLAGS) -c src/nestedfor.cxx
	g++ $(LDFLAGS) nestedfor.o -o bin/nestedfor
	rm nestedfor.o

guess :
	g++ $(CXXFLAGS) -c src/guess.cxx
	g++ $(LDFLAGS) guess.o -o bin/guess
	rm guess.o

random :
	g++ $(CXXFLAGS) -c src/random.cxx
	g++ $(LDFLAGS) random.o -o bin/random
	rm random.o

random2 :
	g++ $(CXXFLAGS) -c src/random2.cxx
	g++ $(LDFLAGS) random2.o -o bin/random2
	rm random2.o

numint :
	g++ $(CXXFLAGS) -c src/numint.cxx
	g++ $(LDFLAGS) numint.o -o bin/numint
	rm numint.o

numint2 :
	g++ $(CXXFLAGS) -c src/numint2.cxx
	g++ $(LDFLAGS) numint2.o -o bin/numint2
	rm numint2.o

templatetest :
	g++ $(CXXFLAGS) -c src/templatetest.cxx
	g++ $(LDFLAGS) templatetest.o -o bin/templatetest
	rm templatetest.o

all: textfile textfile2 textfile3 tempcheck signcheck forloopsum whileloopsum nestedfor guess random random2 numint numint2

clean :
	rm -f *.o 
	@echo "All cleaned-up :-)"

cleanall:
	rm -f *.o 
	rm bin/textfile outfile.txt bin/textfile2 bin/textfile3 bin/tempcheck bin/signcheck bin/forloopsum bin/whileloopsum bin/nestedfor bin/guess bin/random bin/random2 bin/numint bin/numint2

# DO NOT DELETE
