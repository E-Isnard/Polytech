.PHONY:run

bin/main_mvector: src/main_mvector.cpp src/MVector.cpp
	@echo "Compilation en cours ..."
	@g++ -W -Wall src/main_mvector.cpp src/MVector.cpp -o bin/main_mvector

run: bin/main_mvector
	@./bin/main_mvector