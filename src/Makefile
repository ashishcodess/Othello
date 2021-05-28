DOM=main/java/Dominio/*.java main/java/Dominio/Jugador/*.java main/java/Dominio/Partida/*.java main/java/Dominio/Ranking/*.java
PERSISTENCIA=main/java/ControladorPersistencia/*.java main/java/MyException/*.java
PRESENTACION=main/java/Presentacion/*.java main/java/Presentacion/Menus/*.java main/java/Presentacion/Partida_Tablero/*.java main/java/Presentacion/Ranking/*.java
MAIN=main/java/*.java
C_DOM=main/java/Dominio/*.class main/java/Dominio/Jugador/*.class main/java/Dominio/Partida/*.class main/java/Dominio/Ranking/*.class
C_PERSISTENCIA=main/java/ControladorPersistencia/*.class main/java/MyException/*.class
C_PRESENTACION=main/java/Presentacion/*.class main/java/Presentacion/Menus/*.class main/java/Presentacion/Partida_Tablero/*.class main/java/Presentacion/Ranking/*.class
C_MAIN=main/java/Main.class

all:
	javac --release 11 -encoding UTF-8 $(DOM) $(PERSISTENCIA) $(PRESENTACION) $(MAIN)
	jar cmf Othello.jar files/META-INF/MANIFEST.MF -C $(C_MAIN) $(C_DOM) $(C_PERSISTENCIA) $(C_PRESENTACION) 

clean:
	rm -rf ../EXE