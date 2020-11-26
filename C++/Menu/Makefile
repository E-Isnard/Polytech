bin/tst_menu.exe: src/menu_item.hpp src/menu.hpp src/simple_menu_item.hpp src/tst_menu.cpp src/walking_item.hpp
	@echo Compilation en cours...
	@g++ -Wall src/tst_menu.cpp -o bin/tst_menu 

run: bin/tst_menu.exe
	@./bin/tst_menu

.PHONY:run