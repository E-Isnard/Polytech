.PHONY:
	run

bin/main_mvector: src/MVector.cpp src/main_mvector.cpp
	@echo Compilation en cours ...
	@g++ -W -Wall -lm src/main_mvector.cpp src/MVector.cpp -o bin/main_mvector

run: bin/main_mvector
	@./bin/main_mvector