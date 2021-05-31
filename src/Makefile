DOM=othello/java/Dominio/*.java othello/java/Dominio/Jugador/*.java othello/java/Dominio/Partida/*.java othello/java/Dominio/Ranking/*.java
PERSISTENCIA=othello/java/ControladorPersistencia/*.java othello/java/MyException/*.java
PRESENTACION=othello/java/Presentacion/*.java othello/java/Presentacion/Menus/*.java othello/java/Presentacion/Partida_Tablero/*.java othello/java/Presentacion/Ranking/*.java
MAIN=othello/java/Main.java
C_DOM=Dominio/*.class Dominio/Jugador/*.class Dominio/Partida/*.class Dominio/Ranking/*.class
C_PERSISTENCIA=ControladorPersistencia/*.class MyException/*.class
C_PRESENTACION=Presentacion/*.class Presentacion/Menus/*.class Presentacion/Partida_Tablero/*.class Presentacion/Ranking/*.class
C_MAIN=Main.class
MANIFEST=../../META-INF/MANIFEST.MF

all:
	javac --release 11 $(DOM) $(PERSISTENCIA) $(PRESENTACION) $(MAIN)
	cd othello/java/ && jar -cvfm Othello.jar $(MANIFEST) $(C_MAIN) $(C_DOM) $(C_PERSISTENCIA) $(C_PRESENTACION)
	mkdir -p ../EXE
	mkdir -p ../EXE/files
	mkdir -p ../EXE/files/fichas
	mkdir -p ../EXE/files/partidas
	mkdir -p ../EXE/files/ranking
	mkdir -p ../EXE/files/tableros
	mkdir -p ../EXE/files/users
	cp -r files/* ../EXE/files/
	mv othello/java/Othello.jar ../EXE/
	cd ../EXE/ && java -jar Othello.jar

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
	