.PHONY:
	run
bin/main_matrix.exe: src/Matrix.cpp src/MVector.cpp src/main_matrix.cpp
	@echo Compilation en cours...
	@g++ -W -Wall -lm src/Matrix.cpp src/MVector.cpp src/main_matrix.cpp -o bin/main_matrix

run: bin/main_matrix.exe
	@./bin/main_matrix.exe