.PHONY:
	run
bin/main_list.exe: src/List.cpp src/main_list_simple.cpp
	@echo Compilation en cours...
	@g++ -W -Wall -lm src/List.cpp src/main_list_simple.cpp -o bin/main_list.exe

run: bin/main_list.exe
	@./bin/main_list.exe