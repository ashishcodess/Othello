DOM=othello/java/Dominio/*.java othello/java/Dominio/Jugador/*.java othello/java/Dominio/Partida/*.java othello/java/Dominio/Ranking/*.java
PERSISTENCIA=othello/java/ControladorPersistencia/*.java othello/java/MyException/*.java
PRESENTACION=othello/java/Presentacion/*.java othello/java/Presentacion/Menus/*.java othello/java/Presentacion/Partida_Tablero/*.java othello/java/Presentacion/Ranking/*.java
MAIN=othello/java/Main.java
C_DOM=othello/java/Dominio/*.class othello/java/Dominio/Jugador/*.class othello/java/Dominio/Partida/*.class othello/java/Dominio/Ranking/*.class
C_PERSISTENCIA=othello/java/ControladorPersistencia/*.class othello/java/MyException/*.class
C_PRESENTACION=othello/java/Presentacion/*.class othello/java/Presentacion/Menus/*.class othello/java/Presentacion/Partida_Tablero/*.class othello/java/Presentacion/Ranking/*.class
C_MAIN=othello/java/Main.class
MANIFEST=META-INF/MANIFEST.MF

all:
	javac --release 11 $(DOM) $(PERSISTENCIA) $(PRESENTACION) $(MAIN)
	jar -cvfm Othello.jar $(MANIFEST) $(C_MAIN) $(C_DOM) $(C_PERSISTENCIA) $(C_PRESENTACION)
	mkdir -p ../EXE
	mkdir -p ../EXE/files
	mkdir -p ../EXE/files/fichas
	mkdir -p ../EXE/files/partidas
	mkdir -p ../EXE/files/ranking
	mkdir -p ../EXE/files/tableros
	mkdir -p ../EXE/files/users
	cp -r files/* ../EXE/files/
	mv Othello.jar ../EXE/

copy:
	rm -rf ../EXE
	mkdir -p ../EXE
	mkdir -p ../EXE/files
	mkdir -p ../EXE/files/fichas
	mkdir -p ../EXE/files/partidas
	mkdir -p ../EXE/files/ranking
	mkdir -p ../EXE/files/tableros
	mkdir -p ../EXE/files/users
	cp -r files/* ../EXE/files/

clean:
	rm -rf ../EXE
	rm -rf ../EXE/*.jar
	rm -rf othello/java/*.class
	rm -rf othello/java/MyException/*.class
	rm -rf othello/java/ControladorPersistencia/*.class
	rm -rf othello/java/Dominio/*.class
	rm -rf othello/java/Dominio/Jugador/*.class
	rm -rf othello/java/Dominio/Partida/*.class
	rm -rf othello/java/Dominio/Ranking/*.class
	rm -rf othello/java/Presentacion/*.class
	rm -rf othello/java/Presentacion/Menus/*.class
	rm -rf othello/java/Presentacion/Partida_Tablero/*.class
	rm -rf othello/java/Presentacion/Ranking/*.class
	